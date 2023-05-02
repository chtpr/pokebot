package edu.northeastern.cs5500.starterbot.exception;

/**
 * UnauthorizedPokemonSelectionException is an exception that indicates a starter pokemon cannot be
 * chosen by the user. It is thrown when a Choose Starter button click event is received with a user
 * who already has a pokemon.
 */
public class UnauthorizedPokemonSelectionException extends Exception {

    /**
     * A constructor that takes in a discord user id and produces an informational string.
     *
     * @param discordUserId - the id of the user associated with the event that triggered this
     *     exception
     */
    public UnauthorizedPokemonSelectionException(String discordUserId) {
        super(
                String.format(
                        "User with id %s is unauthorized to choose a starter pokemon at this time.",
                        discordUserId));
    }
}
