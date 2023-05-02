package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
import edu.northeastern.cs5500.starterbot.exception.ForceCancelTradeException;
import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.Trade;
import edu.northeastern.cs5500.starterbot.utils.MessageUtil;
import edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

/** TradeButtonClickHandleConfirm allows to process confirmance of the trade. */
@Singleton
@Slf4j
@ExcludeClassFromJacocoGeneratedReport
public class TradeButtonClickHandleConfirm implements ButtonClickHandler {
    @Inject TradeController tradeController;
    @Inject PokemonController pokemonController;
    @Inject TradeMessageUtil msgUtil;

    @Inject
    public TradeButtonClickHandleConfirm() {}

    /**
     * Gets the trade button click handler name.
     *
     * @return name - the name of the trade button click handler
     */
    @Override
    public String getName() {
        return "confirm_trade";
    }

    /**
     * Creates a message which states the trade has been succesfully executed.
     *
     * @param aquiredPokemon pokemon that user will get as a result of trade
     * @return a String message
     */
    String renderTradeConfirmationMessage(Pokemon aquiredPokemon) {
        return String.format(
                "The trade has been completed. Have fun with your new %s!",
                aquiredPokemon.getName());
    }

    /**
     * Creates a message which informs one party that the other party has confirmed the trade.
     *
     * @param userAsMention a user that confirmed the trade offer
     * @return a String message
     * @throws TradeException
     */
    String renderOtherPartyConfirmedMessage(String userAsMention) throws TradeException {
        return String.format("%s has confirmed the trade.", userAsMention);
    }

    /**
     * Sends trade confirmation message
     *
     * @param event a trade button click event
     * @param trade current trade
     * @throws TradeException
     */
    @ExcludeFromJacocoGeneratedReport
    void sendTradeConfirmationMessages(ButtonClickEvent event, Trade trade) throws TradeException {
        Pokemon user1Pokemon = pokemonController.getPokemonById(trade.getUser1Offer());
        Pokemon user2Pokemon = pokemonController.getPokemonById(trade.getUser2Offer());
        // The condition to complete the trade is that two users confirmed the trade offer.
        // isUser1Closing checks if User1 was the second person who pushed confirmation button.
        Boolean isUser1Closing = event.getUser().getId().equals(trade.getUser1Id());
        event.reply(renderTradeConfirmationMessage(isUser1Closing ? user2Pokemon : user1Pokemon))
                .queue();
        event.getJDA()
                .retrieveUserById(isUser1Closing ? trade.getUser2Id() : trade.getUser1Id())
                .queue(
                        otherUser ->
                                MessageUtil.sendDirectMessage(
                                        otherUser,
                                        renderTradeConfirmationMessage(
                                                isUser1Closing ? user1Pokemon : user2Pokemon),
                                        false,
                                        0));
    }

    /**
     * Informs user that the trade has been confirmed
     *
     * @param event a trade button click event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        log.info("{} clicked confirm button", event.getUser().getName());
        try {
            try {
                Trade trade = tradeController.acceptTrade(event.getUser().getId());
                String otherUserId;
                if (trade.getUser1Id().equals(event.getUser().getId())) {
                    otherUserId = trade.getUser2Id();
                } else {
                    otherUserId = trade.getUser1Id();
                }

                if (trade.getUser1Accept() && trade.getUser2Accept()) {
                    trade = tradeController.completeTrade(trade.getId());
                    sendTradeConfirmationMessages(event, trade);
                } else {
                    event.reply("You have confirmed the trade.").queue();
                    msgUtil.sendDM(
                                    event.getJDA(),
                                    trade,
                                    otherUserId,
                                    renderOtherPartyConfirmedMessage(
                                            event.getUser().getAsMention()))
                            .queue();
                }
                event.getMessage().delete().queue();
            } catch (ForceCancelTradeException e) {
                Trade trade = tradeController.cancelTrade(e.getUser1Id());
                msgUtil.cleanupTradeMessages(event.getJDA(), trade);
                event.getJDA()
                        .retrieveUserById(e.getUser1Id())
                        .queue(
                                user ->
                                        MessageUtil.sendDirectMessage(
                                                user, e.getMessageToUser1(), false, 0));
                event.getJDA()
                        .retrieveUserById(e.getUser2Id())
                        .queue(
                                user ->
                                        MessageUtil.sendDirectMessage(
                                                user, e.getMessageToUser2(), false, 0));
                throw e;
            }
        } catch (TradeException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
