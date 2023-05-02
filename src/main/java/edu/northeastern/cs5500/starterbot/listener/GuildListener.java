package edu.northeastern.cs5500.starterbot.listener;

import edu.northeastern.cs5500.starterbot.command.ChooseStarterCommand;
import edu.northeastern.cs5500.starterbot.command.GreetNewUserCommand;
import edu.northeastern.cs5500.starterbot.command.GreetReturningUserCommand;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/** The Guild Listener listens for guild member events. */
@Slf4j
public class GuildListener extends ListenerAdapter {

    @Inject GreetNewUserCommand greetNewUserCommand;
    @Inject ChooseStarterCommand chooseStarterCommand;
    @Inject UserInventoryController userInventoryController;
    @Inject GreetReturningUserCommand greetReturningUserCommand;

    @Inject
    public GuildListener() {
        super();
    }

    /**
     * This event is trigged when a member joins the server. The new or returning member is greeted
     * and new user is prompted to choose a starter pokemon.
     */
    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        log.info(
                String.format("Guild Member joined with member ID: %s", event.getMember().getId()));

        if (userInventoryController.getPartyForUser(event.getUser().getId()).isEmpty()) {
            greetNewUserCommand.onGuildEvent(event);
            chooseStarterCommand.onGuildEvent(event);
        } else {
            greetReturningUserCommand.onGuildEvent(event);
        }
    }

    /**
     * This event is triggered when a member leaves the server or is removed. Not currently used
     * other than to inform via logs when a user has left the server.
     */
    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        log.info(
                String.format(
                        "Guild Member left/removed with member ID: %s", event.getUser().getId()));
    }
}
