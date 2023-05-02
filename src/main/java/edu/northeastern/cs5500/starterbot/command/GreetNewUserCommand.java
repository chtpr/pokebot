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

/** The greet new user command sends a direct message to a user, welcoming them to the server. */
@Singleton
@Slf4j
public class GreetNewUserCommand implements GuildEventHandler {

    static final String WELCOME_NEW_USER_IMAGE_URL =
            "https://graphicxtreme.com/wp-content/uploads/2020/04/pokemon-banner-1000x500.jpg";

    @Inject
    public GreetNewUserCommand() {}

    MessageEmbed generateGreetUserEmbed() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Welcome!");
        embedBuilder.setDescription(
                "Hello new trainer, welcome to the TeamAlpaca Pokemon Server!\n\nHere you can experience the wonders of Pokemon in its simplest form by sticking to the basics: catching, trading, and battling.");
        embedBuilder.setImage(WELCOME_NEW_USER_IMAGE_URL);
        return embedBuilder.build();
    }

    /**
     * Executes a direct message greeting to the user who joined the server.
     *
     * @param genericEvent - a generic guild event, expected to be a GuildMemberJoinEvent
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onGuildEvent(GenericGuildEvent genericEvent) {
        GuildMemberJoinEvent event = (GuildMemberJoinEvent) genericEvent;
        log.info(
                String.format("Greeting new member with member ID: %s", event.getMember().getId()));
        MessageUtil.sendDirectMessage(event.getUser(), generateGreetUserEmbed(), false, 0);
    }
}
