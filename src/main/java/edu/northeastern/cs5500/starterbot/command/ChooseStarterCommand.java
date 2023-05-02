package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.utils.MessageUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

/**
 * The choose starter command sends an interactive direct message to a user, prompting them to
 * choose a starter pokemon.
 */
@Singleton
@Slf4j
public class ChooseStarterCommand implements GuildEventHandler {

    private static final String BULBASAUR_NAME = "Bulbasaur";
    private static final String CHARMANDER_NAME = "Charmander";
    private static final String SQUIRTLE_NAME = "Squirtle";

    private static final int BULBASAUR_NUMBER = 1;
    private static final int CHARMANDER_NUMBER = 4;
    private static final int SQUIRTLE_NUMBER = 7;

    private static final String GREEN_CIRCLE_EMOJI = "\ud83d\udfe2";
    private static final String PURPLE_CIRCLE_EMOJI = "\ud83d\udfe3";
    private static final String RED_CIRCLE_EMOJI = "\ud83d\udd34";
    private static final String BLUE_CIRCLE_EMOJI = "\ud83d\udd35";
    private static final String CAUTION_EMOJI = "\u26a0\ufe0f";

    private static final String CHOOSE_STARTER_ID = "choose-starter:%d";
    private static final String CHOOSE_STARTER_IMAGE_URL =
            "https://pvplive.net/wp-content/uploads/2021/01/2x1_PokemonPRBYPoll_v03.ad0fb582e7aa96d0b609fd9e3453fc3c79ec3364-7245868.jpg";

    // This delay gives the user time to read the greeting message before the choose pokemon message
    // appears
    static final int MESSAGE_DELAY_IN_SECONDS = 8;

    @Inject
    public ChooseStarterCommand() {}

    /**
     * Generates a static message prompting the user to choose a starter pokemon.
     *
     * @return the choose starter pokemon Message to be sent
     */
    Message generateChooseStarterMessage() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Choose Your Starter Pokemon");
        embedBuilder.setDescription(
                String.format(
                        "Which starter do you wish to start your journey with?\n\nFrom left to right:\n\nBulbasaur, a Grass %s /Poison %s type Pokemon\nCharmander, a Fire %s type Pokemon\nSquirtle, a Water %s type Pokemon\n\n %s Your decision is permanent, so choose carefully! %s",
                        GREEN_CIRCLE_EMOJI,
                        PURPLE_CIRCLE_EMOJI,
                        RED_CIRCLE_EMOJI,
                        BLUE_CIRCLE_EMOJI,
                        CAUTION_EMOJI,
                        CAUTION_EMOJI));
        embedBuilder.setImage(CHOOSE_STARTER_IMAGE_URL);
        MessageEmbed embed = embedBuilder.build();

        MessageBuilder messageBuilder = new MessageBuilder();
        Message buttonMessage =
                messageBuilder
                        .setEmbeds(embed)
                        .setActionRows(
                                ActionRow.of(
                                        Button.success(
                                                String.format(CHOOSE_STARTER_ID, BULBASAUR_NUMBER),
                                                BULBASAUR_NAME),
                                        Button.danger(
                                                String.format(CHOOSE_STARTER_ID, CHARMANDER_NUMBER),
                                                CHARMANDER_NAME),
                                        Button.primary(
                                                String.format(CHOOSE_STARTER_ID, SQUIRTLE_NUMBER),
                                                SQUIRTLE_NAME)))
                        .build();
        return buttonMessage;
    }

    /**
     * Executes a direct message to the user who joined the server, prompting them to choose a
     * starter pokemon by clicking one of the buttons in the message.
     *
     * @param genericEvent - a generic guild event, expected to be a GuildMemberJoinEvent
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onGuildEvent(GenericGuildEvent genericEvent) {
        GuildMemberJoinEvent event = (GuildMemberJoinEvent) genericEvent;
        log.info(
                String.format(
                        "Choose starter message sent to user with user ID: %s",
                        event.getMember().getId()));
        MessageUtil.sendDirectMessage(
                event.getUser(), generateChooseStarterMessage(), true, MESSAGE_DELAY_IN_SECONDS);
    }
}
