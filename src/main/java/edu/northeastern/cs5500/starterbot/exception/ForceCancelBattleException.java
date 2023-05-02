package edu.northeastern.cs5500.starterbot.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
/**
 * ForceCancelBattleException is the class that is used for conditions that a bot might want to
 * catch during confirmation stage in battle.
 *
 * <p>For example, if user happen to no longer possess the pokemon offered for battle we might want
 * to force cancel the battle. Otherwise, the battle won't be completed and both parties are locked
 * in battle which blocks them to create other battle offers.
 */
public class ForceCancelBattleException extends BattleException {

    public String fromUserId;
    public String toUserId;
    public String messageToFromUser;
    public String messageToToUser;

    public ForceCancelBattleException(
            String fromUserId, String toUserId, String messageToFromUser, String messageToToUser) {
        super("Unable to continue the battle");
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.messageToFromUser = messageToFromUser;
        this.messageToToUser = messageToToUser;
    }
}
