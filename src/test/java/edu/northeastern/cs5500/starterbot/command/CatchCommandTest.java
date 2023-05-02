package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CatchCommandTest {
    static PokemonShowdownService pokemonShowdownService = null;
    static PokedexController pokedexController = null;
    static final int NUMBER_OF_CATCH_RELEASE_BUTTONS = 2;
    static final int NUMBER_OF_ACTION_ROWS = 1;

    @BeforeAll
    static void setupService() {
        // The service is readonly so it's fine to cache it
        pokemonShowdownService = new PokemonShowdownService();
        pokedexController =
                new PokedexController(new InMemoryRepository<>(), pokemonShowdownService);
    }

    @Test
    void testNameMatchesData() {
        CatchCommand catchCommand = new CatchCommand(null);
        String name = catchCommand.getName();
        CommandData commandData = catchCommand.getCommandData();
        assertThat(name).isEqualTo(commandData.getName());
    }

    @Test
    void testCatchPokemon() {
        CatchCommand catchCommand = new CatchCommand(pokedexController);
        String username = "chtpr";

        Message message = catchCommand.catchPokemon(username);
        MessageEmbed embed = message.getEmbeds().get(0);

        assertThat(message.getActionRows().size()).isEqualTo(NUMBER_OF_ACTION_ROWS);
        assertThat(message.getButtons().size()).isEqualTo(NUMBER_OF_CATCH_RELEASE_BUTTONS);

        assertThat(embed.getTitle()).isNotEmpty();
        assertThat(embed.getDescription()).isNotEmpty();
        assertThat(embed.getFields()).isNotEmpty();
        assertThat(embed.getImage().toString()).isNotEmpty();
    }
}
