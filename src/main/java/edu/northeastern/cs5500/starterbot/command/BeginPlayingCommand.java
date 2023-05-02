package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.utils.MessageUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

/**
 * The begin playing command sends a direct message to a user, telling them how and where to start
 * playing the game.
 */
@Singleton
@Slf4j
public class BeginPlayingCommand {

    private static final String BEGIN_PLAYING_IMAGE_URL =
            "https://c.tenor.com/rbx3ph5SLRUAAAAi/pikachu-pokemon.gif";

    private static final String CONGRATS_EMOJI = "\ud83c\udf89";

    // This allows for a more natural messaging experience
    static final int SEND_BEGIN_PLAYING_MESSAGE_DELAY = 2;

    @Inject
    public BeginPlayingCommand() {}

    MessageEmbed generateBeginPlayingEmbed() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Start Your Journey");
        embedBuilder.setDescription(
                String.format(
                        "Congrats, Trainer! %s \n\nYou are now ready to begin catching, trading, and battling pokemon with other trainers in the server.\n\n Proceed to the '#play-game' channel to begin playing. \n\nNote: Once there, all actions can be accomplished by typing '/' in the message bar followed by one of the suggested commands shown.\n\n Good luck!",
                        CONGRATS_EMOJI));
        embedBuilder.setImage(BEGIN_PLAYING_IMAGE_URL);
        return embedBuilder.build();
    }

    /**
     * Executes a direct message to the user, instructing them on how and where to begin playing the
     * game.
     *
     * @param discordUser - the new member to be sent the instruction message
     * @param previousDelay - any previous delay that should be considered before queuing up the
     *     next message
     */
    @ExcludeFromJacocoGeneratedReport
    public void sendBeginPlayingInstructionMessage(User discordUser, int previousDelay) {
        log.info(
                String.format(
                        "Begin playing instructions sent to new member with user ID: %s",
                        discordUser));
        final int nextMessageDelay = previousDelay + SEND_BEGIN_PLAYING_MESSAGE_DELAY;
        MessageUtil.sendDirectMessage(
                discordUser, generateBeginPlayingEmbed(), false, nextMessageDelay);
    }
}
