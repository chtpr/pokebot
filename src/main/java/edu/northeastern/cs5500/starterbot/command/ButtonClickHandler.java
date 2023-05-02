package edu.northeastern.cs5500.starterbot.command;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

/**
 * Button click handler is an interface which enforces a consistent way to interact with button
 * click events.
 */
public interface ButtonClickHandler {

    /**
     * Gets the button click handler name.
     *
     * @return name - the name of the button click handler
     */
    @Nonnull
    public String getName();

    /**
     * Executes a procedure based on the button click event for that button click handler.
     *
     * @param event - a button click event
     */
    public void onButtonClick(@Nonnull ButtonClickEvent event);
}
