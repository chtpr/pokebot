package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.exception.BattleException;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

/** BattleButtonClickHandleAccept allows to process acceptance of the battle invite. */
@Singleton
@Slf4j
public class BattleButtonClickHandleAccept implements ButtonClickHandler {

    public static final String ACCEPT_BATTLE = "accept_battle";
    public static final String SELECT_POKEMON_DROPDOWN = "select-pokemon-dropdown";
    public static final String CLICKED_ACCEPT_BUTTON = "%s clicked accept button";
    public static final String CANCEL_BATTLE_LABLE = "Cancel battle";
    public static final String CANCEL_BATTLE_ID = "cancel_battle";
    public static final String INITIATE_THE_BATTLE = "Initiating the battle...";
    public static final String CHOOSE_POKEMON_TO_BATTLE = "Choose your pokemon to battle";
    public static final String CHOOSE_POKEMON_TO_BATTLE_TITLE =
            "Please choose your pokemon to battle";
    public static final String NO_POKEMONS_TO_BATTLE =
            "Unable to start a battle, one of the parties doesn't have any pokemons in their inventory.";

    @Inject BattleController battleController;
    @Inject UserInventoryController userInventoryController;
    @Inject BattleMessageUtil battleMessageUtil;

    @Inject
    public BattleButtonClickHandleAccept() {}

    /**
     * Gets the battle button click handler name.
     *
     * @return name - the name of the battle button click handler
     */
    @Override
    public String getName() {
        return ACCEPT_BATTLE;
    }

    /**
     * Creates a message with selection menu of pokemons.
     *
     * @param userPokemons pokemons this user possesses
     * @return message
     * @throws BattleException
     */
    Message renderSelectionMessage(Collection<Pokemon> userPokemons) throws BattleException {
        if (userPokemons.isEmpty()) {
            throw new BattleException(NO_POKEMONS_TO_BATTLE);
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(CHOOSE_POKEMON_TO_BATTLE_TITLE);
        MessageEmbed embed = embedBuilder.build();
        MessageBuilder messageBuilder = new MessageBuilder();
        SelectionMenu.Builder menuBuilder =
                SelectionMenu.create(SELECT_POKEMON_DROPDOWN)
                        .setPlaceholder(CHOOSE_POKEMON_TO_BATTLE);

        for (Pokemon pokemon : userPokemons) {
            String msg = String.format("battle:%s", pokemon.getId().toHexString());
            menuBuilder.addOption(pokemon.getName(), msg);
        }

        SelectionMenu menu = menuBuilder.build();
        return messageBuilder
                .setEmbeds(embed)
                .setActionRows(
                        ActionRow.of(menu),
                        ActionRow.of(Button.danger(CANCEL_BATTLE_ID, CANCEL_BATTLE_LABLE)))
                .build();
    }

    /**
     * Informs users that the battle begins and sends both users an embed with choose pokemon
     * request
     *
     * @param event - a battle button click event
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        log.info(CLICKED_ACCEPT_BUTTON, event.getUser().getName());
        try {
            Battle battle = battleController.acceptBattleRequest(event.getUser().getId());
            Message dm1 =
                    renderSelectionMessage(
                            userInventoryController.getUserPokemons(battle.getFromUserId()));
            Message dm2 =
                    renderSelectionMessage(
                            userInventoryController.getUserPokemons(battle.getToUserId()));

            event.reply(INITIATE_THE_BATTLE)
                    .setEphemeral(true)
                    .queue(
                            _msg -> {
                                battleMessageUtil
                                        .sendDM(
                                                event.getJDA(), battle,
                                                battle.getFromUserId(), dm1)
                                        .queue();
                                battleMessageUtil
                                        .sendDM(
                                                event.getJDA(), battle,
                                                battle.getToUserId(), dm2)
                                        .queue();
                            });

            battleController.removePendingMessageId(battle.getId(), event.getMessage().getId());
            event.getMessage().delete().queue();
        } catch (BattleException e) {
            event.reply(e.getMessage()).queue();
        }
    }
}
