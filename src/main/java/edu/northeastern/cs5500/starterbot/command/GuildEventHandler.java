package edu.northeastern.cs5500.starterbot.command;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

/**
 * Guild event handler is an interface which enforces a consistent way to interact with specific
 * guild events.
 */
public interface GuildEventHandler {

    /**
     * Executes a procedure based on the generic guild event for that guild event.
     *
     * @param genericEvent - a generic guild event
     */
    public void onGuildEvent(@Nonnull GenericGuildEvent event);
}
