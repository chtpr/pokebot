package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

/**
 * PokemonController is used to create specific copies of Pokemon; e.g. generating a specific
 * Pikachu when one is caught.
 */
@Singleton
@Slf4j
public class PokemonController {
    PokedexController pokedexController;
    GenericRepository<Pokemon> pokemonRepository;

    @Inject
    public PokemonController(
            GenericRepository<Pokemon> pokemonRepository, PokedexController pokedexController) {
        this.pokemonRepository = pokemonRepository;
        this.pokedexController = pokedexController;
        log.info("Created PokemonController");
    }

    /**
     * Creates a Pokemon object from a Pokedex Entry object. Adds it to the Pokemon repository.
     *
     * @param pokedexEntry the Pokedex Entry object
     * @return the corresponding Pokemon object
     */
    @Nonnull
    public Pokemon createPokemon(PokedexEntry pokedexEntry) {
        if (pokedexEntry == null) {
            log.error("Logic error - cannot create a pokemon from a null pokedex entry");
            throw new RuntimeException(
                    "Logic error - cannot create a pokemon from a null pokedex entry");
        }
        Pokemon pokemon = new Pokemon(pokedexEntry);
        pokemon.setGender(randomGender(pokedexEntry.getGenderRatio()));
        return pokemonRepository.add(pokemon);
    }

    /**
     * Returns a random gender for the Pokemon.
     *
     * @return a random gender
     */
    @Nonnull
    String randomGender(Map<String, Double> genderRatio) {
        Double maleRatio = genderRatio.get("M");
        Double randomNum = Math.random();
        String gender = (randomNum < maleRatio) ? "Male" : "Female";
        return gender;
    }

    /**
     * Creates a Pokemon object when given its corresponding Pokedex number.
     *
     * @param pokemonNumber the Pokedex number
     * @return the Pokemon object
     */
    @Nullable
    public Pokemon newPokemonFromNumber(int pokemonNumber) {
        PokedexEntry pokedexEntry = pokedexController.getPokemonWithNumber(pokemonNumber);
        if (pokedexEntry == null) {
            log.warn("Tried to create a pokemon with an unknown number: {}", pokemonNumber);
            return null;
        }
        return createPokemon(pokedexEntry);
    }

    /**
     * Gets a Pokemon by ObjectId.
     *
     * @param pokemonId the ObjectId of the Pokemon
     * @return the Pokemon
     */
    @Nullable
    public Pokemon getPokemonById(ObjectId pokemonId) {
        return pokemonRepository.get(pokemonId);
    }
}
