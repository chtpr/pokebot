package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserInventoryControllerTest {
    static PokemonShowdownService pokemonShowdownService = null;
    static PokedexController pokedexController = null;

    @BeforeAll
    static void setupService() {
        // The service is readonly so it's fine to cache it
        pokemonShowdownService = new PokemonShowdownService();
        pokedexController =
                new PokedexController(new InMemoryRepository<>(), pokemonShowdownService);
    }

    static final String USER_ID_1 = "350082976618512414";
    static final String USER_ID_2 = "123482976618512414";

    @Test
    void testGetInventoryForMemberIdCreatesAndReturnsNewInventory() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);
        ArrayList<ObjectId> party =
                userInventoryController.getInventoryForMemberId(USER_ID_1).getParty();
        assertThat(party.size()).isEqualTo(0);
    }

    @Test
    void testGetInventoryForMemberIdReturnsExistingInventory() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);
        ArrayList<ObjectId> party =
                userInventoryController.getInventoryForMemberId(USER_ID_1).getParty();
        assertThat(party.size()).isEqualTo(0);

        ObjectId objId = new ObjectId();
        party.add(objId);

        party = userInventoryController.getInventoryForMemberId(USER_ID_1).getParty();
        assertThat(party.size()).isGreaterThan(0);
    }

    @Test
    void testGetPartyForUser() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);
        ArrayList<ObjectId> party =
                userInventoryController.getInventoryForMemberId(USER_ID_1).getParty();
        assertThat(party.size()).isEqualTo(0);

        ObjectId objId = new ObjectId();
        party.add(objId);

        party = userInventoryController.getPartyForUser(USER_ID_1);
        assertThat(party.size()).isGreaterThan(0);
    }

    @Test
    void testSetPartyForUser() {
        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);
        ArrayList<ObjectId> party =
                userInventoryController.getInventoryForMemberId(USER_ID_1).getParty();
        assertThat(party.size()).isEqualTo(0);

        ObjectId objId = new ObjectId();
        ArrayList<ObjectId> updatedParty = new ArrayList<>();
        updatedParty.add(objId);
        assertThat(updatedParty).isNotEqualTo(party);

        userInventoryController.setPartyForUser(USER_ID_1, updatedParty);
        party = userInventoryController.getPartyForUser(USER_ID_1);
        assertThat(updatedParty).isEqualTo(party);
    }

    @Test
    void addPokemonToUserPartyHappyPath() {
        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        assertThat(userInventoryController.getInventoryForMemberId(USER_ID_1).getParty()).isEmpty();

        Pokemon pokemon = pokemonController.newPokemonFromNumber(1);

        // Manually add pokemon
        addPokemonIdToUserPartyForTesting(userInventoryController, USER_ID_1, pokemon.getId());
        ArrayList<ObjectId> expectedParty =
                userInventoryController.getInventoryForMemberId(USER_ID_1).getParty();

        // Call function to add pokemon
        ArrayList<ObjectId> newParty2 = new ArrayList<>();
        userInventoryController.setPartyForUser(USER_ID_1, newParty2);
        userInventoryController.addPokemonToUserParty(USER_ID_1, pokemon);
        ArrayList<ObjectId> actualParty =
                userInventoryController.getInventoryForMemberId(USER_ID_1).getParty();

        assertThat(expectedParty.get(0)).isEqualTo(actualParty.get(0));
    }

    @Test
    void addPokemonToUserPartyDuplicateIgnored() {
        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        GenericRepository<UserInventory> userInventoryRepository = new InMemoryRepository<>();
        UserInventoryController userInventoryController =
                new UserInventoryController(userInventoryRepository);

        assertThat(userInventoryController.getInventoryForMemberId(USER_ID_2).getParty()).isEmpty();

        // Add first pokemon
        Pokemon pokemon = pokemonController.newPokemonFromNumber(1);
        userInventoryController.addPokemonToUserParty(USER_ID_2, pokemon);

        assertThat(userInventoryController.getInventoryForMemberId(USER_ID_2).getParty().size())
                .isEqualTo(1);

        // Add duplicate
        userInventoryController.addPokemonToUserParty(USER_ID_2, pokemon);

        assertThat(userInventoryController.getInventoryForMemberId(USER_ID_2).getParty().size())
                .isEqualTo(1);
    }

    public static void addPokemonIdToUserPartyForTesting(
            UserInventoryController userInventoryController, String userId, ObjectId pokemonId) {
        ArrayList<ObjectId> party = userInventoryController.getPartyForUser(userId);
        party.add(pokemonId);
        userInventoryController.setPartyForUser(userId, party);
    }
}
