package edu.northeastern.cs5500.starterbot.exception;

/**
 * InvalidPokedexNumberException is an exception that indicates that an invalid pokedex number was
 * supplied. It is thrown when a given pokedex number could not be used to retrieve a pokemon in our
 * system.
 */
public class InvalidPokedexNumberException extends RuntimeException {

    /**
     * A constructor that takes in a pokedex number and produces an informational string.
     *
     * @param pokedexNumber - the pokedex number which triggered the exception
     */
    public InvalidPokedexNumberException(int pokedexNumber) {
        super(String.format("Unable to retrieve a pokemon with pokedex number %d.", pokedexNumber));
    }
}
