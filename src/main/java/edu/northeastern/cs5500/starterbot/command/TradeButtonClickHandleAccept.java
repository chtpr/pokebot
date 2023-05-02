package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.Trade;
import edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

/** TradeButtonClickHandleAccept allows to process acceptance of the trade request. */
@Singleton
@Slf4j
public class TradeButtonClickHandleAccept implements ButtonClickHandler {
    @Inject TradeController tradeController;
    @Inject UserInventoryController inventoryController;
    @Inject PokemonController pokemonController;
    @Inject TradeMessageUtil msgUtil;

    @Inject
    public TradeButtonClickHandleAccept() {}

    /**
     * Gets the trade button click handler name.
     *
     * @return name - the name of the trade button click handler
     */
    @Override
    public String getName() {
        return "accept_trade";
    }

    /**
     * Creates a message with selection menu of pokemons.
     *
     * @param userPokemons pokemons this user possesses
     * @return message
     * @throws TradeException
     */
    Message renderSelectionMessage(Collection<Pokemon> userPokemons) throws TradeException {
        if (userPokemons.isEmpty()) {
            throw new TradeException(
                    "Unable to start trade, one of the parties doesn't have any pokemons in their inventory.");
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Please choose your pokemon to trade");
        MessageEmbed embed = embedBuilder.build();
        MessageBuilder messageBuilder = new MessageBuilder();
        SelectionMenu.Builder menuBuilder =
                SelectionMenu.create("select-pokemon-dropdown")
                        .setPlaceholder("Choose your pokemon to trade");
        for (Pokemon pokemon : userPokemons) {
            menuBuilder.addOption(pokemon.getName(), "trade:" + pokemon.getId().toHexString());
        }
        SelectionMenu menu = menuBuilder.build();
        return messageBuilder
                .setEmbeds(embed)
                .setActionRows(
                        ActionRow.of(menu),
                        ActionRow.of(Button.danger("cancel_trade", "Cancel trade")))
                .build();
    }

    /**
     * Informs users that the trade begins and sends both users an embed with choose pokemon request
     *
     * @param event - a trade button click event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        log.info("{} clicked accept button", event.getUser().getName());
        try {
            // Prepare all the data
            Trade trade = tradeController.acceptTradeRequest(event.getUser().getId());
            Message dm1 =
                    renderSelectionMessage(inventoryController.getUserPokemons(trade.getUser1Id()));
            Message dm2 =
                    renderSelectionMessage(inventoryController.getUserPokemons(trade.getUser2Id()));
            // Send out messages
            event.reply("Initiating the trade...")
                    .setEphemeral(true)
                    .queue(
                            _msg -> {
                                msgUtil.sendDM(event.getJDA(), trade, trade.getUser1Id(), dm1)
                                        .queue();
                                msgUtil.sendDM(event.getJDA(), trade, trade.getUser2Id(), dm2)
                                        .queue();
                            });
            // Remove the trade request message as the user has responded
            tradeController.removePendingMessageId(trade.getId(), event.getMessage().getId());
            event.getMessage().delete().queue();
        } catch (TradeException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
