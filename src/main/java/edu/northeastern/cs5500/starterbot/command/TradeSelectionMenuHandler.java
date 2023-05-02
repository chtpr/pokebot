package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.Trade;
import edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil;
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

/** TradeSelectionMenuHandler allows to process users choice of pokemons to trade */
@Singleton
@Slf4j
@ExcludeClassFromJacocoGeneratedReport
public class TradeSelectionMenuHandler implements SelectionMenuHandler {

    @Inject TradeController tradeController;
    @Inject UserInventoryController inventoryController;
    @Inject PokemonController pokemonController;
    @Inject TradeMessageUtil msgUtil;

    @Inject
    public TradeSelectionMenuHandler() {}

    /**
     * Gets the trade selection menu handler name.
     *
     * @return name - the name of the trade selection menu handler
     */
    @Override
    public String getName() {
        return "trade";
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
     * Creates a message user will get when choose their own pokemon to trade. This message will
     * contain the name and image of their choice.
     *
     * @param pokemon a pokemon user wants to exchange
     * @return a message
     */
    Message renderOwnChoiceConfirmation(Pokemon pokemon) {
        return renderPokemonMessage(
                "Your trade offer",
                String.format("You've chosen Level %s %s", pokemon.getLevel(), pokemon.getName()),
                pokemon.getImage());
    }

    /**
     * Creates a message user will get when other party their own pokemon to trade. This message
     * will contain the name and image of their choice.
     *
     * @param pokemon a pokemon the other user in trade wants to exchange
     * @param sourceUserName a name of the other user in trade
     * @param sourceUserMention a name of the other user in trade as mention
     * @return a message
     */
    Message renderOthersChoiceConfirmation(
            Pokemon pokemon, String sourceUserName, String sourceUserMention) {
        return renderPokemonMessage(
                String.format("Trade offer from %s", sourceUserName),
                String.format(
                        "%s has chosen Level %s %s",
                        sourceUserMention, pokemon.getLevel(), pokemon.getName()),
                pokemon.getImage());
    }

    /**
     * Creates a message with final trade confirmation embed
     *
     * @param otherUserAsMention a name of the other user in trade as mention
     * @param primaryUserPokemon primary user's pokemon offer
     * @param otherUserPokemon the other user's pokemon offer
     * @return message
     */
    Message renderFinalConfirmationMessage(
            String otherUserAsMention, Pokemon primaryUserPokemon, Pokemon otherUserPokemon) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Please confirm the trade");
        embedBuilder.setDescription(
                String.format(
                        "Your Level %s pokemon %s will be exchanged for %s's Level %s pokemon %s",
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
                                Button.success("confirm_trade", "Confirm trade"),
                                Button.danger("cancel_trade", "Cancel trade")))
                .build();
    }

    /**
     * Sends trade confirmation message
     *
     * @param trade current trade
     * @param primaryUser primary user
     * @param otherUser another user in trade
     * @param primaryUserPokemon primary user's pokemon offered to trade
     * @param otherUserPokemon another user's pokemon offered to trade
     */
    @ExcludeFromJacocoGeneratedReport
    private void sendFinalConfirmationDM(
            Trade trade,
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
                .queue(msg -> tradeController.addPendingMessageId(trade.getId(), msg.getId()));
    }

    /**
     * Sends a DM with selections
     *
     * @param event SelectionMenuEvent event
     * @param otherUser another party in trade
     * @param pokemon selected pokemon
     * @param trade current trade
     * @return RestAction<Void>
     */
    @ExcludeFromJacocoGeneratedReport
    private RestAction<Void> sendSelectionDMs(
            SelectionMenuEvent event, User otherUser, Pokemon pokemon, Trade trade) {

        return event.reply(renderOwnChoiceConfirmation(pokemon))
                .and(
                        msgUtil.sendDM(
                                event.getJDA(),
                                trade,
                                otherUser.getId(),
                                renderOthersChoiceConfirmation(
                                        pokemon,
                                        event.getUser().getName(),
                                        event.getUser().getAsMention())));
    }

    /**
     * A helper to send a DM with selections
     *
     * @param trade current trade
     * @param user1 primary user
     * @param user2 another user in trade
     * @param pokemon1 a pokemon selected by user1
     * @param pokemon2 a pokemon selected by user2
     */
    @ExcludeFromJacocoGeneratedReport
    private void sendOutFinalConfirmations(
            Trade trade, User user1, User user2, Pokemon pokemon1, Pokemon pokemon2) {
        sendFinalConfirmationDM(trade, user1, user2, pokemon1, pokemon2);
        sendFinalConfirmationDM(trade, user2, user1, pokemon2, pokemon1);
    }

    /**
     * Sends out messages
     *
     * @param event SelectionMenuEvent event
     * @param otherUser another user in trade
     * @param pokemon a chosen pokemon
     * @param trade current trade
     */
    @ExcludeFromJacocoGeneratedReport
    private void sendOutMessages(
            SelectionMenuEvent event, User otherUser, Pokemon pokemon, Trade trade) {
        sendSelectionDMs(event, otherUser, pokemon, trade)
                .queue(
                        _result -> {
                            log.info(
                                    "User 1 offered {}, user 2 offered {}",
                                    trade.getUser1Offer(),
                                    trade.getUser2Offer());
                            if (trade.getUser1Offer() != null && trade.getUser2Offer() != null) {
                                log.info(
                                        "All selections are made, sending out final confirmation messages");
                                Boolean isUser1Offering =
                                        trade.getUser1Id().equals(event.getUser().getId());
                                sendOutFinalConfirmations(
                                        trade,
                                        isUser1Offering ? event.getUser() : otherUser,
                                        !isUser1Offering ? event.getUser() : otherUser,
                                        pokemonController.getPokemonById(trade.getUser1Offer()),
                                        pokemonController.getPokemonById(trade.getUser2Offer()));
                            }
                        });
    }

    /**
     * Executes a procedure based on the the trade selection menu event for that trade selection
     * menu handler.
     *
     * @param event - a trade election menu event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        try {
            // Prepare all the data
            List<String> values = event.getInteraction().getValues();
            if (values.size() != 1) {
                throw new TradeException("Please select a pokemon");
            }
            ObjectId pokemonId = new ObjectId(values.get(0).split(":")[1]);
            Trade trade = tradeController.offerPokemon(event.getUser().getId(), pokemonId);
            Pokemon pokemon = pokemonController.getPokemonById(pokemonId);
            String otherUserId;
            if (trade.getUser1Id().equals(event.getUser().getId())) {
                otherUserId = trade.getUser2Id();
            } else {
                otherUserId = trade.getUser1Id();
            }
            // Send out messages
            event.getJDA()
                    .retrieveUserById(otherUserId)
                    .queue(otherUser -> sendOutMessages(event, otherUser, pokemon, trade));
            event.getMessage().delete().queue();
        } catch (TradeException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
