package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownEntry;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * PokedexController is used to look up entries in the pokedex.
 *
 * <p>Pokedex entries describe pokemon of a specific type; e.g. what percentage of Pikachu are
 * female?
 */
@Slf4j
@Singleton
public class PokedexController {
    // Handpick the Pokemon we want to include
    static final String[] POKEMON_NAMES = {
        "bulbasaur",
        "charmander",
        "squirtle",
        "pikachu",
        "eevee",
        "vaporeon",
        "jolteon",
        "flareon",
        "mew",
        "espeon",
        "umbreon",
        "mightyena"
    };

    GenericRepository<PokedexEntry> pokedexRepository;
    PokemonShowdownService pokemonShowdownService;

    @Inject
    public PokedexController(
            GenericRepository<PokedexEntry> pokedexRepository,
            PokemonShowdownService pokemonShowdownService) {
        this.pokemonShowdownService = pokemonShowdownService;
        this.pokedexRepository = pokedexRepository;

        // if pokedex repository is empty
        if (pokedexRepository.count() == 0) {
            Map<String, PokemonShowdownEntry> pokedex = pokemonShowdownService.fetchPokedex();
            // create a pokedex entry from a showdown entry for each name
            for (String pokemonName : POKEMON_NAMES) {
                PokemonShowdownEntry showdownEntry = pokedex.get(pokemonName);
                if (showdownEntry == null) {
                    log.error("Unable to find a pokemon in showdown matching '{}'", pokemonName);
                    continue;
                }

                PokedexEntry entry = new PokedexEntry();
                entry.setPokedexNumber(showdownEntry.getNum());
                entry.setName(showdownEntry.getName());

                ArrayList<String> pokemonTypes = new ArrayList<>();
                String[] showdownTypes = showdownEntry.getTypes();
                for (String t : showdownTypes) {
                    pokemonTypes.add(t);
                }
                entry.setPokemonTypes(pokemonTypes);
                entry.setGenderRatio(showdownEntry.getGenderRatio());
                entry.genderRatio();
                entry.setBaseStats(showdownEntry.getBaseStats());
                log.info("created pokemon '{}'", showdownEntry.getName());
                // add to the repository
                pokedexRepository.add(entry);
            }
        }
        log.info("Created PokedexController");
    }

    /**
     * Gets a Pokemon from the repository when provided with the Pokemon number.
     *
     * @param pokedexNumber the Pokemon number
     * @return a Pokemon, null if not found
     */
    @Nullable
    public PokedexEntry getPokemonWithNumber(long pokedexNumber) {
        Collection<PokedexEntry> entries = pokedexRepository.getAll();
        for (PokedexEntry entry : entries) {
            if (entry.getPokedexNumber() == pokedexNumber) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Gets a random pokedex entry. To be used when catching a Pokemon.
     *
     * @return a random pokedex entry
     */
    @Nonnull
    public PokedexEntry getRandomPokedexEntry() {
        int index = (new Random()).nextInt((int) pokedexRepository.count());

        for (PokedexEntry entry : pokedexRepository.getAll()) {
            if (index-- == 0) {
                return entry;
            }
        }

        log.error("Logic error - should never be unable to get a random Pokemon");
        throw new RuntimeException("Logic error - should never be unable to get a random Pokemon");
    }
}
