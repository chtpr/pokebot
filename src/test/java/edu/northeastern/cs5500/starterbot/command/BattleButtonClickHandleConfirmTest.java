package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BattleButtonClickHandleConfirmTest {

    public static final String TEST_USER = "testUser";

    static PokemonShowdownService pokemonShowdownService = null;
    static PokedexController pokedexController = null;

    @BeforeAll
    static void setupService() {
        pokemonShowdownService = new PokemonShowdownService();
        pokedexController =
                new PokedexController(new InMemoryRepository<>(), pokemonShowdownService);
    }

    @Test
    void testNameMatchesData() {
        BattleButtonClickHandleConfirm battleButtonClickHandleConfirm =
                new BattleButtonClickHandleConfirm();
        String name = battleButtonClickHandleConfirm.getName();
        assertThat(name).isEqualTo(BattleButtonClickHandleConfirm.CONFIRM_BATTLE);
    }

    @Test
    void testRenderBattleConfirmationMessage() {
        BattleButtonClickHandleConfirm battleButtonClickHandleConfirm =
                new BattleButtonClickHandleConfirm();
        Battle battle = new Battle();
        String message =
                battleButtonClickHandleConfirm.renderBattleConfirmationMessage(
                        battle, BattleButtonClickHandleConfirm.YOU_WIN);
        assertThat(message).isNotEmpty();
    }

    @Test
    void testRenderOtherPartyConfirmedMessage() throws BattleException {
        BattleButtonClickHandleConfirm battleButtonClickHandleConfirm =
                new BattleButtonClickHandleConfirm();
        String message = battleButtonClickHandleConfirm.renderOtherPartyConfirmedMessage(TEST_USER);
        assertThat(message).isNotEmpty();
    }
}
