package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import java.util.ArrayList;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class InspectPokemonCommand implements SlashCommandHandler {
    @Inject UserInventoryController userInventoryController;
    @Inject PokemonController pokemonController;

    @Inject
    public InspectPokemonCommand() {}

    /** A constructor without injection, available for testing purposes. */
    @Nonnull
    public InspectPokemonCommand(
            UserInventoryController userInventoryController, PokemonController pokemonController) {
        this.userInventoryController = userInventoryController;
        this.pokemonController = pokemonController;
    }

    /** Get the name of the command */
    @Override
    public String getName() {
        return "inspectpokemon";
    }

    /** Get the data of the command */
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "View the stats of one specific Pokemon")
                .addOptions(
                        new OptionData(
                                        OptionType.INTEGER,
                                        "index",
                                        "The index of the pokemon in your party to show")
                                .setRequired(true));
    }

    /**
     * Generate the embed of the command
     *
     * @return MessageEmbed - the embed to be displayed
     */
    public MessageEmbed inspectPokemon(String discordUserId, int index) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        ArrayList<ObjectId> party = userInventoryController.getPartyForUser(discordUserId);
        ObjectId pokemonId = party.get(index - 1);

        // get the Pokemon
        Pokemon pokemon = pokemonController.getPokemonById(pokemonId);

        // show the Pokemon
        embedBuilder.setTitle("Inspecting Pokemon");
        String pokemonInfo = pokemonInfoBuilder(pokemon);
        embedBuilder.setDescription(pokemonInfo);
        embedBuilder.setImage(pokemon.getImage());
        return embedBuilder.build();
    }

    /** The actual command event */
    @Override
    public void onSlashCommandEvent(SlashCommandEvent event) {
        log.info("event: inspecting a single Pokemon");
        MessageBuilder messageBuilder = new MessageBuilder();
        String discordUserId = event.getUser().getId();
        int index = (int) event.getOption("index").getAsLong();
        ArrayList<ObjectId> party = userInventoryController.getPartyForUser(discordUserId);

        // make these into exceptions and catch them
        // refer to DELETEpokemonCommand
        // check for invalid party id
        if (!validateIndex(index, party)) {
            event.reply(invalidMessageBuilder(index, party)).queue();
            return;
        }

        messageBuilder.setEmbeds(inspectPokemon(discordUserId, index));
        event.reply(messageBuilder.build()).queue();
    }

    /**
     * Validte if the index is valid
     *
     * @param index the index of pokemon to inspect
     * @param party the pokemon party of the user
     * @return
     */
    public Boolean validateIndex(int index, ArrayList<ObjectId> party) {
        if ((index <= 0) | index > party.size()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Build the message for invalid index input
     *
     * @param index the index of pokemon to inspect
     * @param party the pokemon party of the user
     * @return
     */
    public String invalidMessageBuilder(int index, ArrayList<ObjectId> party) {
        if (index <= 0) {
            return "Invalid index; must be 1 or greater!";
        }
        if (index > party.size()) {
            return String.format(
                    "You tried to inspect pokemon %d but you only have %d pokemon!",
                    index, party.size());
        }
        return null;
    }

    /**
     * A helper to generate pokemon info for the embed
     *
     * @param pokemon the pokemon we need info of
     * @return a pokemon info string
     */
    private String pokemonInfoBuilder(Pokemon pokemon) {
        String pokemonInfo =
                String.format(
                        "Pokemon: %s\nLevel:  %s\nGender:  %s\nTypes:  %s\n",
                        pokemon.getName(),
                        pokemon.getLevel(),
                        pokemon.getGender(),
                        String.join(", ", pokemon.getPokemonTypes()));

        return pokemonInfo;
    }
}
