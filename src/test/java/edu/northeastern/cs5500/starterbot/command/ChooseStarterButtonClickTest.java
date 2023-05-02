package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonControllerTest;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.exception.UnauthorizedPokemonSelectionException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ChooseStarterButtonClickTest {

    static final String CHOSEN_POKEMON_NAME = "Squirtle";
    static final int CHOSEN_POKEMON_POKEDEX_NUM = 7;
    static final String USER_ID_1 = "350082976618512414";

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
    void testGenerateStarterChosenConfirmationMessage() {
        ChooseStarterButtonClick buttonClick = new ChooseStarterButtonClick(null, null);
        MessageEmbed embed =
                buttonClick
                        .generateStarterChosenConfirmationMessage(CHOSEN_POKEMON_NAME)
                        .getEmbeds()
                        .get(0);

        assertThat(embed.getDescription()).isNotEmpty();
        assertThat(embed.getImage().toString()).isNotEmpty();
    }

    @Test
    void testGeneratePokemonAddedConfirmationMessageSquirtle() {
        ChooseStarterButtonClick buttonClick = new ChooseStarterButtonClick(null, null);
        Message message = buttonClick.generatePokemonAddedConfirmationMessage(CHOSEN_POKEMON_NAME);

        assertThat(message.getContentRaw()).isNotEmpty();
    }

    @Test
    void testCannotChooseSecondStarterPokemon() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        ChooseStarterButtonClick buttonClick =
                new ChooseStarterButtonClick(userInventoryController, pokemonController);

        userInventoryController.addPokemonToUserParty(
                USER_ID_1, PokemonControllerTest.generateSquirtle());

        try {
            buttonClick.chooseStarterPokemon(USER_ID_1, CHOSEN_POKEMON_POKEDEX_NUM);
            fail("UnauthorizedPokemonSelectionException should have been thrown.");
        } catch (UnauthorizedPokemonSelectionException e) {
            assertThat(e.getMessage())
                    .isEqualTo(
                            String.format(
                                    "User with id %s is unauthorized to choose a starter pokemon at this time.",
                                    USER_ID_1));
            assertThat(userInventoryController.getPartyForUser(USER_ID_1).size()).isEqualTo(1);
        }
    }

    @Test
    void testChooseStarterPokemonAddsPokemonToParty() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        ChooseStarterButtonClick buttonClick =
                new ChooseStarterButtonClick(userInventoryController, pokemonController);

        try {
            buttonClick.chooseStarterPokemon(USER_ID_1, CHOSEN_POKEMON_POKEDEX_NUM);
        } catch (UnauthorizedPokemonSelectionException e) {
            fail("UnauthorizedPokemonSelectionException should NOT have been thrown.");
        }

        ObjectId pokemonObjectId = userInventoryController.getPartyForUser(USER_ID_1).get(0);
        Pokemon pokemonAdded = pokemonController.getPokemonById(pokemonObjectId);

        assertThat(userInventoryController.getPartyForUser(USER_ID_1).size()).isEqualTo(1);
        assertThat(pokemonAdded.getName()).isEqualTo(CHOSEN_POKEMON_NAME);
    }
}
