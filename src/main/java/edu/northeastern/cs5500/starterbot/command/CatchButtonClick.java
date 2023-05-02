package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

@Singleton
@Slf4j
public class CatchButtonClick implements ButtonClickHandler {
    @Inject UserInventoryController userInventoryController;
    @Inject PokemonController pokemonController;

    @Inject
    public CatchButtonClick() {}

    /**
     * Gets the button click handler name.
     *
     * @return name - the name of the button click handler
     */
    @Override
    public String getName() {
        return "keep-release";
    }

    /**
     * Executes keeping or releasing the Pokemon. If kept, the Pokemon will be created and added to
     * the user party. If released, a Pokemon is never created and the user will receive a message
     * say they have released the Pokemon.
     *
     * @param event - a button click event, expected to be a "keep or release" button click
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String discordUserId = event.getUser().getId();

        String buttonLabel = event.getButton().getLabel();
        String caughtPokemonNumber = event.getButton().getId().split(":", 2)[1];
        log.info(
                String.format(
                        "Button click event: User with id %s chose %s!",
                        discordUserId, buttonLabel));

        switch (buttonLabel) {
            case CatchCommand.KEEP_BUTTON:
                // Create pokemon
                log.info(
                        String.format(
                                "Getting pokemon number %s from database", caughtPokemonNumber));
                Pokemon caughtPokemon =
                        pokemonController.newPokemonFromNumber(
                                Integer.parseInt(caughtPokemonNumber));
                if (caughtPokemon == null) {
                    log.error("Unknown pokemon number: '{}'", caughtPokemonNumber);
                    event.reply("Sorry, due to a bug, that pokemon got away!").queue();
                    return;
                }
                // Add pokemon to user's inventory
                userInventoryController.addPokemonToUserParty(discordUserId, caughtPokemon);
                log.info(
                        String.format(
                                "Adding pokemon %s to user's party with user id: %s",
                                caughtPokemon.getName(), discordUserId));

                event.reply(pokemonJoinedPartyMessage(caughtPokemon)).queue();

                break;
            case CatchCommand.RELEASE_BUTTON:
                event.reply(pokemonReleasedMessage()).queue();
                break;
            default:
                log.error("Unknown button label on catch {}", buttonLabel);
                event.reply("Sorry, an unexpected error occured.").queue();
                break;
        }
    }

    /**
     * Generates a message telling the user a pokemon has been released.
     *
     * @return the message
     */
    Message pokemonReleasedMessage() {
        Message message = new MessageBuilder().setContent("Pokemon has been released").build();
        return message;
    }

    /**
     * Generates a message telling the user a pokemon has joined their party.
     *
     * @param pokemon the pokemon
     * @return the message
     */
    Message pokemonJoinedPartyMessage(Pokemon pokemon) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(String.format("%s has joined your party!", pokemon.getName()));
        embedBuilder.addField("Type: ", pokemon.getPokemonTypes().get(0), true);
        if (pokemon.getPokemonTypes().size() > 1) {
            embedBuilder.addField("Secondary Type: ", pokemon.getPokemonTypes().get(1), true);
        }
        embedBuilder.addField("Gender: ", pokemon.getGender(), true);
        embedBuilder.addField("Level: ", String.valueOf(pokemon.getLevel()), true);
        MessageEmbed embed = embedBuilder.build();
        MessageBuilder messageBuilder = new MessageBuilder();
        Message message = messageBuilder.setEmbeds(embed).build();
        return message;
    }
}
