package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import java.util.ArrayList;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

/**
 * The command will return the current Party of Pokemon which is all the Pokemon that the user has.
 */
@Singleton
@Slf4j
public class ViewPartyCommand implements SlashCommandHandler {

    static final String PARTY_IMAGE =
            "https://www.pikpng.com/pngl/b/59-590092_pokeball-download-transparent-png-image-png-download-clipart.png";

    @Inject
    public ViewPartyCommand() {}

    @Inject UserInventoryController userInventoryController;
    @Inject PokemonController pokemonController;

    /** A constructor without injection, available for testing purposes. */
    public ViewPartyCommand(
            UserInventoryController userInventoryController, PokemonController pokemonController) {
        this.userInventoryController = userInventoryController;
        this.pokemonController = pokemonController;
    }

    /**
     * Returns the name of the command
     *
     * @return String - command name
     */
    @Override
    public String getName() {
        return "party";
    }

    /**
     * Get the command data of the command
     *
     * @return CommandData - the data contained in this command
     */
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "View the Pokemon you have");
    }

    /**
     * Creates and returns the embed for the party
     *
     * @return MessageEmbed - the embed triggered by the command
     */
    @Nonnull
    MessageEmbed viewParty(String discordUserId) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Your Pokemon Party");
        ArrayList<Pokemon> party = userInventoryController.getUserPokemons(discordUserId);
        embedBuilder.setImage(PARTY_IMAGE);
        // when the party is empty
        if (party.isEmpty()) {
            embedBuilder.setDescription("You have no Pokemon. Do you want to catch some?");
            return embedBuilder.build();
        }

        // when the party is not empty
        embedBuilder.setDescription(embedMessageBuilder(party));
        return embedBuilder.build();
    }

    /**
     * Return the embed message for the pokemon party that the user has
     *
     * @param party the pokemon party that the user has
     * @return the message string for embed
     */
    public static String embedMessageBuilder(ArrayList<Pokemon> party) {
        StringBuilder message = new StringBuilder();

        message.append("\n--------------------------------------------------------------------");

        int i = 1;
        for (Pokemon pokemon : party) {
            String str =
                    String.format(
                            "\nIndex: %s | Name: %s | Level: %s | Gender: %s",
                            i, pokemon.getName(), pokemon.getLevel(), pokemon.getGender());
            message.append(str);
            i++;
        }

        message.append("\n\nThese are all the Pokemon you have!");
        return message.toString();
    }

    /**
     * Creates the logic for when the event happens
     *
     * @param event - when the command is triggered
     */
    @Override
    public void onSlashCommandEvent(SlashCommandEvent event) {
        log.info("event: view the Party");
        MessageBuilder messageBuilder = new MessageBuilder();
        String discordUserId = event.getUser().getId();
        messageBuilder.setEmbeds(viewParty(discordUserId));
        event.reply(messageBuilder.build()).queue();
    }
}
