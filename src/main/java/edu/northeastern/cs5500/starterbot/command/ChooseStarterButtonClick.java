package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.exception.InvalidPokedexNumberException;
import edu.northeastern.cs5500.starterbot.exception.UnauthorizedPokemonSelectionException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.utils.MessageUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

/** ChooseStarterButtonClick sets and confirms the starter pokemon a user has chosen. */
@Singleton
@Slf4j
public class ChooseStarterButtonClick implements ButtonClickHandler {

    BeginPlayingCommand beginPlayingCommand = new BeginPlayingCommand();

    private static final String BULBASAUR_NAME_LOWER = "bulbasaur";
    private static final String CHARMANDER_NAME_LOWER = "charmander";
    private static final String SQUIRTLE_NAME_LOWER = "squirtle";

    private static final String CHOOSE_BULBASAUR_IMAGE =
            "https://static.wikia.nocookie.net/legendsofthemultiuniverse/images/4/4c/001_bulbasaur_by_pklucario-d5z1g10.png/revision/latest?cb=20170701131534";
    private static final String CHOOSE_CHARMANDER_IMAGE =
            "https://seeklogo.com/images/C/charmander-logo-AC289C2CDD-seeklogo.com.png";
    private static final String CHOOSE_SQUIRTLE_IMAGE =
            "https://i.pinimg.com/originals/1b/47/3a/1b473a97ded04ac7c5b85eaf2baa5441.png";

    private static final String CHOOSE_BULBASAUR_EMOJI = "\ud83c\udf31";
    private static final String CHOOSE_CHARMANDER_EMOJI = "\ud83d\udd25";
    private static final String CHOOSE_SQUIRTLE_EMOJI = "\ud83d\udca6";
    private static final String SPARKLE_EMOJI = "\u2728";

    // This allows for a more natural messaging experience
    static final int POKEMON_ADDED_TO_PARTY_MESSAGE_DELAY = 1;

    private UserInventoryController userInventoryController;
    private PokemonController pokemonController;

    @Inject
    public ChooseStarterButtonClick(
            UserInventoryController userInventoryController, PokemonController pokemonController) {
        this.userInventoryController = userInventoryController;
        this.pokemonController = pokemonController;
    }

    /**
     * Gets the button click handler name.
     *
     * @return name - the name of the button click handler
     */
    @Override
    public String getName() {
        return "choose-starter";
    }

    /**
     * Sets pokemon chosen by the user and executes a direct message confirmation.
     *
     * @param event - a ButtonClickEvent, expected to be a "choose starter" button click
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onButtonClick(ButtonClickEvent event) {
        String discordUserId = event.getUser().getId();
        String chosenPokemonNumber = event.getButton().getId().split(":", 2)[1];
        log.info(
                String.format(
                        "Button click event: User with id %s chose %s!",
                        discordUserId, event.getButton().getLabel()));

        String pokemonName = "";
        try {
            pokemonName =
                    chooseStarterPokemon(discordUserId, Integer.parseInt(chosenPokemonNumber));
        } catch (UnauthorizedPokemonSelectionException e) {
            event.reply("Oops! You've already chosen a starter pokemon.").queue();
            return;
        }

        sendMessages(event, pokemonName);
    }

    /**
     * Executes the retrieval and addition of a starter pokemon to the user's party.
     *
     * @param discordUserId - the id of the discord user who initiated the button click event
     * @param pokemonNumber - the pokedex number of the chosen starter pokemon
     * @return a string representing the name of the chosen starter pokemon
     * @throws UnauthorizedPokemonSelectionException
     */
    String chooseStarterPokemon(String discordUserId, int pokemonNumber)
            throws UnauthorizedPokemonSelectionException {

        // Check if user is allowed to choose a starter
        if (!(userInventoryController.getPartyForUser(discordUserId).isEmpty())) {
            throw new UnauthorizedPokemonSelectionException(discordUserId);
        }

        // Create pokemon
        log.info(String.format("Getting pokemon number {} from database", pokemonNumber));
        Pokemon chosenStarterPokemon = pokemonController.newPokemonFromNumber(pokemonNumber);
        if (chosenStarterPokemon == null) {
            log.error("Unknown pokemon number chosen for starter: '{}'", pokemonNumber);
            // This is a runtime exception as it indicates a programming error occurred (source:
            // https://stackoverflow.com/a/499534/18546128)
            throw new InvalidPokedexNumberException(pokemonNumber);
        }

        // Add pokemon to user's inventory
        userInventoryController.addPokemonToUserParty(discordUserId, chosenStarterPokemon);
        log.info(
                String.format(
                        "Adding pokemon %s to user's party with user id: %s",
                        chosenStarterPokemon.getName(), discordUserId));

        return chosenStarterPokemon.getName();
    }

    /**
     * Sends the necessary messages to a new user after a starter pokemon is chosen, including a
     * confirmation and next steps.
     *
     * @param pokemonName - the name of the chosen pokemon
     * @param event - the ButtonClickEvent
     */
    @ExcludeFromJacocoGeneratedReport
    void sendMessages(ButtonClickEvent event, String pokemonName) {
        // Reply with messages confirming pokemon chosen
        event.reply(generateStarterChosenConfirmationMessage(pokemonName)).queue();
        MessageUtil.sendDirectMessage(
                event.getUser(),
                generatePokemonAddedConfirmationMessage(pokemonName),
                false,
                POKEMON_ADDED_TO_PARTY_MESSAGE_DELAY);

        // Send playing instructions
        beginPlayingCommand.sendBeginPlayingInstructionMessage(
                event.getUser(), POKEMON_ADDED_TO_PARTY_MESSAGE_DELAY);
    }

    /**
     * Generates a message confirming the starter pokemon chosen.
     *
     * @param pokemonName - the name of the chosen pokemon
     * @return a confirmation Message to be sent
     */
    Message generateStarterChosenConfirmationMessage(String pokemonName) {
        MessageBuilder messageBuilder = new MessageBuilder();

        String imageUrl = "";
        String emoji = "";

        switch (pokemonName.toLowerCase()) {
            case BULBASAUR_NAME_LOWER:
                imageUrl = CHOOSE_BULBASAUR_IMAGE;
                emoji = CHOOSE_BULBASAUR_EMOJI;
                break;
            case CHARMANDER_NAME_LOWER:
                imageUrl = CHOOSE_CHARMANDER_IMAGE;
                emoji = CHOOSE_CHARMANDER_EMOJI;
                break;
            case SQUIRTLE_NAME_LOWER:
                imageUrl = CHOOSE_SQUIRTLE_IMAGE;
                emoji = CHOOSE_SQUIRTLE_EMOJI;
                break;
            default:
                log.error("An unexpected starter pokemon name was given: %s", pokemonName);
                return messageBuilder.setContent("").build();
        }

        String description =
                String.format(
                        "%s%s You'll start your journey with %s! %s%s",
                        SPARKLE_EMOJI, emoji, pokemonName, SPARKLE_EMOJI, emoji);
        MessageEmbed embed =
                new EmbedBuilder().setDescription(description).setImage(imageUrl).build();

        return messageBuilder.setEmbeds(embed).build();
    }

    /**
     * Generates a message telling the user a pokemon has been added to their party.
     *
     * @param pokemonName - the name of the pokemon that was added to the party
     */
    Message generatePokemonAddedConfirmationMessage(String pokemonName) {
        Message message =
                new MessageBuilder()
                        .setContent(String.format("%s has been added to your party.", pokemonName))
                        .build();
        return message;
    }
}
