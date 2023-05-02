package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

/** Controls the behavior of the userInventory repo, and behaviors of the userInventories in it. */
@Slf4j
@Singleton
public class UserInventoryController {

    private GenericRepository<UserInventory> userInventoryRepository;
    @Inject PokemonController pokemonController;

    @Inject
    public UserInventoryController(GenericRepository<UserInventory> userInventoryRepository) {
        this.userInventoryRepository = userInventoryRepository;
    }

    /**
     * Get inventory for a particular user by discordMemberId If the user doesn't have an existing
     * inventory, create a new one for them The new inventory should have an empty party
     *
     * @param discordMemberId - the user's ID on discord
     * @return the according inventory for the user
     */
    @Nonnull
    public UserInventory getInventoryForMemberId(String discordMemberId) {
        Collection<UserInventory> userInventories = userInventoryRepository.getAll();
        for (UserInventory currentUserInventory : userInventories) {
            if (currentUserInventory.getDiscordUserId().equals(discordMemberId)) {
                return currentUserInventory;
            }
        }

        UserInventory userInventory = new UserInventory();
        userInventory.setDiscordUserId(discordMemberId);
        userInventory.setParty(new ArrayList<ObjectId>());
        userInventoryRepository.add(userInventory);
        return userInventory;
    }

    /**
     * Get the Pokemon party for a particular user by discordMemberId
     *
     * @param discordMemberId - the user ID on discord
     * @return the Pokemon party for that user
     */
    @Nonnull
    public ArrayList<ObjectId> getPartyForUser(String discordMemberId) {
        return getInventoryForMemberId(discordMemberId).getParty();
    }

    /**
     * Set the Pokemon party for a particular user
     *
     * @param discordMemberId - the user ID on discord
     * @param party - the new Pokemon party to be set
     */
    public void setPartyForUser(String discordMemberId, ArrayList<ObjectId> party) {
        UserInventory userInventory = getInventoryForMemberId(discordMemberId);
        userInventory.setParty(party);
        userInventoryRepository.update(userInventory);
    }

    /**
     * Adds a pokemon to the given user's party.
     *
     * <p>If attempting to add a duplicate pokemon (same ObjectId) it will be ignored.
     *
     * @param discordMemberId - the discord ID of the target member
     * @param pokemon - the pokemon to be added to the party
     */
    public void addPokemonToUserParty(String discordMemberId, Pokemon pokemon) {
        ArrayList<ObjectId> currentParty = getPartyForUser(discordMemberId);

        long duplicatePartyMemberIds =
                currentParty.stream()
                        .filter(partyMemberId -> partyMemberId.equals(pokemon.getId()))
                        .count();

        if (duplicatePartyMemberIds > 0) {
            log.debug("Attempted to add duplicate pokemon with ObjectId: " + pokemon.getId());
            return;
        }

        currentParty.add(pokemon.getId());
        setPartyForUser(discordMemberId, currentParty);
    }

    /**
     * Gets pokemons this user currently has
     *
     * @param userId id of the user
     * @return list of pokemons
     */
    public ArrayList<Pokemon> getUserPokemons(String userId) {
        ArrayList<Pokemon> result = new ArrayList<>();
        for (ObjectId pokemonId : this.getPartyForUser(userId)) {
            result.add(pokemonController.getPokemonById(pokemonId));
        }
        return result;
    }
}
