package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Trade;
import edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

/**
 * TradeButtonClickHandleCancel allows to process cancellation of the trade by any trade
 * participant.
 */
@Singleton
@Slf4j
public class TradeButtonClickHandleCancel implements ButtonClickHandler {
    @Inject TradeController tradeController;
    @Inject TradeMessageUtil msgUtil;

    @Inject
    public TradeButtonClickHandleCancel() {}

    /**
     * Gets the trade button click handler name.
     *
     * @return name - the name of the trade button click handler
     */
    @Override
    public String getName() {
        return "cancel_trade";
    }

    /**
     * Informs both users that the trade was canceled and ensures cancellation of the trade between
     * two users.
     *
     * @param event - a trade button click event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        log.info("{} clicked cancel button", event.getUser().getName());
        try {
            Trade trade = tradeController.cancelTrade(event.getUser().getId());
            log.info("Cancelled the trade {} after pushing cancel", trade.getId());
            event.reply("You canceled the trade").queue();
            String targetUserId;
            if (trade.getUser1Id().equals(event.getUser().getId())) {
                targetUserId = trade.getUser2Id();
            } else {
                targetUserId = trade.getUser1Id();
            }
            msgUtil.sendDM(
                            event.getJDA(),
                            trade,
                            targetUserId,
                            String.format(
                                    "%s has canceled the trade request",
                                    event.getUser().getAsMention()))
                    .queue();
            msgUtil.cleanupTradeMessages(event.getJDA(), trade);
        } catch (TradeException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
