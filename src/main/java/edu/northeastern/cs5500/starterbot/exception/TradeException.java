package edu.northeastern.cs5500.starterbot.exception;
/**
 * TradeException is the class that is used for conditions that a bot might want to catch during
 * trade process.
 */
public class TradeException extends Exception {
    public TradeException(String message) {
        super(message);
    }
}
