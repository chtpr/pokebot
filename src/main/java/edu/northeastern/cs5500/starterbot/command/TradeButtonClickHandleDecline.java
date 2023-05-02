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

/** TradeButtonClickHandleDecline allows to process decline of the trade request. */
@Singleton
@Slf4j
public class TradeButtonClickHandleDecline implements ButtonClickHandler {
    @Inject TradeController tradeController;
    @Inject TradeMessageUtil msgUtil;

    @Inject
    public TradeButtonClickHandleDecline() {}

    /**
     * Gets the trade button click handler name.
     *
     * @return name - the name of the trade button click handler
     */
    @Override
    public String getName() {
        return "decline_trade";
    }

    /**
     * Informs both users that the trade was declined and ensures cancellation of the trade between
     * two users.
     *
     * @param event - a trade button click event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        log.info("{} clicked decline button", event.getUser().getName());
        try {
            Trade trade = tradeController.cancelTrade(event.getUser().getId());
            log.info(
                    "The trade {} has been cancelled, {} declined the trade",
                    trade.getId(),
                    event.getUser().getName());
            event.reply("You declined the trade request").queue();
            msgUtil.sendDM(
                            event.getJDA(),
                            trade,
                            trade.getUser1Id(),
                            String.format(
                                    "%s has declined the trade request",
                                    event.getUser().getAsMention()))
                    .queue();
            msgUtil.cleanupTradeMessages(event.getJDA(), trade);
        } catch (TradeException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
