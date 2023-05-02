package edu.northeastern.cs5500.starterbot.exception;

/**
 * InvalidPokemonIndexException is an exception that can be thrown by the DeletePokemonCommand. It
 * can be thrown when a user gives an invalid party index of the pokemon they are tring to delete.
 */
public class InvalidPokemonIndexException extends Exception {
    public InvalidPokemonIndexException(String message) {
        super(message);
    }
}
