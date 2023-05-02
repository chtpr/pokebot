package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryControllerTest;
import edu.northeastern.cs5500.starterbot.exception.InvalidPokemonIndexException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DeletePokemonCommandTest {

    static PokemonShowdownService pokemonShowdownService = null;
    static PokedexController pokedexController = null;

    @BeforeAll
    static void setupService() {
        // The service is readonly so it's fine to cache it
        pokemonShowdownService = new PokemonShowdownService();
        pokedexController =
                new PokedexController(new InMemoryRepository<>(), pokemonShowdownService);
    }

    static final String USER_ID_1 = "350082976618512414";

    @Test
    void testNameMatchesData() {
        DeletePokemonCommand deletePokemonCommand = new DeletePokemonCommand(null, null);
        String name = deletePokemonCommand.getName();
        CommandData commandData = deletePokemonCommand.getCommandData();

        assertThat(name).isEqualTo(commandData.getName());
    }

    @Test
    void testCommandDataHasExpectedFields() {
        DeletePokemonCommand deletePokemonCommand = new DeletePokemonCommand(null, null);
        CommandData commandData = deletePokemonCommand.getCommandData();

        assertThat(commandData.getOptions()).isNotNull();
        assertThat(commandData.getName()).isNotEmpty();
        assertThat(commandData.getDescription()).isNotEmpty();
    }

    @Test
    void validateIndexGivenPreventsPartyFromBecomingEmptyThrowsException() {
        DeletePokemonCommand deletePokemonCommand = new DeletePokemonCommand(null, null);
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        // Add object id to party
        ArrayList<ObjectId> party = new ArrayList<>();
        party.add(new ObjectId());
        userInventoryController.setPartyForUser(USER_ID_1, party);

        try {
            deletePokemonCommand.validateIndexGiven(1, party);
            fail("InvalidPokemonIndexException should have been thrown.");
        } catch (InvalidPokemonIndexException e) {
            assertThat(e.getMessage())
                    .isEqualTo("You only have one pokemon. You cannot delete your last pokemon.");
        }
    }

    @Test
    void validateIndexGivenIndexCannotBeZeroThrowsException() {
        DeletePokemonCommand deletePokemonCommand = new DeletePokemonCommand(null, null);
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        // Add object ids to party
        ArrayList<ObjectId> party = new ArrayList<>();
        party.add(new ObjectId());
        party.add(new ObjectId());
        userInventoryController.setPartyForUser(USER_ID_1, party);

        try {
            deletePokemonCommand.validateIndexGiven(0, party);
            fail("InvalidPokemonIndexException should have been thrown.");
        } catch (InvalidPokemonIndexException e) {
            assertThat(e.getMessage()).isEqualTo("Invalid index; must be 1 or greater!");
        }
    }

    @Test
    void validateIndexGivenIndexGreaterThanPartySizeThrowsException() {
        DeletePokemonCommand deletePokemonCommand = new DeletePokemonCommand(null, null);
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        // Add object ids to party
        ArrayList<ObjectId> party = new ArrayList<>();
        party.add(new ObjectId());
        party.add(new ObjectId());
        userInventoryController.setPartyForUser(USER_ID_1, party);

        try {
            deletePokemonCommand.validateIndexGiven(3, party);
            fail("InvalidPokemonIndexException should have been thrown.");
        } catch (InvalidPokemonIndexException e) {
            assertThat(e.getMessage())
                    .isEqualTo(
                            String.format(
                                    "You tried to delete pokemon 3 but you only have %d pokemon!",
                                    party.size()));
        }
    }

    @Test
    void validateIndexGivenAllValidationChecksPass() throws InvalidPokemonIndexException {
        DeletePokemonCommand deletePokemonCommand = new DeletePokemonCommand(null, null);
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        // Add object ids to party
        ArrayList<ObjectId> party = new ArrayList<>();
        party.add(new ObjectId());
        party.add(new ObjectId());
        userInventoryController.setPartyForUser(USER_ID_1, party);

        deletePokemonCommand.validateIndexGiven(1, party);
    }

    @Test
    void testRemovePokemonFromParty() {
        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        DeletePokemonCommand deletePokemonCommand =
                new DeletePokemonCommand(userInventoryController, pokemonController);

        // Add a bulbasaur (this one will be deleted)
        Pokemon bulbasaur = pokemonController.newPokemonFromNumber(1);
        ObjectId objectIdToDelete = bulbasaur.getId();
        UserInventoryControllerTest.addPokemonIdToUserPartyForTesting(
                userInventoryController, USER_ID_1, bulbasaur.getId());

        // Add a squirtle
        Pokemon squirtle = pokemonController.newPokemonFromNumber(7);
        UserInventoryControllerTest.addPokemonIdToUserPartyForTesting(
                userInventoryController, USER_ID_1, squirtle.getId());

        // Remove pokemon
        ArrayList<ObjectId> party =
                userInventoryController.getInventoryForMemberId(USER_ID_1).getParty();
        deletePokemonCommand.removePokemonFromParty(party, USER_ID_1, objectIdToDelete);

        assertThat(userInventoryController.getPartyForUser(USER_ID_1))
                .doesNotContain(objectIdToDelete);
        assertThat(userInventoryController.getPartyForUser(USER_ID_1).size()).isEqualTo(1);
    }
}
