package edu.northeastern.cs5500.starterbot.listener;

import com.mongodb.lang.NonNull;
import edu.northeastern.cs5500.starterbot.command.ButtonClickHandler;
import edu.northeastern.cs5500.starterbot.command.SelectionMenuHandler;
import edu.northeastern.cs5500.starterbot.command.SlashCommandHandler;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@Slf4j
public class MessageListener extends ListenerAdapter {

    @Inject Set<SlashCommandHandler> commands;
    @Inject Set<ButtonClickHandler> buttons;
    @Inject Set<SelectionMenuHandler> selectionMenus;

    @Inject
    public MessageListener() {
        super();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        for (SlashCommandHandler command : commands) {
            if (command.getName().equals(event.getName())) {
                command.onSlashCommandEvent(event);
                return;
            }
        }
    }

    /**
     * This event is trigged when a button is clicked. onButtonClick is called with the event for
     * the appropriate button click handler.
     *
     * @param event - a ButtonClickEvent
     */
    @Override
    public void onButtonClick(@Nonnull ButtonClickEvent event) {
        String id = event.getButton().getId();
        String handlerName = id.split(":", 2)[0];
        log.info("onButtonClick: {}", id);

        for (ButtonClickHandler buttonClickHandler : buttons) {
            if (buttonClickHandler.getName().equals(handlerName)) {
                buttonClickHandler.onButtonClick(event);
                return;
            }
        }
        log.error("Unknown button handler: {}", handlerName);
    }

    /**
     * This event is trigged when an item in drop-down menu is clicked. onSelectionMenu is called
     * with the event for the appropriate selection menu handler.
     *
     * @param event - a SelectionMenuEvent
     */
    @Override
    public void onSelectionMenu(@NonNull SelectionMenuEvent event) {
        String id = event.getSelectedOptions().get(0).getValue();
        String handlerName = id.split(":", 2)[0];

        for (SelectionMenuHandler selectionMenuHandler : selectionMenus) {
            if (selectionMenuHandler.getName().equals(handlerName)) {
                selectionMenuHandler.onSelectionMenu(event);
                return;
            }
        }

        log.error("Unknown selection menu handler: {}", handlerName);
    }

    public Collection<CommandData> allCommandData() {
        return commands.stream()
                .map(SlashCommandHandler::getCommandData)
                .collect(Collectors.toList());
    }
}
