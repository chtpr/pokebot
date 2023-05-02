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

/** BattleButtonClickHandleDecline allows to process decline of the battle request. */
@Singleton
@Slf4j
public class BattleButtonClickHandleDecline implements ButtonClickHandler {

    public static final String DECLINE_BATTLE = "decline_battle";
    public static final String CLICKED_DECLINE_BUTTON = "%s clicked decline button";
    public static final String THE_BATTLE_HAS_BEEN_CANCELLED =
            "The battle %s has been cancelled, %s declined the battle";
    public static final String YOU_DECLINED_BATTLE_INVITE = "You declined the battle invite";
    public static final String OHTER_PARTY_DECLINED_BATTLE_INVITE =
            "%s has declined the battle invite";

    @Inject BattleController battleController;
    @Inject BattleMessageUtil battleMessageUtil;

    @Inject
    public BattleButtonClickHandleDecline() {}

    /**
     * Gets the battle button click handler name.
     *
     * @return name - the name of the battle button click handler
     */
    @Override
    public String getName() {
        return DECLINE_BATTLE;
    }

    /**
     * Informs both users that the battle was declined and ensures cancellation of the battle
     * between two users.
     *
     * @param event - a battle button click event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        log.info(CLICKED_DECLINE_BUTTON, event.getUser().getName());
        try {
            Battle battle = battleController.cancelBattle(event.getUser().getId());
            log.info(THE_BATTLE_HAS_BEEN_CANCELLED, battle, event.getUser().getName());
            event.reply(YOU_DECLINED_BATTLE_INVITE).queue();
            battleMessageUtil
                    .sendDM(
                            event.getJDA(),
                            battle,
                            battle.getFromUserId(),
                            String.format(
                                    OHTER_PARTY_DECLINED_BATTLE_INVITE,
                                    event.getUser().getAsMention()))
                    .queue();
            battleMessageUtil.cleanupBattleMessages(event.getJDA(), battle);
        } catch (BattleException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
