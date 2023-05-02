package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.model.Battle;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

/** The battle command will start the battling process between two users. */
@Singleton
public class BattleCommand implements SlashCommandHandler {

    public static final String ACCEPT_BUTTON = "accept_battle";
    public static final String DECLINE_BUTTON = "decline_battle";
    public static final String CANCEL_BUTTON = "cancel_battle";
    public static final String CONFIRM_BUTTON = "confirm_battle";
    public static final String USER = "user";
    public static final String BATTLE = "battle";
    public static final String BATTLE_INVITE = "Battle Invite";
    public static final String BATTLE_AGAINST_YOUR_FRIENDS = "Battle Against Your Friends";
    public static final String BOT_SEND_A_BATTLE_INVITE_TO_USER =
            "The bot will send a battle invite to a selected user";
    public static final String INVITE_TO_A_BATTLE = "%s invites you to a battle.";
    public static final String NO_INTERESTS = "Not Interested.";
    public static final String LET_S_BATTLE = "Let's Battle!";
    public static final String BEEN_NOTIFIED_OF_BATTLE_INVITE =
            "%s has been notified of your battle invite.";

    @Inject BattleController battleController;

    @Inject
    public BattleCommand() {}

    /**
     * Gets the battle command name.
     *
     * @return battle
     */
    @Override
    public String getName() {
        return BATTLE;
    }

    /**
     * Gets command data
     *
     * @return Battle Against Your Friends in Command
     */
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), BATTLE_AGAINST_YOUR_FRIENDS)
                .addOptions(
                        new OptionData(OptionType.USER, USER, BOT_SEND_A_BATTLE_INVITE_TO_USER)
                                .setRequired(true));
    }

    /**
     * Generates an embed offering a battle
     *
     * @param fromUserId user who started the fight
     * @return a message embed
     */
    MessageEmbed renderBattleRequest(String fromUserId) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(BATTLE_INVITE);
        embedBuilder.setDescription(String.format(INVITE_TO_A_BATTLE, fromUserId));
        return embedBuilder.build();
    }

    /**
     * Generates a message offering a battle.
     *
     * @param fromUserId user who started the fight
     * @return a message to be sent
     */
    Message renderBattleMessage(String fromUserId) {
        MessageEmbed messageEmbed = renderBattleRequest(fromUserId);
        MessageBuilder messageBuilder = new MessageBuilder();
        Message message =
                messageBuilder
                        .setEmbeds(messageEmbed)
                        .setActionRows(
                                ActionRow.of(
                                        Button.success(ACCEPT_BUTTON, LET_S_BATTLE),
                                        Button.danger(CANCEL_BUTTON, NO_INTERESTS)))
                        .build();
        return message;
    }

    /**
     * Sends a message to confirm the battle request
     *
     * @param jda jda object of this event
     * @param battle battle
     */
    @ExcludeFromJacocoGeneratedReport
    private void sendConfirmBattleRequest(JDA jda, Battle battle) {
        jda.retrieveUserById(battle.getFromUserId())
                .queue(
                        fromUser -> {
                            Message message = renderBattleMessage(fromUser.getAsMention());
                            jda.retrieveUserById(battle.getToUserId())
                                    .flatMap(toUser -> toUser.openPrivateChannel())
                                    .flatMap(channel -> channel.sendMessage(message))
                                    .queue(
                                            msg ->
                                                    battleController.addPendingMessageId(
                                                            battle.getId(), msg.getId()));
                        });
    }

    /**
     * Processes the request for battle.
     *
     * @param event an event expected to be a SlashCommandEvent
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onSlashCommandEvent(SlashCommandEvent event) {
        String sourceUserId = event.getUser().getId();
        User targetUser = event.getOption(USER).getAsUser();
        String targetUserId = targetUser.getId();

        try {
            Battle battle = battleController.createBattle(sourceUserId, targetUserId);
            sendConfirmBattleRequest(event.getJDA(), battle);
            event.reply(String.format(BEEN_NOTIFIED_OF_BATTLE_INVITE, targetUser.getAsMention()))
                    .queue();
        } catch (BattleException battleException) {
            event.reply(battleException.getMessage()).queue();
        }
    }
}
