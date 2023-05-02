package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.entities.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BattleButtonClickHandleAcceptTest {

    public static final String NO_EXCEPTION =
            "Expected BattleException, but no exception was thrown";

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
        BattleButtonClickHandleAccept battleButtonClickHandler =
                new BattleButtonClickHandleAccept();
        String name = battleButtonClickHandler.getName();
        assertThat(name).isEqualTo(BattleButtonClickHandleAccept.ACCEPT_BATTLE);
    }

    @Test
    void testRenderSelectionMessageUnsuccessfull() {
        try {
            List<Pokemon> collection = new ArrayList<>();
            BattleButtonClickHandleAccept handler = new BattleButtonClickHandleAccept();
            handler.renderSelectionMessage(collection);
            fail(NO_EXCEPTION);
        } catch (BattleException e) {
            assertThat(e.getMessage())
                    .isEqualTo(BattleButtonClickHandleAccept.NO_POKEMONS_TO_BATTLE);
        }
    }

    @Test
    void testEmbedMatchesRenderSelectionMessageEmbed() throws BattleException {
        PokemonController pokemonController =
                new PokemonController(new InMemoryRepository<>(), pokedexController);
        BattleButtonClickHandleAccept handler = new BattleButtonClickHandleAccept();
        Pokemon charmander = pokemonController.newPokemonFromNumber(4);
        List<Pokemon> collection = new ArrayList<>();
        collection.add(charmander);
        Message actual = handler.renderSelectionMessage(collection);
        assertThat(actual.getActionRows()).isNotEmpty();
    }
}
