package edu.northeastern.cs5500.starterbot.controller;

import com.mongodb.lang.Nullable;
import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.exception.ForceCancelBattleException;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

/**
 * BattleController is used to manage one-turned battle workflow. It includes create battle, get
 * battle, accept battle, choose pokemon to battle, decline battle, cancel battle, complete battle,
 * get winner from battle, and other helper methods.
 */
@Singleton
@Slf4j
public class BattleController {

    public static final String ATTEMPT_TO_COMPLETE_NO_CONFIRMED_BATTLE =
            "Attempt to complete a battle where not everybody has confirmed it";
    public static final String ATTEMPT_TO_COMPLETE_MISSING_POKEMON_BATTLE =
            "Attempt to complete a battle where some pokemon are missing";
    public static final String ATTEMPT_TO_COMPLETE_NOT_EXIST_BATTLE =
            "Attempt to complete a battle that does not exist";
    public static final String CANCELLED_BATTLE = "%s cancelled their battle";
    public static final String FROM_USER_OFFERED_TO_BATTLE = "%s (fromUser) offered to battle %s";
    public static final String CHOSE_POKEMON_TO_BATTLE_MORE_THAN_ONCE =
            "%s chose pokemon on a battle more than once";
    public static final String EXCEPTION_CANNOT_CHANGE_UPDATE_CHOICE_ON_A_BATTLE =
            "Cannot change/update choice on a battle";
    public static final String OFFERED_POKEMON_TO_A_NOT_START_BATTLE =
            "%s offered pokemon on a battle which hasn't started yet";
    public static final String EXCEPTION_THE_BATTLE_HASN_T_STARTED = "The battle hasn't started.";
    public static final String EXCEPTION_THIS_BATTLE_IS_NOT_ACTIVE = "This battle is not active.";
    public static final String OFFERED_POKEMON_TO_BATTLE_BUT_NO_BATTLE_EXISTS =
            "%s offered pokemon on a battle but no battle exists";
    public static final String EXCEPTION_FRIEND_NO_POKEMONS_TO_BATTLE =
            "Your friend doesn't have any pokemons to battle";
    public static final String EXCEPTION_NO_POKEMONS_TO_BATTLE =
            "You don't have any pokemons to battle";
    public static final String USER_OFFERED_TO_BATTLE = "%s (toUser) offered to battle %s";
    public static final String THE_BATTLE_IS_NOT_ACTIVE = "The battle is not active.";
    public static final String FROM_USER_NO_LONGER_POSSESS_THEIR_POKEMON =
            "From user no longer possess their pokemon";
    public static final String TO_USER_NO_LONGER_POSSESS_THEIR_POKEMON =
            "To user no longer possess their pokemon";
    public static final String BATTLE_CANCELLED_BY_USER =
            "You no longer possess the pokemon you offered. The battle will be cancelled.";
    public static final String BATTLE_CANCELLED_BY_OTHER_PARTY =
            "The other party no longer possess the pokemon they offered. The battle will be cancelled.";
    public static final String EXCEPTION_NO_BATTLE_EXISTS =
            "Chose pokemon on a battle that doesn't exist";
    public static final String EXCEPTION_CANNOT_BATTLE_YOURSELF =
            "You cannot battle with yourself!";
    public static final String EXCEPTION_ALREADY_ACCEPTED_BATTLE =
            "You have already accepted battle";
    public static final String LOG_ERROR_NO_BATTLE_EXISTS =
            "%s chooses pokemon to a battle to %s but no battle exists";
    public static final String LOG_ERROR_ACCEPTED_BATTLE =
            "%s accepted the same battle more than once";
    public static final String LOG_ERROR_DECLINE_A_NON_EXISTED_BATTLE =
            "%s tried to decline a battle to %s that does not exist";
    public static final String LOG_INFO_DECLINED_A_BATTLE = "%s declined a battle with %s";
    public static final int ONE_HANDURD = 100;

    GenericRepository<Battle> battleRepository;
    PokemonController pokemonController;
    UserInventoryController userInventoryController;

    @Inject
    public BattleController(
            GenericRepository<Battle> battleRepository,
            PokemonController pokemonController,
            UserInventoryController userInventoryController) {
        this.battleRepository = battleRepository;
        this.pokemonController = pokemonController;
        this.userInventoryController = userInventoryController;
    }

    /**
     * Created a battle between two users.
     *
     * @param fromUserId the user who started the fight
     * @param toUserId the user who was challenged
     * @return Battle
     * @throws BattleException
     */
    @Nonnull
    public Battle createBattle(String fromUserId, String toUserId) throws BattleException {
        if (fromUserId.equals(toUserId)) {
            throw new BattleException(EXCEPTION_CANNOT_BATTLE_YOURSELF);
        }

        Battle existingBattle = getBattleWithTwoUsers(fromUserId, toUserId);

        if (existingBattle != null) {
            return existingBattle;
        }

        Battle newBattle = new Battle();
        newBattle.setFromUserId(fromUserId);
        newBattle.setToUserId(toUserId);
        newBattle.setAccepted(false);
        newBattle.setToUserConfirmedBattle(false);

        ArrayList<ObjectId> fromUserPokemonList = getUserPokemonList(fromUserId);
        if (fromUserPokemonList == null || fromUserPokemonList.isEmpty()) {
            throw new BattleException(EXCEPTION_NO_POKEMONS_TO_BATTLE);
        } else {
            newBattle.setFromUserPokemonList(fromUserPokemonList);
        }

        ArrayList<ObjectId> toUserPokemonList = getUserPokemonList(toUserId);
        if (toUserPokemonList == null || toUserPokemonList.isEmpty()) {
            throw new BattleException(EXCEPTION_FRIEND_NO_POKEMONS_TO_BATTLE);
        } else {
            newBattle.setToUserPokemonList(toUserPokemonList);
        }

        return battleRepository.add(newBattle);
    }

    /**
     * Gets user's pokemon list
     *
     * @param userId userId
     * @return ArrayList<ObjectId> list of pokemonId
     * @throws BattleException
     */
    private ArrayList<ObjectId> getUserPokemonList(String userId) throws BattleException {
        return userInventoryController.getPartyForUser(userId);
    }

    /**
     * Get the active Battle object between these two users if it exists.
     *
     * @param fromUserId the user who started the fight
     * @param toUserId the user who was challenged
     * @return null if there is no acitve battle, otherwise a Battle object
     */
    @Nullable
    public Battle getBattleWithTwoUsers(String fromUserId, String toUserId) {
        if (fromUserId.equals(toUserId)) { // cannot battle with yourself
            return null;
        }

        for (Battle battle : battleRepository.getAll()) {
            if (battle.getFromUserId().equals(fromUserId)
                    || battle.getToUserId().equals(fromUserId)) {
                if (battle.getFromUserId().equals(toUserId)
                        || battle.getToUserId().equals(toUserId)) {
                    return battle;
                }
            }
        }

        return null;
    }

    /**
     * Get the active Battle object between these two users if it exists.
     *
     * @param userId the user
     * @return null if there is no acitve battle, otherwise a Battle object
     */
    @Nullable
    public Battle getBattleWithOneUser(String userId) {
        for (Battle battle : battleRepository.getAll()) {
            if (battle.getFromUserId().equals(userId) || battle.getToUserId().equals(userId)) {
                return battle;
            }
        }
        return null;
    }

    /**
     * Gets all battles from battle repository
     *
     * @return ArrayList<Battle> list of battle
     */
    @Nullable
    public ArrayList<Battle> getAllBattles() {
        ArrayList<Battle> battles = new ArrayList<>();
        for (Battle battle : battleRepository.getAll()) {
            battles.add(battle);
        }
        return battles;
    }

    /**
     * Accept battle request
     *
     * @param acceptingUserId
     * @return Battle
     * @throws BattleException
     */
    public Battle acceptBattleRequest(String acceptingUserId) throws BattleException {
        Battle existingBattle = getBattleWithOneUser(acceptingUserId);
        if (existingBattle == null) {
            log.error(LOG_ERROR_NO_BATTLE_EXISTS, acceptingUserId, acceptingUserId);
            throw new BattleException(EXCEPTION_NO_BATTLE_EXISTS);
        }
        if (!existingBattle.getToUserId().equals(acceptingUserId)) {
            log.error(LOG_ERROR_ACCEPTED_BATTLE, acceptingUserId);
            throw new BattleException(EXCEPTION_ALREADY_ACCEPTED_BATTLE);
        }
        existingBattle.setToUserConfirmedBattle(true);
        Battle updatedBattle = battleRepository.update(existingBattle);
        return updatedBattle;
    }

    /**
     * Accepted a battle between these two users if it does not exist.
     *
     * @param acceptingUserId the user that accepted a battle
     * @return Battle
     * @throws BattleException
     */
    @Nonnull
    public Battle acceptBattle(String acceptingUserId) throws BattleException {

        Battle existingBattle = getBattleWithOneUser(acceptingUserId);

        if (existingBattle == null) {
            log.error(LOG_ERROR_NO_BATTLE_EXISTS, acceptingUserId, acceptingUserId);
            throw new BattleException(EXCEPTION_NO_BATTLE_EXISTS);
        }

        if (existingBattle.getFromUserId().equals(acceptingUserId)) {
            Boolean accepted = existingBattle.getFromUserAccept();
            if (accepted) {
                log.error(LOG_ERROR_ACCEPTED_BATTLE, acceptingUserId);
                throw new BattleException(EXCEPTION_ALREADY_ACCEPTED_BATTLE);
            }
            existingBattle.setFromUserAccept(true);
        } else if (existingBattle.getToUserId().equals(acceptingUserId)) {
            Boolean accepted = existingBattle.getToUserAccept();
            if (accepted) {
                log.error(LOG_ERROR_ACCEPTED_BATTLE, acceptingUserId);
                throw new BattleException(EXCEPTION_ALREADY_ACCEPTED_BATTLE);
            }
            existingBattle.setToUserAccept(true);
        }

        battleRepository.update(existingBattle);
        return existingBattle;
    }

    /**
     * Choose a pokemon to battle
     *
     * @param userId userId
     * @param pokemonToBattle the pokemon to battle
     * @return Battle
     * @throws BattleException
     */
    @Nonnull
    public Battle choosePokemonToBattle(String userId, ObjectId pokemonToBattle)
            throws BattleException {
        Battle existingBattle = getBattleWithOneUser(userId);

        if (existingBattle == null) {
            log.error(OFFERED_POKEMON_TO_BATTLE_BUT_NO_BATTLE_EXISTS, userId);
            throw new BattleException(EXCEPTION_THIS_BATTLE_IS_NOT_ACTIVE);
        }

        if (!existingBattle.getToUserConfirmedBattle()) {
            log.error(OFFERED_POKEMON_TO_A_NOT_START_BATTLE, userId);
            throw new BattleException(EXCEPTION_THE_BATTLE_HASN_T_STARTED);
        }

        if (existingBattle.getFromUserId().equals(userId)) {
            ObjectId existingPokemon = existingBattle.getFromUserActivePokemonId();
            if (existingPokemon != null) {
                log.error(CHOSE_POKEMON_TO_BATTLE_MORE_THAN_ONCE, userId);
                throw new BattleException(EXCEPTION_CANNOT_CHANGE_UPDATE_CHOICE_ON_A_BATTLE);
            }
            log.info(FROM_USER_OFFERED_TO_BATTLE, userId, pokemonToBattle);
            existingBattle.setFromUserActivePokemonId(pokemonToBattle);
        } else {
            ObjectId existingPokemon = existingBattle.getToUserActivePokemonId();
            if (existingPokemon != null) {
                log.error(CHOSE_POKEMON_TO_BATTLE_MORE_THAN_ONCE, userId);
                throw new BattleException(EXCEPTION_CANNOT_CHANGE_UPDATE_CHOICE_ON_A_BATTLE);
            }
            log.info(USER_OFFERED_TO_BATTLE, userId, pokemonToBattle);
            existingBattle.setToUserActivePokemonId(pokemonToBattle);
        }

        battleRepository.update(existingBattle);
        return existingBattle;
    }

    /**
     * Declined the battle between these two users if it exists.
     *
     * @param decliningUserId the user that requested rejection
     * @param otherBattlingUserId the other user in the battle
     * @return ture if an active battle was sucessfully declined, flase otherwise
     */
    @Nonnull
    public boolean declineBattle(String decliningUserId, String otherBattlingUserId) {

        Battle existinBattle = getBattleWithTwoUsers(decliningUserId, otherBattlingUserId);

        if (existinBattle == null) {
            log.error(LOG_ERROR_DECLINE_A_NON_EXISTED_BATTLE, decliningUserId, otherBattlingUserId);
            return false;
        }

        log.info(LOG_INFO_DECLINED_A_BATTLE, decliningUserId, otherBattlingUserId);
        existinBattle.setFromUserAccept(false);
        existinBattle.setToUserAccept(false);
        battleRepository.delete(existinBattle.getId());
        return true;
    }

    /**
     * Cancelled the battle between these two users if it exists.
     *
     * @param cancellingUserId the user that requested cancellation
     * @return Battle
     * @throws BattleException
     */
    public Battle cancelBattle(String cancellingUserId) throws BattleException {
        Battle existinBattle = getBattleWithOneUser(cancellingUserId);

        if (existinBattle == null) {
            log.error(LOG_ERROR_DECLINE_A_NON_EXISTED_BATTLE, cancellingUserId);
            throw new BattleException(THE_BATTLE_IS_NOT_ACTIVE);
        }

        log.info(CANCELLED_BATTLE, cancellingUserId);
        existinBattle.setFromUserAccept(false);
        existinBattle.setToUserAccept(false);
        battleRepository.delete(existinBattle.getId());
        return existinBattle;
    }

    /**
     * Completes the battle between two users.
     *
     * @param battleId an ID of the battle between two users
     * @return completed battle
     * @throws BattleException
     */
    public Battle completeBattle(ObjectId battleId) throws BattleException {
        Battle battle = battleRepository.get(battleId);
        if (battle == null) {
            log.error(ATTEMPT_TO_COMPLETE_NOT_EXIST_BATTLE);
            throw new BattleException(THE_BATTLE_IS_NOT_ACTIVE);
        }
        ObjectId fromUserPokemonId = battle.getFromUserActivePokemonId();
        ObjectId toUserPokemonId = battle.getToUserActivePokemonId();
        if (fromUserPokemonId == null || toUserPokemonId == null) {
            log.error(ATTEMPT_TO_COMPLETE_MISSING_POKEMON_BATTLE);
            throw new BattleException(THE_BATTLE_IS_NOT_ACTIVE);
        }
        if (!battle.getFromUserAccept() || !battle.getToUserAccept()) {
            log.error(ATTEMPT_TO_COMPLETE_NO_CONFIRMED_BATTLE);
            throw new BattleException(THE_BATTLE_IS_NOT_ACTIVE);
        }
        ArrayList<ObjectId> fromUserParty =
                userInventoryController.getPartyForUser(battle.getFromUserId());
        ArrayList<ObjectId> toUserParty =
                userInventoryController.getPartyForUser(battle.getToUserId());
        if (!fromUserParty.contains(fromUserPokemonId)) {
            log.error(FROM_USER_NO_LONGER_POSSESS_THEIR_POKEMON);
            throw new ForceCancelBattleException(
                    battle.getFromUserId(),
                    battle.getToUserId(),
                    BATTLE_CANCELLED_BY_USER,
                    BATTLE_CANCELLED_BY_OTHER_PARTY);
        }
        if (!toUserParty.contains(toUserPokemonId)) {
            log.error(TO_USER_NO_LONGER_POSSESS_THEIR_POKEMON);
            throw new ForceCancelBattleException(
                    battle.getToUserId(),
                    battle.getFromUserId(),
                    BATTLE_CANCELLED_BY_USER,
                    BATTLE_CANCELLED_BY_OTHER_PARTY);
        }

        Pokemon fromUserPokemon = pokemonController.getPokemonById(fromUserPokemonId);
        Pokemon toUserPokemon = pokemonController.getPokemonById(toUserPokemonId);

        String winUserId =
                getBattleWinnerByRandomlyBaseStats(battle, fromUserPokemon, toUserPokemon);
        if (winUserId != null) {
            battle.setWinUserId(winUserId);
            if (winUserId.equals(battle.getFromUserId())) {
                toUserParty.remove(toUserPokemonId);
            } else if (winUserId.equals(battle.getToUserId())) {
                fromUserParty.remove(fromUserPokemonId);
            }
        }
        userInventoryController.setPartyForUser(battle.getFromUserId(), fromUserParty);
        userInventoryController.setPartyForUser(battle.getToUserId(), toUserParty);
        battleRepository.delete(battleId);
        return battle;
    }

    /**
     * Gets winner from a battle based on random health, attack, and speed
     *
     * @param battle battle
     * @param fromUserPokemon from user's pokemon
     * @param toUserPokemon to user's pokemon
     * @return String winner user id
     * @throws BattleException
     */
    private String getBattleWinnerByRandomlyBaseStats(
            Battle battle, Pokemon fromUserPokemon, Pokemon toUserPokemon) throws BattleException {
        String winUserId = null;

        String fromUserId = battle.getFromUserId();
        String toUserId = battle.getToUserId();
        Battle existingBattle = getBattleWithTwoUsers(fromUserId, toUserId);

        if (existingBattle == null) {
            log.error(LOG_ERROR_NO_BATTLE_EXISTS, fromUserId, toUserId);
            throw new BattleException(EXCEPTION_NO_BATTLE_EXISTS);
        }

        Random random = new Random();
        Integer fromUserPokemonSpeed = random.nextInt(ONE_HANDURD);
        Integer toUserPokemonSpeed = random.nextInt(ONE_HANDURD);
        Integer fromUserPokemonHealth = random.nextInt(ONE_HANDURD);
        Integer toUserPokemonHealth = random.nextInt(ONE_HANDURD);
        Integer fromUserPokemonAttack = random.nextInt(ONE_HANDURD);
        Integer toUserPokemonAttack = random.nextInt(ONE_HANDURD);

        if (fromUserPokemonSpeed >= toUserPokemonSpeed) {
            toUserPokemonHealth -= fromUserPokemonAttack;
            if (toUserPokemonHealth <= 0) {
                winUserId = fromUserId;
            }
        } else {
            fromUserPokemonHealth -= toUserPokemonAttack;
            if (fromUserPokemonHealth <= 0) {
                winUserId = toUserId;
            }
        }
        return winUserId;
    }

    /**
     * Stores ID of Message that is pending.
     *
     * @param battle current battle
     * @param messageId message id that needs to be stored
     */
    public void addPendingMessageId(ObjectId battleId, String messageId) {
        Battle battle = battleRepository.get(battleId);
        if (battle.getPendingMessageIds().add(messageId)) {
            battleRepository.update(battle);
        }
    }

    /**
     * Removes ID of Message that is pending.
     *
     * @param battle current battle
     * @param messageId message id that needs to be removed
     */
    public void removePendingMessageId(ObjectId battleId, String messageId) {
        Battle battle = battleRepository.get(battleId);
        if (battle.getPendingMessageIds().remove(messageId)) {
            battleRepository.update(battle);
        }
    }
}
