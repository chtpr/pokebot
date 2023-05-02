package edu.northeastern.cs5500.starterbot.utils;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.model.Trade;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.requests.RestAction;

/**
 * TradeMessageUtil is a utility class that allows for ease in sending a message during the trade to
 * a Discord user.
 */
@ExcludeClassFromJacocoGeneratedReport
@Singleton
public class TradeMessageUtil {

    @Inject TradeController tradeController;
    @Inject UserInventoryController inventoryController;
    @Inject PokemonController pokemonController;

    @Inject
    TradeMessageUtil() {}

    /**
     * Sends a DM message
     *
     * @param jda JDA instance of this interaction
     * @param trade current trade
     * @param targetUserId recipient of the message
     * @param message a message to be send
     */
    public RestAction<Message> sendDM(JDA jda, Trade trade, String targetUserId, Message message) {
        return jda.retrieveUserById(targetUserId)
                .flatMap(user -> user.openPrivateChannel())
                .flatMap(channel -> channel.sendMessage(message))
                .map(
                        msg -> {
                            tradeController.addPendingMessageId(trade.getId(), msg.getId());
                            return msg;
                        });
    }

    /**
     * Sends a DM message
     *
     * @param jda JDA instance of this interaction
     * @param trade current trade
     * @param targetUserId recipient of the message
     * @param messageString a message to be send
     */
    public RestAction<Message> sendDM(
            JDA jda, Trade trade, String targetUserId, String messageString) {
        return jda.retrieveUserById(targetUserId)
                .flatMap(user -> user.openPrivateChannel())
                .flatMap(channel -> channel.sendMessage(messageString))
                .map(
                        msg -> {
                            tradeController.addPendingMessageId(trade.getId(), msg.getId());
                            return msg;
                        });
    }

    /**
     * Removes irrelevant trade messages from the chat.
     *
     * @param jda JDA instance of this interaction
     * @param trade current trade
     */
    public void cleanupTradeMessages(JDA jda, Trade trade) {
        cleanupUserMessages(jda, trade.getUser1Id(), trade.getPendingMessageIds());
        cleanupUserMessages(jda, trade.getUser2Id(), trade.getPendingMessageIds());
    }

    /**
     * Removes irrelevant trade messages from the chat.
     *
     * @param jda JDA instance of this interaction
     * @param userId user ID
     * @param messageIds all IDs of messages existing in the chat that should be removed
     */
    private void cleanupUserMessages(JDA jda, String userId, Collection<String> messageIds) {
        jda.retrieveUserById(userId)
                .flatMap(user -> user.openPrivateChannel())
                .queue(
                        channel -> {
                            for (String messageId : messageIds) {
                                channel.deleteMessageById(messageId)
                                        .onErrorMap(_err -> null)
                                        .queue();
                            }
                        });
    }
}
