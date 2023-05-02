package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.utils.MessageUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

/**
 * The greet returning user command sends a direct message to a returning user, welcoming them back
 * to the server.
 */
@Singleton
@Slf4j
public class GreetReturningUserCommand implements GuildEventHandler {

    static final String WELCOME_BACK_IMAGE_URL =
            "https://i0.wp.com/mynintendonews.com/wp-content/uploads/2014/08/ash_and_pikachu_pokemon.png?resize=930%2C620&ssl=1";

    @Inject
    public GreetReturningUserCommand() {}

    MessageEmbed generateGreetUserEmbed(String userName) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Welcome Back, Trainer!");
        embedBuilder.setDescription(
                String.format(
                        "Glad to see you're back, %s.\n\n Head to the '#play-game' channel to continue playing!",
                        userName));
        embedBuilder.setImage(WELCOME_BACK_IMAGE_URL);
        return embedBuilder.build();
    }

    /**
     * Executes a direct message greeting to the user who joined the server.
     *
     * @param genericEvent - a generic guild event, expected to be a GuildMemberJoinEvent
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onGuildEvent(GenericGuildEvent genericEvent) {
        GuildMemberJoinEvent event = (GuildMemberJoinEvent) genericEvent;
        log.info(
                String.format(
                        "Greeting returning member with member ID: %s", event.getMember().getId()));
        MessageUtil.sendDirectMessage(
                event.getUser(), generateGreetUserEmbed(event.getUser().getName()), false, 0);
    }
}
