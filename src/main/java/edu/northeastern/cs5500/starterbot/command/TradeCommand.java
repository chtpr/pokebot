package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Trade;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

/** The trade command will start the trading process between two users. */
@Singleton
@ExcludeClassFromJacocoGeneratedReport
public class TradeCommand implements SlashCommandHandler {
    @Inject TradeController tradeController;

    @Inject
    public TradeCommand() {}

    /**
     * Gets the trade command name.
     *
     * @return name - the name of trade command
     */
    @Override
    public String getName() {
        return "trade";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Trade pokemon with another trainer")
                .addOptions(
                        new OptionData(
                                        OptionType.USER,
                                        "user",
                                        "The bot will send a trade request to a selected user")
                                .setRequired(true));
    }

    /**
     * Generates an embed offering a trade.
     *
     * @param userMention - a user to me mentioned in the trade request
     * @return a message embed
     */
    MessageEmbed renderTradeRequest(String userMention) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Trade request");
        embedBuilder.setDescription(String.format("%s wants to trade with you!", userMention));
        return embedBuilder.build();
    }

    /**
     * Generates a message offering a trade.
     *
     * @param userMention - a user to me mentioned in the trade request
     * @return a message to be sent
     */
    Message renderTradeMessage(String userMention) {
        MessageEmbed embed = renderTradeRequest(userMention);
        MessageBuilder messageBuilder = new MessageBuilder();
        Message message =
                messageBuilder
                        .setEmbeds(embed)
                        .setActionRows(
                                ActionRow.of(
                                        Button.success("accept_trade", "Accept request"),
                                        Button.danger("decline_trade", "Decline request")))
                        .build();
        return message;
    }
    /**
     * Sends a message to confirm the trade request
     *
     * @param jda jda object of this event
     * @param trade current trade
     */
    @ExcludeFromJacocoGeneratedReport
    private void sendConfirmTradeStartRequest(JDA jda, Trade trade) {
        jda.retrieveUserById(trade.getUser1Id())
                .queue(
                        user1 -> {
                            Message message = renderTradeMessage(user1.getAsMention());
                            jda.retrieveUserById(trade.getUser2Id())
                                    .flatMap(user2 -> user2.openPrivateChannel())
                                    .flatMap(channel -> channel.sendMessage(message))
                                    .queue(
                                            msg ->
                                                    tradeController.addPendingMessageId(
                                                            trade.getId(), msg.getId()));
                        });
    }

    /**
     * Processes the request for trade.
     *
     * @param event - an event expected to be a SlashCommandEvent
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onSlashCommandEvent(SlashCommandEvent event) {
        String sourceUserId = event.getUser().getId();
        String targetUserId = event.getOption("user").getAsUser().getId();
        try {
            Trade trade = tradeController.beginTrade(sourceUserId, targetUserId);
            if (trade != null) {
                sendConfirmTradeStartRequest(event.getJDA(), trade);
                event.getJDA()
                        .retrieveUserById(targetUserId)
                        .queue(
                                user -> {
                                    event.reply(
                                                    String.format(
                                                            "Waiting for %s to accept request, the trade will continue in the DM with the Pokemon bot",
                                                            user.getAsMention()))
                                            .queue();
                                });
            }
        } catch (TradeException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
