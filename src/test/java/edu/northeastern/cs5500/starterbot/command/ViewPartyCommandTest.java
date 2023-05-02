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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ViewPartyCommandTest {
    static PokemonShowdownService pokemonShowdownService = null;
    static PokedexController pokedexController = null;
    static final String EXPECTED_STRING =
            "\n--------------------------------------------------------------------"
                    + "\nIndex: 1 | Name: Squirtle | Level: 1 | Gender: Female";

    @BeforeAll
    static void setupService() {
        // The service is readonly so it's fine to cache it
        pokemonShowdownService = new PokemonShowdownService();
        pokedexController =
                new PokedexController(new InMemoryRepository<>(), pokemonShowdownService);
    }

    @Test
    void testViewPartyCommand() {

        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        ViewPartyCommand viewPartyCommand =
                new ViewPartyCommand(userInventoryController, pokemonController);
        String name = viewPartyCommand.getName();
        CommandData commandData = viewPartyCommand.getCommandData();
        assertThat(name).isEqualTo(commandData.getName());
    }

    @Test
    void testViewPartyReturnsTheCorrectEmbedForEmptyParty() {
        String discordUserId = "23h5ikoqaehokljhaoe";
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);
        ViewPartyCommand viewPartyCommand =
                new ViewPartyCommand(userInventoryController, pokemonController);

        // generate the actual embed
        MessageEmbed actual = viewPartyCommand.viewParty(discordUserId);

        assertThat(actual.getTitle()).isNotEmpty();
        assertThat(actual.getDescription()).isNotEmpty();
        assertThat(actual.getImage().toString()).isNotEmpty();
    }

    @Test
    void testViewPartyReturnsTheCorrectEmbedForNonEmptyParty() {
        ArrayList<Pokemon> party = new ArrayList<>();
        Pokemon squirtle = PokemonControllerTest.generateSquirtle();

        party.add(squirtle);
        String actualMessage = ViewPartyCommand.embedMessageBuilder(party);
        assertThat(EXPECTED_STRING.equals(actualMessage));
    }
}
