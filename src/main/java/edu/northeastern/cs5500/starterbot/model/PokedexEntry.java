package edu.northeastern.cs5500.starterbot.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
/**
 * Model for a Pokedex entry. Pokedex entries describe pokemon of a specific type; e.g. what
 * percentage of Pikachu are female?
 */
public class PokedexEntry implements Model {
    static final Double EVEN_GENDER_RATIO = 0.5;

    ObjectId id;

    long pokedexNumber;
    String name;
    ArrayList<String> pokemonTypes;
    Map<String, Double> genderRatio;
    Map<String, Integer> baseStats;

    /**
     * Method to create and get the Pokemon image.
     *
     * @return the Pokemon image
     */
    public String getImage() {
        return String.format(
                "https://cdn.traction.one/pokedex/pokemon/%d.png", this.getPokedexNumber());
    }

    /**
     * If Showdown entry object has no gender ratio (meaning it's even) sets the gender ratio to
     * even.
     */
    public void genderRatio() {
        Map<String, Double> evenGenderRatio = new HashMap<String, Double>();
        if (this.genderRatio == null) {
            evenGenderRatio.put("M", EVEN_GENDER_RATIO);
            evenGenderRatio.put("F", EVEN_GENDER_RATIO);
            this.genderRatio = evenGenderRatio;
        }
    }
}
