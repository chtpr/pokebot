package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import net.dv8tion.jda.api.entities.Message;
import org.junit.jupiter.api.Test;

class TradeSelectionMenuHandlerTest {
    @Test
    void testNameMatchesData() {
        TradeSelectionMenuHandler tradeSelectionMenuHandler = new TradeSelectionMenuHandler();
        String name = tradeSelectionMenuHandler.getName();
        String expected = "trade";

        assertThat(name).isEqualTo(expected);
    }

    @Test
    void testRenderPokemonMessage() {
        String title = "Title";
        String description = "Description";
        String url = "https://google.com/";
        TradeSelectionMenuHandler tradeSelectionMenuHandler = new TradeSelectionMenuHandler();
        Message message = tradeSelectionMenuHandler.renderPokemonMessage(title, description, url);
        assertThat(message.getActionRows()).isNotNull();
    }

    @Test
    void testRenderOwnChoiceConfirmation() {
        TradeSelectionMenuHandler tradeSelectionMenuHandler = new TradeSelectionMenuHandler();
        Pokemon pokemon = new Pokemon();
        Message message = tradeSelectionMenuHandler.renderOwnChoiceConfirmation(pokemon);
        assertThat(message.getActionRows()).isNotNull();
    }

    @Test
    void testRenderOthersChoiceConfirmation() {
        TradeSelectionMenuHandler tradeSelectionMenuHandler = new TradeSelectionMenuHandler();
        Pokemon pokemon = new Pokemon();
        String sourceUserName = "username";
        String sourceUserMention = "user mention";
        Message message =
                tradeSelectionMenuHandler.renderOthersChoiceConfirmation(
                        pokemon, sourceUserName, sourceUserMention);
        assertThat(message.getActionRows()).isNotNull();
    }

    @Test
    void testRenderFinalConfirmationMessage() {
        TradeSelectionMenuHandler tradeSelectionMenuHandler = new TradeSelectionMenuHandler();
        String mention = "mention";
        Pokemon primaryUserPokemon = new Pokemon();
        Pokemon otherUserPokemon = new Pokemon();
        Message message =
                tradeSelectionMenuHandler.renderFinalConfirmationMessage(
                        mention, primaryUserPokemon, otherUserPokemon);
        assertThat(message.getActionRows()).isNotNull();
    }
}
