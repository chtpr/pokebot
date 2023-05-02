package edu.northeastern.cs5500.starterbot.model;

import java.util.ArrayList;
import lombok.Data;
import org.bson.types.ObjectId;

/** A place to store users' properties. For example: their Pokemon party */
@Data
public class UserInventory implements Model {
    ObjectId id;

    // This is the "snowflake id" of the user
    // e.g. event.getUser().getId()
    String discordUserId;

    // The Pokemon party that the user has
    ArrayList<ObjectId> party;
}
