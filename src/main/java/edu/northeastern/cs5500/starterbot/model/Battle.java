package edu.northeastern.cs5500.starterbot.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class Battle implements Model {
    // to look up the battle later
    ObjectId id;

    // user who started the fight
    @Nonnull String fromUserId;

    // user who was challenged
    @Nonnull String toUserId;

    // if false, not yet accepted by challenger
    // if true, the battle is in progress
    // if a challenger declines the battle, delete the battle object
    Boolean accepted = false;

    // a list of pokemon that belong to fromUser
    @Nullable ArrayList<ObjectId> fromUserPokemonList = null;

    // a list of pokemon that belong to toUser
    @Nullable ArrayList<ObjectId> toUserPokemonList = null;

    // battle only starts when both users have selected pokemon
    // once both users have selected pokemon start the battle loop
    ObjectId fromUserActivePokemonId = null;
    ObjectId toUserActivePokemonId = null;

    // when both users select moves, execute them and
    // then set both selections to null
    ObjectId fromUserSelectedMove = null;
    ObjectId toUserSelectedMove = null;

    Set<String> pendingMessageIds = new HashSet<>();

    @Nonnull Boolean toUserConfirmedBattle;

    Boolean fromUserAccept = false;
    Boolean toUserAccept = false;

    // user who gets to win
    String winUserId = null;
}
