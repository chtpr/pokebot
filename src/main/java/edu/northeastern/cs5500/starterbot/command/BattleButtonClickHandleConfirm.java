package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.exception.ForceCancelBattleException;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil;
import edu.northeastern.cs5500.starterbot.utils.MessageUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

/** BattleButtonClickHandleConfirm allows to process confirmance of the battle. */
@ExcludeClassFromJacocoGeneratedReport
@Singleton
@Slf4j
public class BattleButtonClickHandleConfirm implements ButtonClickHandler {

    public static final String CONFIRM_BATTLE = "confirm_battle";
    public static final String THE_BATTLE_HAS_BEEN_EXECUTED = "The battle has been executed. %s";
    public static final String THE_BATTLE_ENDS_UP_AS_A_TIE =
            "The battle has been executed and end up as a tie!";
    public static final String HAS_CONFIRMED_THE_BATTLE = "%s has confirmed the battle.";
    public static final String YOU_WIN = "You win!";
    public static final String YOU_LOSE = "You lose.";
    public static final String CLICKED_CONFIRM_BUTTON = "%s clicked confirm button";
    public static final String CONFIRMED_THE_BATTLE = "You have confirmed the battle.";

    @Inject BattleController battleController;
    @Inject BattleMessageUtil battleMessageUtil;

    @Inject
    public BattleButtonClickHandleConfirm() {}

    /**
     * Gets the battle button click handler name.
     *
     * @return name - the name of the battle button click handler
     */
    @Override
    public String getName() {
        return CONFIRM_BATTLE;
    }

    /**
     * Creates a message which states the battle has been succesfully executed and who will win.
     *
     * @param battle pokemon that user will get as a result of battle
     * @param winOrNotString win or lose
     * @return a String message
     */
    String renderBattleConfirmationMessage(Battle battle, String winOrNotString) {
        String winUserId = battle.getWinUserId();
        if (winUserId != null) {
            return String.format(THE_BATTLE_HAS_BEEN_EXECUTED, winOrNotString);
        }
        return String.format(THE_BATTLE_ENDS_UP_AS_A_TIE);
    }

    /**
     * Creates a message which informs one party that the other party has confirmed the battle.
     *
     * @param userAsMention a user that confirmed the battle offer
     * @return a String message
     * @throws BattleException
     */
    String renderOtherPartyConfirmedMessage(String userAsMention) throws BattleException {
        return String.format(HAS_CONFIRMED_THE_BATTLE, userAsMention);
    }

    /**
     * Sends battle confirmation message
     *
     * @param event a battle button click event
     * @param battle current battle
     * @throws BattleException
     */
    @ExcludeFromJacocoGeneratedReport
    void sendBattleConfirmationMessages(ButtonClickEvent event, Battle battle)
            throws BattleException {
        Boolean isFromUserClosing = event.getUser().getId().equals(battle.getFromUserId());
        Boolean isFromUserWin = event.getUser().getId().equals(battle.getWinUserId());
        event.reply(renderBattleConfirmationMessage(battle, isFromUserWin ? YOU_WIN : YOU_LOSE))
                .queue();
        event.getJDA()
                .retrieveUserById(isFromUserClosing ? battle.getToUserId() : battle.getFromUserId())
                .queue(
                        otherUser ->
                                MessageUtil.sendDirectMessage(
                                        otherUser,
                                        renderBattleConfirmationMessage(
                                                battle, isFromUserWin ? YOU_LOSE : YOU_WIN),
                                        false,
                                        0));
    }

    /**
     * Informs user that the battle has been confirmed
     *
     * @param event a battle button click event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        log.info(CLICKED_CONFIRM_BUTTON, event.getUser().getName());
        try {
            try {
                Battle battle = battleController.acceptBattle(event.getUser().getId());
                String otherUserId;
                if (battle.getFromUserId().equals(event.getUser().getId())) {
                    otherUserId = battle.getToUserId();
                } else {
                    otherUserId = battle.getFromUserId();
                }

                if (battle.getFromUserAccept() && battle.getToUserAccept()) {
                    battle = battleController.completeBattle(battle.getId());
                    sendBattleConfirmationMessages(event, battle);
                } else {
                    event.reply(CONFIRMED_THE_BATTLE).queue();
                    battleMessageUtil
                            .sendDM(
                                    event.getJDA(),
                                    battle,
                                    otherUserId,
                                    renderOtherPartyConfirmedMessage(
                                            event.getUser().getAsMention()))
                            .queue();
                }
                event.getMessage().delete().queue();
            } catch (ForceCancelBattleException e) {
                Battle battle = battleController.cancelBattle(e.getFromUserId());
                battleMessageUtil.cleanupBattleMessages(event.getJDA(), battle);
                event.getJDA()
                        .retrieveUserById(e.getFromUserId())
                        .queue(
                                user ->
                                        MessageUtil.sendDirectMessage(
                                                user, e.getMessageToFromUser(), false, 0));
                event.getJDA()
                        .retrieveUserById(e.getToUserId())
                        .queue(
                                user ->
                                        MessageUtil.sendDirectMessage(
                                                user, e.getMessageToToUser(), false, 0));
                throw e;
            }
        } catch (BattleException battleException) {
            event.reply(battleException.getMessage()).queue();
        }
    }
}
