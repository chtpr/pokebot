package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

/**
 * The catch command will return a random pokemon from the list of catchable pokemon and let the
 * user know that they caught it.
 */
@Singleton
@Slf4j
public class CatchCommand implements SlashCommandHandler {
    public static final String KEEP_BUTTON = "Keep";
    public static final String RELEASE_BUTTON = "Release";

    PokedexController pokedexController;

    @Inject
    public CatchCommand(PokedexController pokedexController) {
        this.pokedexController = pokedexController;
    }

    /** Gets the name of the slash command */
    @Override
    public String getName() {
        return "catch";
    }

    /** Gets the command data of the slash command */
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Catch a random pokemon");
    }

    /**
     * Generates a message that shows the user a randomly caught Pokemon. Pokedex controller is used
     * to get a random Pokemon, and then if the user decides to keep it, the Pokemon will be created
     * and added to the user party through the onButtonClick event. If the user decides to release
     * it, a Pokemon is never created and the user will receive a message say they have released the
     * Pokemon.
     *
     * @param event the SlashCommandEvent used to provide username
     * @return the message that shows the user a randomly caught Pokemon
     */
    Message catchPokemon(String discordUsername) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        PokedexEntry entry = pokedexController.getRandomPokedexEntry();
        embedBuilder.setTitle(
                String.format("You, %s, caught a %s!", discordUsername, entry.getName()));
        embedBuilder.setDescription(
                String.format("Would you like to keep or release the %s?", entry.getName()));
        embedBuilder.addField("Type", entry.getPokemonTypes().get(0), false);
        log.info(String.format("Types: %s", entry.getPokemonTypes().get(0)));
        if (entry.getPokemonTypes().size() > 1) {
            embedBuilder.addField("Secondary Type", entry.getPokemonTypes().get(1), false);
        }
        embedBuilder.setImage(entry.getImage());

        MessageEmbed embed = embedBuilder.build();
        MessageBuilder messageBuilder = new MessageBuilder();
        Message message =
                messageBuilder
                        .setEmbeds(embed)
                        .setActionRows(
                                ActionRow.of(
                                        Button.primary(
                                                String.format(
                                                        "keep-release:%d",
                                                        entry.getPokedexNumber()),
                                                KEEP_BUTTON),
                                        Button.danger(
                                                String.format("keep-release:%d", 0),
                                                RELEASE_BUTTON)))
                        .build();
        return message;
    }

    /**
     * Executes the initial catch event.
     *
     * @param event - a slash command event, expected to be a "/catch"
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onSlashCommandEvent(SlashCommandEvent event) {
        log.info("event: /catch completed");
        String discordUsername = event.getUser().getName();
        event.reply(catchPokemon(discordUsername)).queue();
    }
}
