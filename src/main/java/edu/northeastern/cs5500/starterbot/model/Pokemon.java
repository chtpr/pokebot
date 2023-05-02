package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
/**
 * Our Pokemon model, includes fields such as Pokemon number, name, types, gender, image, level,
 * exp, and fainted status.
 */
public class Pokemon extends PokedexEntry {
    public Pokemon(PokedexEntry entry) {
        // deliberately NOT copying the ID of the pokedex entry
        setPokedexNumber(entry.getPokedexNumber());
        setName(entry.getName());
        setPokemonTypes(entry.getPokemonTypes());
        setGenderRatio(entry.getGenderRatio());
        setBaseStats(entry.getBaseStats());
    }

    String gender;
    Integer level = 1;
    Integer experiencePoints = 0;
    Boolean fainted = false;
}
