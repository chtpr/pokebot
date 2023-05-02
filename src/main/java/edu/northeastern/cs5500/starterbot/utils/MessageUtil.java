package edu.northeastern.cs5500.starterbot.utils;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

/** MessageUtil is a utility class that allows for ease in sending a message to a discord user. */
@ExcludeClassFromJacocoGeneratedReport
public class MessageUtil {

    /**
     * Sends a direct message to a user via a private channel.
     *
     * @param user - the user to be messaged
     * @param message - the message to be sent
     * @param sendTyping - a boolean which represents whether discord's "X is typing..." feature
     *     should be displayed
     * @param delay - the number of seconds that the message should be delayed before being sent
     */
    public static void sendDirectMessage(
            User user, Message message, boolean sendTyping, int delay) {
        sendDirectMessage(user, channel -> channel.sendMessage(message), sendTyping, delay);
    }

    /**
     * Sends a direct message embed to a user via a private channel.
     *
     * @param user - the user to be messaged
     * @param embed - the message embed to be sent
     * @param sendTyping - a boolean which represents whether discord's "X is typing..." feature
     *     should be displayed
     * @param delay - the number of seconds that the message embed should be delayed before being
     *     sent
     */
    public static void sendDirectMessage(
            User user, MessageEmbed embed, boolean sendTyping, int delay) {
        sendDirectMessage(user, channel -> channel.sendMessageEmbeds(embed), sendTyping, delay);
    }

    /**
     * @param user - the user to be messaged
     * @param messageString - the String message to be sent
     * @param sendTyping - a boolean which represents whether discord's "X is typing..." feature
     *     should be displayed
     * @param delay - the number of seconds that the message embed should be delayed before being
     *     sent
     */
    public static void sendDirectMessage(
            User user, String messageString, boolean sendTyping, int delay) {
        Message message = (new MessageBuilder()).setContent(messageString).build();
        sendDirectMessage(user, message, sendTyping, delay);
    }

    /**
     * A helper function which executes the sending of a private message to a user.
     *
     * @param user - the user to be messaged
     * @param sendMessage - the function that will be used to send the message
     * @param sendTyping - a boolean which represents whether discord's "X is typing..." feature
     *     should be displayed
     * @param delay - the number of seconds that the message embed should be delayed before being
     *     sent
     */
    private static void sendDirectMessage(
            User user,
            Function<PrivateChannel, MessageAction> sendMessage,
            boolean sendTyping,
            int delay) {

        final RestAction<PrivateChannel> privateChannel = user.openPrivateChannel();
        if (sendTyping) privateChannel.flatMap(channel -> channel.sendTyping()).queue();

        privateChannel.flatMap(sendMessage).queueAfter(Math.max(delay, 0), TimeUnit.SECONDS);
    }
}
