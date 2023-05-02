package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

/**
 * BattleButtonClickHandleCancel allows to process cancellation of the battle by any battle
 * participant.
 */
@Singleton
@Slf4j
public class BattleButtonClickHandleCancel implements ButtonClickHandler {

    public static final String CANCEL_BATTLE = "cancel_battle";
    public static final String CLICKED_CANCEL_BUTTON = "%s clicked cancel button";
    public static final String CANCELLED_THE_BATTLE =
            "Cancelled the battle %s after pushing cancel";
    public static final String YOU_CANCELED_THE_BATTLE = "You canceled the battle";
    public static final String CANCELED_BATTLE_REQUEST = "%s has canceled the battle request";

    @Inject BattleController battleController;
    @Inject BattleMessageUtil battleMessageUtil;

    @Inject
    public BattleButtonClickHandleCancel() {}

    /**
     * Gets the battle button click handler name.
     *
     * @return name - the name of the battle button click handler
     */
    @Override
    public String getName() {
        return CANCEL_BATTLE;
    }

    /**
     * Informs both users that the battle was canceled and ensures cancellation of the battle
     * between two users.
     *
     * @param event - a battle button click event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        log.info(CLICKED_CANCEL_BUTTON, event.getUser().getName());
        try {
            Battle battle = battleController.cancelBattle(event.getUser().getId());
            log.info(CANCELLED_THE_BATTLE, battle.getId());
            event.reply(YOU_CANCELED_THE_BATTLE).queue();
            String targetUserId;
            if (battle.getFromUserId().equals(event.getUser().getId())) {
                targetUserId = battle.getToUserId();
            } else {
                targetUserId = battle.getFromUserId();
            }
            battleMessageUtil
                    .sendDM(
                            event.getJDA(),
                            battle,
                            targetUserId,
                            String.format(CANCELED_BATTLE_REQUEST, event.getUser().getAsMention()))
                    .queue();
            battleMessageUtil.cleanupBattleMessages(event.getJDA(), battle);
        } catch (BattleException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
