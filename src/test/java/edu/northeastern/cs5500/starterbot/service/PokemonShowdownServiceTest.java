package edu.northeastern.cs5500.starterbot.service;

import static com.google.common.truth.Truth.assertThat;

import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PokemonShowdownServiceTest {
    static PokemonShowdownService pokemonShowdownService = null;

    @BeforeAll
    static void setupService() {
        // The service is readonly so it's fine to cache it
        pokemonShowdownService = new PokemonShowdownService();
    }

    @Test
    void testPokedexHasBulbasaur() {
        Map<String, PokemonShowdownEntry> pokedex = pokemonShowdownService.fetchPokedex();
        assertThat(pokedex).containsKey("bulbasaur");
    }

    @Test
    void testShedinjaHasMaxHp1() {
        Map<String, PokemonShowdownEntry> pokedex = pokemonShowdownService.fetchPokedex();
        assertThat(pokedex).containsKey("shedinja");
        PokemonShowdownEntry shedinja = pokedex.get("shedinja");
        assertThat(shedinja.getMaxHP()).isEqualTo(1);
    }
}
