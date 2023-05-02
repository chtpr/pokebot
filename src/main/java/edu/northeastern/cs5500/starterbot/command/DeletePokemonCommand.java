package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.exception.InvalidPokemonIndexException;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.bson.types.ObjectId;

@Slf4j
@Singleton
public class DeletePokemonCommand implements SlashCommandHandler {

    private UserInventoryController userInventoryController;
    private PokemonController pokemonController;

    @Inject
    DeletePokemonCommand(
            UserInventoryController userInventoryController, PokemonController pokemonController) {
        this.userInventoryController = userInventoryController;
        this.pokemonController = pokemonController;
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Delete a Pokemon by its index in the party")
                .addOptions(
                        new OptionData(
                                        OptionType.INTEGER,
                                        "index",
                                        "The bot will delete the chosen pokemon from the party")
                                .setRequired(true));
    }

    @Override
    public void onSlashCommandEvent(SlashCommandEvent event) {
        String discordUserId = event.getUser().getId();
        log.info("event: /delete used by user with id: {}", discordUserId);
        int index = (int) event.getOption("index").getAsLong();

        // get user's pokemon party
        ArrayList<ObjectId> party = userInventoryController.getPartyForUser(discordUserId);

        // validate index provided
        try {
            validateIndexGiven(index, party);
        } catch (InvalidPokemonIndexException e) {
            event.reply(e.getMessage()).queue();
            return;
        }

        // get the pokemon Id to be deleted from party
        ObjectId pokemonIdToDelete = party.get(index - 1);

        // remove pokemon from party
        removePokemonFromParty(party, discordUserId, pokemonIdToDelete);

        Pokemon deletedPokemon = pokemonController.getPokemonById(pokemonIdToDelete);
        event.reply(String.format("You have released %s!", deletedPokemon.getName())).queue();
    }

    void validateIndexGiven(int index, List<ObjectId> party) throws InvalidPokemonIndexException {
        // not allow users to delete if it will make the party empty
        if (party.size() == 1) {
            log.error("can't delete when only having 1 pokemon");
            throw new InvalidPokemonIndexException(
                    "You only have one pokemon. You cannot delete your last pokemon.");
        }

        // check for invalid index
        if (index <= 0) {
            throw new InvalidPokemonIndexException("Invalid index; must be 1 or greater!");
        }

        // check for index out of bounds
        if (index > party.size()) {
            throw new InvalidPokemonIndexException(
                    String.format(
                            "You tried to delete pokemon %d but you only have %d pokemon!",
                            index, party.size()));
        }
    }

    void removePokemonFromParty(
            ArrayList<ObjectId> party, String discordUserId, ObjectId pokemonIdToDelete) {
        ArrayList<ObjectId> newParty = new ArrayList<>();

        for (ObjectId id : party) {
            if (id.equals(pokemonIdToDelete)) {
                continue;
            }
            newParty.add(id);
        }

        log.info("Deleting Pokemon with index {} from user {}", pokemonIdToDelete, discordUserId);
        userInventoryController.setPartyForUser(discordUserId, newParty);
    }
}
