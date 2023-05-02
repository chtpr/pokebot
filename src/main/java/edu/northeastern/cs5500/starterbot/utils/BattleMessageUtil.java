package edu.northeastern.cs5500.starterbot.utils;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.model.Battle;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.requests.RestAction;

/**
 * BattleMessageUtil is a utility class that allows for ease in sending a message during the battle
 * to a Discord user.
 */
@Singleton
@ExcludeClassFromJacocoGeneratedReport
public class BattleMessageUtil {

    @Inject BattleController battleController;
    @Inject UserInventoryController userInventoryController;
    @Inject PokemonController pokemonController;

    @Inject
    BattleMessageUtil() {}

    /**
     * Sends a DM message
     *
     * @param jda JDA instance of this interaction
     * @param battle current battle
     * @param targetUserId recipient of the message
     * @param message a message to be send
     */
    @ExcludeFromJacocoGeneratedReport
    public RestAction<Message> sendDM(
            JDA jda, Battle battle, String targetUserId, Message message) {
        return jda.retrieveUserById(targetUserId)
                .flatMap(user -> user.openPrivateChannel())
                .flatMap(channel -> channel.sendMessage(message))
                .map(
                        msg -> {
                            battleController.addPendingMessageId(battle.getId(), msg.getId());
                            return msg;
                        });
    }

    /**
     * Sends a DM message
     *
     * @param jda JDA instance of this interaction
     * @param battle current battle
     * @param targetUserId recipient of the message
     * @param messageString a message to be send
     */
    @ExcludeFromJacocoGeneratedReport
    public RestAction<Message> sendDM(
            JDA jda, Battle battle, String targetUserId, String messageString) {
        return jda.retrieveUserById(targetUserId)
                .flatMap(user -> user.openPrivateChannel())
                .flatMap(channel -> channel.sendMessage(messageString))
                .map(
                        msg -> {
                            battleController.addPendingMessageId(battle.getId(), msg.getId());
                            return msg;
                        });
    }

    /**
     * Removes irrelevant battle messages from the chat.
     *
     * @param jda JDA instance of this interaction
     * @param battle current battle
     */
    @ExcludeFromJacocoGeneratedReport
    public void cleanupBattleMessages(JDA jda, Battle battle) {
        cleanupUserMessages(jda, battle.getFromUserId(), battle.getPendingMessageIds());
        cleanupUserMessages(jda, battle.getToUserId(), battle.getPendingMessageIds());
    }

    /**
     * Removes irrelevant battle messages from the chat.
     *
     * @param jda JDA instance of this interaction
     * @param userId user ID
     * @param messageIds all IDs of messages existing in the chat that should be removed
     */
    @ExcludeFromJacocoGeneratedReport
    private void cleanupUserMessages(JDA jda, String userId, Collection<String> messageIds) {
        jda.retrieveUserById(userId)
                .flatMap(user -> user.openPrivateChannel())
                .queue(
                        channel -> {
                            for (String messageId : messageIds) {
                                channel.deleteMessageById(messageId).queue();
                            }
                        });
    }
}
