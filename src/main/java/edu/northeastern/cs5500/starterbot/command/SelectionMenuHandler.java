package edu.northeastern.cs5500.starterbot.command;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

/**
 * SelectionMenuHandler is an interface which enforces a consistent way to interact with selection
 * menu events.
 */
public interface SelectionMenuHandler {

    /**
     * Gets the selection menu handler name.
     *
     * @return name - the name of the selection menu handler
     */
    @Nonnull
    public String getName();

    /**
     * Executes a procedure based on the the selection menu event for that selection menu handler.
     *
     * @param event - a selection menu event
     */
    public void onSelectionMenu(@Nonnull SelectionMenuEvent event);
}
