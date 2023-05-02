package edu.northeastern.cs5500.starterbot.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
/**
 * ForceCancelTradeException is the class that is used for conditions that a bot might want to catch
 * during confirmation stage in trade. For example, if user happen to no longer possess the pokemon
 * offered for trade we might want to force cancel the trade. Otherwise, the trade won't be
 * completed and both parties are locked in trade which blocks them to create other trade offers.
 */
public class ForceCancelTradeException extends TradeException {

    public String user1Id;
    public String user2Id;
    public String messageToUser1;
    public String messageToUser2;

    public ForceCancelTradeException(
            String user1Id, String user2Id, String messageToUser1, String messageToUser2) {
        super("Unable to continue the trade");
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.messageToUser1 = messageToUser1;
        this.messageToUser2 = messageToUser2;
    }
}
