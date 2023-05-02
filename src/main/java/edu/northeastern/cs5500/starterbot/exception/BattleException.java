package edu.northeastern.cs5500.starterbot.exception;

/**
 * BattleException is the class that is used for conditions that a bot might want to catch during
 * battle process.
 */
public class BattleException extends Exception {
    public BattleException(String message) {
        super(message);
    }
}
