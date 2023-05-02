package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PokedexControllerTest {
    static PokemonShowdownService pokemonShowdownService = null;
    static PokedexController pokedexController = null;
    static final Double MALE_RATIO = 0.875;
    static final Double FEMALE_RATIO = 0.125;
    static final long SQUIRTLE_NO = 7;

    @BeforeAll
    static void setupService() {
        // The service is readonly so it's fine to cache it
        pokemonShowdownService = new PokemonShowdownService();
        GenericRepository<PokedexEntry> pokedexRepository = new InMemoryRepository<>();
        pokedexRepository.add(generateSquirtleEntry());
        pokedexController = new PokedexController(pokedexRepository, pokemonShowdownService);
    }

    public static PokedexEntry generateSquirtleEntry() {

        ArrayList<String> pokemonTypes = new ArrayList<>();
        pokemonTypes.add("water");
        Map<String, Double> genderRatio = new HashMap<String, Double>();
        genderRatio.put("M", MALE_RATIO);
        genderRatio.put("F", FEMALE_RATIO);
        PokedexEntry squirtle = new PokedexEntry();
        squirtle.setPokedexNumber(7);
        squirtle.setName("Squirtle");
        squirtle.setPokemonTypes(pokemonTypes);
        squirtle.setGenderRatio(genderRatio);

        return squirtle;
    }

    @Test
    void testCreatesPokemonFromNumber() {
        PokedexEntry entry = pokedexController.getPokemonWithNumber(SQUIRTLE_NO);
        assertThat(entry.getName()).isEqualTo(generateSquirtleEntry().getName());
        assertThat(entry.getPokedexNumber()).isEqualTo(generateSquirtleEntry().getPokedexNumber());
    }
}
