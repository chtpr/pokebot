package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import net.dv8tion.jda.api.entities.Message;
import org.junit.jupiter.api.Test;

public class BattleSelectionMenuHandlerTest {

    public static final String TITLE = "Title";
    public static final String DESCRIPTION = "Description";
    public static final String URL = "https://google.com/";
    public static final String USERNAME = "username";
    public static final String USER_MENTION = "user mention";
    public static final String MENTION = "mention";

    @Test
    void testNameMatchesData() {
        BattleSelectionMenuHandler battleSelectionMenuHandler = new BattleSelectionMenuHandler();
        String name = battleSelectionMenuHandler.getName();
        assertThat(name).isEqualTo(BattleSelectionMenuHandler.BATTLE);
    }

    @Test
    void testRenderPokemonMessage() {
        BattleSelectionMenuHandler battleSelectionMenuHandler = new BattleSelectionMenuHandler();
        Message message = battleSelectionMenuHandler.renderPokemonMessage(TITLE, DESCRIPTION, URL);
        assertThat(message.getActionRows()).isNotNull();
    }

    @Test
    void testRenderOwnChoiceConfirmation() {
        BattleSelectionMenuHandler battleSelectionMenuHandler = new BattleSelectionMenuHandler();
        Pokemon pokemon = new Pokemon();
        Message message = battleSelectionMenuHandler.renderOwnChoiceConfirmation(pokemon);
        assertThat(message.getActionRows()).isNotNull();
    }

    @Test
    void testRenderOthersChoiceConfirmation() {
        BattleSelectionMenuHandler battleSelectionMenuHandler = new BattleSelectionMenuHandler();
        Pokemon pokemon = new Pokemon();
        Message message =
                battleSelectionMenuHandler.renderOthersChoiceConfirmation(
                        pokemon, USERNAME, USER_MENTION);
        assertThat(message.getActionRows()).isNotNull();
    }

    @Test
    void testRenderFinalConfirmationMessage() {
        BattleSelectionMenuHandler battleSelectionMenuHandler = new BattleSelectionMenuHandler();
        String mention = MENTION;
        Pokemon primaryUserPokemon = new Pokemon();
        Pokemon otherUserPokemon = new Pokemon();
        Message message =
                battleSelectionMenuHandler.renderFinalConfirmationMessage(
                        mention, primaryUserPokemon, otherUserPokemon);
        assertThat(message.getActionRows()).isNotNull();
    }
}
