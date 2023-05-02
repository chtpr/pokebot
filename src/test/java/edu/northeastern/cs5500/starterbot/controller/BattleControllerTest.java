package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

public class BattleControllerTest {

    public static final String NO_BATTLE_EXCEPTION =
            "Expected BattleException, but no exception was thrown";
    public static final String USER_1_ID = "23h5ikoqaehokljhaoe";
    public static final String USER_2_ID = "2kjfksdjdkhokljhaoe";
    public static final String USER_3_ID = "29kjfksdjdkhokljhaoe";

    static PokemonShowdownService pokemonShowdownService = new PokemonShowdownService();
    static PokedexController pokedexController =
            new PokedexController(new InMemoryRepository<PokedexEntry>(), pokemonShowdownService);
    static PokemonController pokemonController =
            new PokemonController(new InMemoryRepository<>(), pokedexController);
    static UserInventoryController userInventoryController =
            new UserInventoryController(new InMemoryRepository<>());

    private BattleController getBattleController() {
        BattleController battleController =
                new BattleController(
                        new InMemoryRepository<Battle>(),
                        pokemonController,
                        userInventoryController);
        return battleController;
    }

    @Test
    void testCannotBattleWithSelf() {
        try {
            BattleController battleController = getBattleController();
            battleController.createBattle(USER_1_ID, USER_1_ID);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.EXCEPTION_CANNOT_BATTLE_YOURSELF);
        }
    }

    @Test
    void testCannotBattleFromUserGotNoParty() {
        try {
            BattleController battleController = getBattleController();
            List<ObjectId> user2Party =
                    battleController.userInventoryController.getPartyForUser(USER_2_ID);
            assertThat(user2Party).isNotNull();
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            assertThat(battle).isNotNull();
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.EXCEPTION_NO_POKEMONS_TO_BATTLE);
        }
    }

    @Test
    void testCannotBattleToUserGotNoParty() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list = new ArrayList<>();
            ObjectId object = new ObjectId();
            list.add(object);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list);
            List<ObjectId> user1Party =
                    battleController.userInventoryController.getPartyForUser(USER_1_ID);
            assertThat(user1Party).isNotNull();
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            assertThat(battle).isNotNull();
        } catch (BattleException e) {
            assertThat(e.getMessage())
                    .isEqualTo(BattleController.EXCEPTION_FRIEND_NO_POKEMONS_TO_BATTLE);
        }
    }

    @Test
    void testCreateBattleSuccess() throws BattleException {
        BattleController battleController = getBattleController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
        Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
        assertThat(battle).isNotNull();
        ArrayList<Battle> list = battleController.getAllBattles();
        assertThat(list).isNotEmpty();
    }

    @Test
    void testGetNotFoundBattle() {
        BattleController battleController = getBattleController();
        assertThat(battleController.getBattleWithOneUser(USER_1_ID)).isNull();
    }

    @Test
    void testSuccessfullyGetBattle1() throws BattleException {
        BattleController battleController = getBattleController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
        Battle expectedBattle = battleController.createBattle(USER_1_ID, USER_2_ID);
        Battle actualBattle = battleController.getBattleWithOneUser(USER_1_ID);
        assertThat(expectedBattle).isEqualTo(actualBattle);
    }

    @Test
    void testSuccessfullyGetBattle2() throws BattleException {
        BattleController battleController = getBattleController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
        Battle expectedBattle = battleController.createBattle(USER_1_ID, USER_2_ID);
        Battle actualBattle = battleController.getBattleWithOneUser(USER_2_ID);
        assertThat(expectedBattle).isEqualTo(actualBattle);
    }

    @Test
    void testAcceptBattleRequestOnBattleNotExist() throws BattleException {
        try {
            BattleController battleController = getBattleController();
            battleController.acceptBattleRequest(USER_1_ID);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.EXCEPTION_NO_BATTLE_EXISTS);
        }
    }

    @Test
    void testAcceptBattleRequestSuccess() throws BattleException {
        BattleController battleController = getBattleController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
        Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
        assertThat(battle).isNotNull();
        battleController.acceptBattleRequest(USER_2_ID);
    }

    @Test
    void testChoosePokemonOnNonexistBattle() {
        try {
            BattleController battleController = getBattleController();
            ObjectId pokemon = new ObjectId();
            battleController.choosePokemonToBattle(USER_1_ID, pokemon);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage())
                    .isEqualTo(BattleController.EXCEPTION_THIS_BATTLE_IS_NOT_ACTIVE);
        }
    }

    @Test
    void testChoosePokemonOnNotConfirmedBattle() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            assertThat(battle).isNotNull();
            battleController.choosePokemonToBattle(USER_2_ID, object2);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage())
                    .isEqualTo(BattleController.EXCEPTION_THE_BATTLE_HASN_T_STARTED);
        }
    }

    @Test
    void testChoosePokemonToBattleSuccess() throws BattleException {
        BattleController battleController = getBattleController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
        Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
        battle.setToUserConfirmedBattle(true);
        assertThat(battle).isNotNull();
        battleController.choosePokemonToBattle(USER_1_ID, object2);
    }

    @Test
    void testAcceptNonExistBattle() {
        try {
            BattleController battleController = getBattleController();
            ObjectId pokemon = new ObjectId();
            assertThat(pokemon).isNotNull();
            battleController.acceptBattle(USER_1_ID);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.EXCEPTION_NO_BATTLE_EXISTS);
        }
    }

    @Test
    void testFromUserAcceptsBattleAgain() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            battle.setFromUserAccept(true);
            battleController.acceptBattle(USER_1_ID);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage())
                    .isEqualTo(BattleController.EXCEPTION_ALREADY_ACCEPTED_BATTLE);
        }
    }

    @Test
    void testToUserAcceptsBattleAgain() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            battle.setToUserAccept(true);
            battleController.acceptBattle(USER_2_ID);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage())
                    .isEqualTo(BattleController.EXCEPTION_ALREADY_ACCEPTED_BATTLE);
        }
    }

    @Test
    void testAcceptBattleSuccess() throws BattleException {
        BattleController battleController = getBattleController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
        Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
        battle.setFromUserAccept(true);
        battleController.acceptBattle(USER_2_ID);
    }

    @Test
    void testCancelNotExistBattle() {
        try {
            BattleController battleController = getBattleController();
            ObjectId pokemon = new ObjectId();
            assertThat(pokemon).isNotNull();
            battleController.cancelBattle(USER_1_ID);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.THE_BATTLE_IS_NOT_ACTIVE);
        }
    }

    @Test
    void testCancelBattleSuccess() throws BattleException {
        BattleController battleController = getBattleController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
        Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
        battle.setFromUserAccept(true);
        battleController.cancelBattle(USER_2_ID);
    }

    @Test
    void testCannotCompleteBattleWhichNotExist() {
        try {
            BattleController battleController = getBattleController();
            ObjectId battleId = new ObjectId();
            battleController.completeBattle(battleId);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.THE_BATTLE_IS_NOT_ACTIVE);
        }
    }

    @Test
    void testCannotCompleteBattleFromUserPokemonMissing() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            battle.setFromUserActivePokemonId(null);
            battleController.completeBattle(battle.getId());
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.THE_BATTLE_IS_NOT_ACTIVE);
        }
    }

    @Test
    void testCannotCompleteBattleToUserPokemonMissing() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            battle.setToUserActivePokemonId(null);
            battleController.completeBattle(battle.getId());
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.THE_BATTLE_IS_NOT_ACTIVE);
        }
    }

    @Test
    void testCannotCompleteBattleMissingConfirmation() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            battle.setFromUserActivePokemonId(object1);
            battle.setToUserActivePokemonId(object2);
            battle.setToUserAccept(false);
            battleController.completeBattle(battle.getId());
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isEqualTo(BattleController.THE_BATTLE_IS_NOT_ACTIVE);
        }
    }

    @Test
    void testCannotCompleteBattleUser1NoLongerHavePokemon() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            ObjectId battleId = battle.getId();
            battle.setFromUserActivePokemonId(object1);
            battle.setToUserActivePokemonId(object2);
            battle.setFromUserAccept(true);
            battle.setToUserAccept(true);
            list1.clear();
            battleController.completeBattle(battleId);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    void testCannotCompleteBattleUser2NoLongerHavePokemon() {
        try {
            BattleController battleController = getBattleController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
            Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
            ObjectId battleId = battle.getId();
            battle.setFromUserActivePokemonId(object1);
            battle.setToUserActivePokemonId(object2);
            battle.setFromUserAccept(true);
            battle.setToUserAccept(true);
            list2.clear();
            battleController.completeBattle(battleId);
            fail(NO_BATTLE_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    void testCompleteBattleSuccess() throws BattleException {
        BattleController battleController = getBattleController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        battleController.userInventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        battleController.userInventoryController.setPartyForUser(USER_2_ID, list2);
        Battle battle = battleController.createBattle(USER_1_ID, USER_2_ID);
        ObjectId battleId = battle.getId();
        battle.setFromUserActivePokemonId(object1);
        battle.setToUserActivePokemonId(object2);
        battle.setFromUserAccept(true);
        battle.setToUserAccept(true);
        battleController.completeBattle(battleId);
        assertThat(battleController.getBattleWithOneUser(USER_1_ID)).isNull();
    }
}
