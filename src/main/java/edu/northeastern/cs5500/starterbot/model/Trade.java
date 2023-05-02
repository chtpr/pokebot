package edu.northeastern.cs5500.starterbot.model;

import java.util.HashSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
/** A Trade is the class that represents a particular trade between two users. */
public class Trade implements Model {
    ObjectId id;

    // Trades are uniquely identified by user1Id and user2Id (in either order)
    // That is, there can only be one trade between any two users
    @Nonnull String user1Id;
    @Nonnull String user2Id;

    @Nonnull Boolean user2ConfirmedTrade;

    @Nullable ObjectId user1Offer = null;
    @Nullable ObjectId user2Offer = null;

    Boolean user1Accept = false;
    Boolean user2Accept = false;

    HashSet<String> pendingMessageIds = new HashSet<String>();
}
