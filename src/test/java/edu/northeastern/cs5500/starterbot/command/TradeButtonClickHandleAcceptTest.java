package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.entities.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TradeButtonClickHandleAcceptTest {
    static PokemonShowdownService pokemonShowdownService = null;
    static PokedexController pokedexController = null;

    @BeforeAll
    static void setupService() {
        // The service is readonly so it's fine to cache it
        pokemonShowdownService = new PokemonShowdownService();
        pokedexController =
                new PokedexController(new InMemoryRepository<>(), pokemonShowdownService);
    }

    @Test
    void testNameMatchesData() {
        TradeButtonClickHandleAccept tradeButtonClickHandler = new TradeButtonClickHandleAccept();
        String name = tradeButtonClickHandler.getName();
        String expected = "accept_trade";
        assertThat(name).isEqualTo(expected);
    }

    @Test
    void testRenderSelectionMessageUnsuccessfull() {
        try {
            List<Pokemon> collection = new ArrayList<>();
            TradeButtonClickHandleAccept handler = new TradeButtonClickHandleAccept();
            handler.renderSelectionMessage(collection);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage())
                    .isEqualTo(
                            "Unable to start trade, one of the parties doesn't have any pokemons in their inventory.");
        }
    }

    @Test
    void testEmbedMatchesRenderSelectionMessageEmbed() throws TradeException {
        PokemonController pokemonController =
                new PokemonController(new InMemoryRepository<>(), pokedexController);
        TradeButtonClickHandleAccept handler = new TradeButtonClickHandleAccept();
        Pokemon charmander = pokemonController.newPokemonFromNumber(4);
        List<Pokemon> collection = new ArrayList<>();
        collection.add(charmander);
        Message actual = handler.renderSelectionMessage(collection);
        assertThat(actual.getActionRows()).isNotEmpty();
    }
}
