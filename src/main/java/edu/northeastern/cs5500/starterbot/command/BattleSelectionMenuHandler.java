package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.RestAction;
import org.bson.types.ObjectId;

/** BattleSelectionMenuHandler allows to process users choice of pokemons to battle */
@Singleton
@Slf4j
@ExcludeClassFromJacocoGeneratedReport
public class BattleSelectionMenuHandler implements SelectionMenuHandler {

    public static final String BATTLE = "battle";
    public static final String BATTLE_PANEL = "Your battle Panel";
    public static final String YOU_CHOSEN_LEVEL_POKEMON = "You've chosen Level %s %s";
    public static final String OTHER_PARTY_BATTLE_PANEL = "%s 's battle panel";
    public static final String CHOSEN_LEVEL_POKEMON = "%s has chosen Level %s %s";
    public static final String CONFIRM_BATTLE = "Please confirm the battle";
    public static final String POKEMON_VS_POKEMON =
            "Your Level %s pokemon %s will be fight with %s's Level %s pokemon %s";
    public static final String CANCEL_BATTLE_ID = "cancel_battle";
    public static final String CANCEL_BATTLE_LABLE = "Cancel battle";
    public static final String CONFIRM_BATTLE_LABLE = "Confirm battle";
    public static final String CONFIRM_BATTLE_ID = "confirm_battle";
    public static final String USERS_OFFERS = "User 1 offered %s, user 2 offered %s";
    public static final String SENDING_OUT_FINAL_CONFIRMATION_MESSAGES =
            "All selections are made, sending out final confirmation messages";
    public static final String PLEASE_SELECT_A_POKEMON = "Please select a pokemon";

    @Inject BattleController battleController;
    @Inject PokemonController pokemonController;
    @Inject BattleMessageUtil battleMessageUtil;

    @Inject
    public BattleSelectionMenuHandler() {}

    /**
     * Gets the battle selection menu handler name.
     *
     * @return name - the name of the battle selection menu handler
     */
    @Override
    public String getName() {
        return BATTLE;
    }

    /**
     * Creates an embed with pokemon a party chose for trading
     *
     * @param title title of the embed
     * @param description description of the embed
     * @param imageURL a URL of pokemon image
     * @return a message
     */
    Message renderPokemonMessage(String title, String description, String imageURL) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(description);
        embedBuilder.setImage(imageURL);
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setEmbeds(embedBuilder.build());
        return messageBuilder.build();
    }

    /**
     * Creates a message user will get when choose their own pokemon to battle. This message will
     * contain the name and image of their choice.
     *
     * @param pokemon a pokemon user wants to exchange
     * @return a message
     */
    Message renderOwnChoiceConfirmation(Pokemon pokemon) {
        return renderPokemonMessage(
                BATTLE_PANEL,
                String.format(YOU_CHOSEN_LEVEL_POKEMON, pokemon.getLevel(), pokemon.getName()),
                pokemon.getImage());
    }

    /**
     * Creates a message user will get when other party their own pokemon to battle. This message
     * will contain the name and image of their choice.
     *
     * @param pokemon a pokemon the other user in battle wants to fight
     * @param sourceUserName a name of the other user in battle
     * @param sourceUserMention a name of the other user in battle as mention
     * @return a message
     */
    Message renderOthersChoiceConfirmation(
            Pokemon pokemon, String sourceUserName, String sourceUserMention) {
        return renderPokemonMessage(
                String.format(OTHER_PARTY_BATTLE_PANEL, sourceUserName),
                String.format(
                        CHOSEN_LEVEL_POKEMON,
                        sourceUserMention,
                        pokemon.getLevel(),
                        pokemon.getName()),
                pokemon.getImage());
    }

    /**
     * Creates a message with final battle confirmation embed
     *
     * @param otherUserAsMention a name of the other user in battle as mention
     * @param primaryUserPokemon primary user's pokemon offer
     * @param otherUserPokemon the other user's pokemon offer
     * @return message
     */
    Message renderFinalConfirmationMessage(
            String otherUserAsMention, Pokemon primaryUserPokemon, Pokemon otherUserPokemon) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(CONFIRM_BATTLE);
        embedBuilder.setDescription(
                String.format(
                        POKEMON_VS_POKEMON,
                        primaryUserPokemon.getLevel(),
                        primaryUserPokemon.getName(),
                        otherUserAsMention,
                        otherUserPokemon.getLevel(),
                        otherUserPokemon.getName()));
        MessageEmbed embed = embedBuilder.build();
        MessageBuilder messageBuilder = new MessageBuilder();
        return messageBuilder
                .setEmbeds(embed)
                .setActionRows(
                        ActionRow.of(
                                Button.success(CONFIRM_BATTLE_ID, CONFIRM_BATTLE_LABLE),
                                Button.danger(CANCEL_BATTLE_ID, CANCEL_BATTLE_LABLE)))
                .build();
    }

    /**
     * Sends battle confirmation message
     *
     * @param battle current battle
     * @param primaryUser primary user
     * @param otherUser another user in battle
     * @param primaryUserPokemon primary user's pokemon offered to battle
     * @param otherUserPokemon another user's pokemon offered to battle
     */
    @ExcludeFromJacocoGeneratedReport
    private void sendFinalConfirmationDM(
            Battle battle,
            User primaryUser,
            User otherUser,
            Pokemon primaryUserPokemon,
            Pokemon otherUserPokemon) {
        Message message =
                renderFinalConfirmationMessage(
                        otherUser.getAsMention(), primaryUserPokemon, otherUserPokemon);
        primaryUser
                .openPrivateChannel()
                .flatMap(channel -> channel.sendMessage(message))
                .queue(msg -> battleController.addPendingMessageId(battle.getId(), msg.getId()));
    }

    /**
     * Sends a DM with selections
     *
     * @param event SelectionMenuEvent event
     * @param otherUser another party in battle
     * @param pokemon selected pokemon
     * @param battle current battle
     * @return RestAction<Void>
     */
    @ExcludeFromJacocoGeneratedReport
    private RestAction<Void> sendSelectionDMs(
            SelectionMenuEvent event, User otherUser, Pokemon pokemon, Battle battle) {

        return event.reply(renderOwnChoiceConfirmation(pokemon))
                .and(
                        battleMessageUtil.sendDM(
                                event.getJDA(),
                                battle,
                                otherUser.getId(),
                                renderOthersChoiceConfirmation(
                                        pokemon,
                                        event.getUser().getName(),
                                        event.getUser().getAsMention())));
    }

    /**
     * A helper to send a DM with selections
     *
     * @param battle current battle
     * @param user1 primary user
     * @param user2 another user in battle
     * @param pokemon1 a pokemon selected by user1
     * @param pokemon2 a pokemon selected by user2
     */
    @ExcludeFromJacocoGeneratedReport
    private void sendOutFinalConfirmations(
            Battle battle, User user1, User user2, Pokemon pokemon1, Pokemon pokemon2) {
        sendFinalConfirmationDM(battle, user1, user2, pokemon1, pokemon2);
        sendFinalConfirmationDM(battle, user2, user1, pokemon2, pokemon1);
    }

    /**
     * Sends out messages
     *
     * @param event SelectionMenuEvent event
     * @param otherUser another user in battle
     * @param pokemon a chosen pokemon
     * @param battle current battle
     */
    @ExcludeFromJacocoGeneratedReport
    private void sendOutMessages(
            SelectionMenuEvent event, User otherUser, Pokemon pokemon, Battle battle) {
        sendSelectionDMs(event, otherUser, pokemon, battle)
                .queue(
                        _result -> {
                            log.info(
                                    USERS_OFFERS,
                                    battle.getFromUserActivePokemonId(),
                                    battle.getToUserActivePokemonId());
                            if (battle.getFromUserActivePokemonId() != null
                                    && battle.getToUserActivePokemonId() != null) {
                                log.info(SENDING_OUT_FINAL_CONFIRMATION_MESSAGES);
                                Boolean isUser1Offering =
                                        battle.getFromUserId().equals(event.getUser().getId());
                                sendOutFinalConfirmations(
                                        battle,
                                        isUser1Offering ? event.getUser() : otherUser,
                                        !isUser1Offering ? event.getUser() : otherUser,
                                        pokemonController.getPokemonById(
                                                battle.getFromUserActivePokemonId()),
                                        pokemonController.getPokemonById(
                                                battle.getToUserActivePokemonId()));
                            }
                        });
    }

    /**
     * Executes a procedure based on the the battle selection menu event for that battle selection
     * menu handler.
     *
     * @param event - a battle election menu event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        try {
            // Prepare all the data
            List<String> values = event.getInteraction().getValues();
            if (values.size() != 1) {
                throw new BattleException(PLEASE_SELECT_A_POKEMON);
            }

            ObjectId pokemonId = new ObjectId(values.get(0).split(":")[1]);
            Battle battle =
                    battleController.choosePokemonToBattle(event.getUser().getId(), pokemonId);
            Pokemon pokemon = pokemonController.getPokemonById(pokemonId);
            String otherUserId;
            if (battle.getFromUserId().equals(event.getUser().getId())) {
                otherUserId = battle.getToUserId();
            } else {
                otherUserId = battle.getFromUserId();
            }

            event.getJDA()
                    .retrieveUserById(otherUserId)
                    .queue(otherUser -> sendOutMessages(event, otherUser, pokemon, battle));
            event.getMessage().delete().queue();
        } catch (BattleException battleException) {
            event.reply(battleException.getMessage()).queue();
        }
    }
}
