package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonControllerTest;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InspectPokemonCommandTest {
    static PokemonShowdownService pokemonShowdownService = null;
    static PokedexController pokedexController = null;
    static final ObjectId FAKE_ID = new ObjectId();
    static final int VALID_INDEX = 1;
    static final int INVALID_INDEX_1 = 0;
    static final int INVALID_INDEX_2 = 4;
    static final int INVALID_INDEX_3 = -1;

    @BeforeAll
    static void setupService() {
        // The service is readonly so it's fine to cache it
        pokemonShowdownService = new PokemonShowdownService();
        pokedexController =
                new PokedexController(new InMemoryRepository<>(), pokemonShowdownService);
    }

    @Test
    void testInspectPokemonCommand() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        InspectPokemonCommand inspectPokemonCommand =
                new InspectPokemonCommand(userInventoryController, pokemonController);
        String name = inspectPokemonCommand.getName();
        CommandData commandData = inspectPokemonCommand.getCommandData();
        assertThat(commandData.getName()).isEqualTo(name);
    }

    @Test
    void testInspectPokemonReturnsTheCorrectEmbed() {
        String discordUserId = "23h5ikoqaehokljhaoe";

        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        InspectPokemonCommand inspectPokemonCommand =
                new InspectPokemonCommand(userInventoryController, pokemonController);

        Pokemon squirtle = PokemonControllerTest.generateSquirtle();
        pokemonRepository.add(squirtle);

        userInventoryController.addPokemonToUserParty(discordUserId, squirtle);

        // generate the actual embed
        MessageEmbed actual = inspectPokemonCommand.inspectPokemon(discordUserId, 1);

        assertThat(actual.getDescription()).isNotEmpty();
        assertThat(actual.getImage().toString()).isNotEmpty();
        assertThat(actual.getTitle()).isNotEmpty();
    }

    @Test
    void testInvalidMessageBuilderForSmallerIndex() {
        String expected = "Invalid index; must be 1 or greater!";
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        InspectPokemonCommand inspectPokemonCommand =
                new InspectPokemonCommand(userInventoryController, pokemonController);
        ArrayList<ObjectId> party = new ArrayList<>();
        party.add(FAKE_ID);
        assertThat(
                expected.equals(
                        inspectPokemonCommand.invalidMessageBuilder(INVALID_INDEX_1, party)));
    }

    @Test
    void testInvalidMessageBuilderForBiggerIndex() {
        String expected =
                String.format(
                        "You tried to inspect pokemon %d but you only have 1 pokemon!",
                        INVALID_INDEX_2);

        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        InspectPokemonCommand inspectPokemonCommand =
                new InspectPokemonCommand(userInventoryController, pokemonController);

        ArrayList<ObjectId> party = new ArrayList<>();
        party.add(FAKE_ID);
        assertThat(
                expected.equals(
                        inspectPokemonCommand.invalidMessageBuilder(INVALID_INDEX_2, party)));
    }

    @Test
    void testInvalidMessageBuilderForValidIndex() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        InspectPokemonCommand inspectPokemonCommand =
                new InspectPokemonCommand(userInventoryController, pokemonController);

        ArrayList<ObjectId> party = new ArrayList<>();
        party.add(FAKE_ID);
        assertThat(inspectPokemonCommand.invalidMessageBuilder(VALID_INDEX, party)).isNull();
    }

    @Test
    void testValidateIndex() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        InspectPokemonCommand inspectPokemonCommand =
                new InspectPokemonCommand(userInventoryController, pokemonController);

        ArrayList<ObjectId> party = new ArrayList<>();
        party.add(FAKE_ID);

        assertThat(inspectPokemonCommand.validateIndex(INVALID_INDEX_1, party)).isFalse();

        assertThat(inspectPokemonCommand.validateIndex(INVALID_INDEX_2, party)).isFalse();

        assertThat(inspectPokemonCommand.validateIndex(INVALID_INDEX_3, party)).isFalse();
    }
}
