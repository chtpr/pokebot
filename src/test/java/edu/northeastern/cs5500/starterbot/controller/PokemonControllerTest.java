package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class PokemonControllerTest {

    static final String CHOSEN_POKEMON_NAME = "Squirtle";
    static final int CHOSEN_POKEMON_POKEDEX_NUM = 7;

    @Test
    void testCreatesPokemonFromNumber() {
        GenericRepository<Pokemon> pokemonRepository = new InMemoryRepository<>();
        pokemonRepository.add(generateSquirtle());

        PokedexController pokedexController =
                new PokedexController(new InMemoryRepository<>(), new PokemonShowdownService());
        PokemonController pokemonController =
                new PokemonController(pokemonRepository, pokedexController);

        Pokemon actualPokemon =
                pokemonController.createPokemon(
                        pokedexController.getPokemonWithNumber(CHOSEN_POKEMON_POKEDEX_NUM));

        assertThat(actualPokemon.getName()).isEqualTo(CHOSEN_POKEMON_NAME);
        assertThat(actualPokemon.getPokedexNumber()).isEqualTo(CHOSEN_POKEMON_POKEDEX_NUM);
    }

    public static Pokemon generateSquirtle() {

        ArrayList<String> pokemonTypes = new ArrayList<>();
        pokemonTypes.add("water");
        pokemonTypes.add("poison");
        Pokemon squirtle = new Pokemon();
        squirtle.setPokedexNumber(7);
        squirtle.setName("Squirtle");
        squirtle.setPokemonTypes(pokemonTypes);
        squirtle.setGender("Female");
        squirtle.setLevel(1);
        squirtle.setExperiencePoints(0);
        squirtle.setFainted(false);

        return squirtle;
    }

    public static Pokemon generateBulbasaur() {
        ArrayList<String> pokemonTypes = new ArrayList<>();
        pokemonTypes.add("grass");
        pokemonTypes.add("poison");
        Pokemon bulbasaur = new Pokemon();
        bulbasaur.setPokedexNumber(1);
        bulbasaur.setName("Bulbasaur");
        bulbasaur.setPokemonTypes(pokemonTypes);
        bulbasaur.setGender("Male");
        bulbasaur.setLevel(1);
        bulbasaur.setExperiencePoints(0);
        bulbasaur.setFainted(false);

        return bulbasaur;
    }
}
