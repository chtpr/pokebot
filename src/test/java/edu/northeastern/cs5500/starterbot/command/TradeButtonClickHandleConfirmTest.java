package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TradeButtonClickHandleConfirmTest {
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
        TradeButtonClickHandleConfirm tradeButtonClickHandleConfirm =
                new TradeButtonClickHandleConfirm();
        String name = tradeButtonClickHandleConfirm.getName();
        String expected = "confirm_trade";
        assertThat(name).isEqualTo(expected);
    }

    @Test
    void testRenderTradeConfirmationMessage() {
        TradeButtonClickHandleConfirm tradeButtonClickHandleConfirm =
                new TradeButtonClickHandleConfirm();
        Pokemon pokemon = new Pokemon();
        String message = tradeButtonClickHandleConfirm.renderTradeConfirmationMessage(pokemon);
        assertThat(message).isNotEmpty();
    }

    @Test
    void testRenderOtherPartyConfirmedMessage() throws TradeException {
        TradeButtonClickHandleConfirm tradeButtonClickHandleConfirm =
                new TradeButtonClickHandleConfirm();
        String mention = "@user";
        String message = tradeButtonClickHandleConfirm.renderOtherPartyConfirmedMessage(mention);
        assertThat(message).isNotEmpty();
    }
}
