package edu.northeastern.cs5500.starterbot.controller;

import com.mongodb.lang.Nullable;
import edu.northeastern.cs5500.starterbot.exception.ForceCancelTradeException;
import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Trade;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Slf4j
@Singleton
public class TradeController {
    GenericRepository<Trade> tradeRepository;
    UserInventoryController inventoryController;

    @Inject
    public TradeController(
            GenericRepository<Trade> tradeRepository, UserInventoryController inventoryController) {
        this.tradeRepository = tradeRepository;
        this.inventoryController = inventoryController;
        log.info("Created TradeController");
    }

    /**
     * Begin a trade between two users.
     *
     * @param user1Id - user initiating the trade
     * @param user2Id - user that receives the trade request
     * @return Trade object representing a trade between two users
     */
    @Nonnull
    public Trade beginTrade(String user1Id, String user2Id) throws TradeException {
        if (user1Id.equals(user2Id)) {
            // You cannot trade with yourself
            log.info("{} tried to trade with themselves", user1Id);
            throw new TradeException("You cannot trade with yourself!");
        }
        if (getTrade(user1Id) != null) {
            log.info("{} tried to trade with someone else during the current trade", user1Id);
            throw new TradeException(
                    "You're already trading with someone. Please complete the ongoing trade first.");
        }
        if (getTrade(user2Id) != null) {
            throw new TradeException(
                    "That trainer is already trading with someone. Please wait for them to complete the trade first.");
        }
        List<ObjectId> user1Party = inventoryController.getPartyForUser(user1Id);
        List<ObjectId> user2Party = inventoryController.getPartyForUser(user2Id);
        if (user1Party.isEmpty()) {
            log.info("{} tried to trade with empty party", user1Id);
            throw new TradeException("You don't have any pokemons to trade");
        }
        if (user2Party.isEmpty()) {
            log.info("{} tried to trade with {} who has no pokemons", user1Id, user2Id);
            throw new TradeException("That trainer doesn't have any pokemons to trade");
        }

        Trade newTrade = new Trade();

        newTrade.setUser1Id(user1Id);
        newTrade.setUser2Id(user2Id);
        newTrade.setUser2ConfirmedTrade(false);

        return tradeRepository.add(newTrade);
    }

    /**
     * Get the active Trade object between these two users if it exists.
     *
     * @param userId one of the users in the trade
     * @return null if there is no active trade, otherwise a Trade object
     */
    @Nullable
    public Trade getTrade(String userId) {
        Trade tradeFound = null;
        for (Trade trade : tradeRepository.getAll()) {
            if (trade.getUser1Id().equals(userId) || trade.getUser2Id().equals(userId)) {
                if (tradeFound != null) {
                    // Invalid state - user1 is trading with several people now at the
                    // same time
                    log.error("Found multiple ongoing trades for user {}", userId);
                    assert false; // Make unit tests fail, but not production
                }
                tradeFound = trade;
            }
        }
        return tradeFound;
    }

    /**
     * Gets all existing trades
     *
     * @return all existing trades
     */
    @Nullable
    ArrayList<Trade> getAllTrades() {
        ArrayList<Trade> trades = new ArrayList<>();
        for (Trade trade : tradeRepository.getAll()) {
            trades.add(trade);
        }
        return trades;
    }

    /**
     * Accept trade request
     *
     * @param acceptingUserId - a user that is expected to respond to trade request
     * @return Trade object representing a trade between two users
     * @throws TradeException
     */
    public Trade acceptTradeRequest(String acceptingUserId) throws TradeException {
        Trade existingTrade = getTrade(acceptingUserId);
        if (existingTrade == null) {
            // If the trade already exists, nothing to do here
            log.error("{} accepted a trade request but no trade exists", acceptingUserId);
            throw new TradeException("This trade is not active.");
        }
        if (!existingTrade.getUser2Id().equals(acceptingUserId)) {
            // If the trade already exists, nothing to do here
            log.error(
                    "{} attempted to accept a trade that they created themselves", acceptingUserId);
            throw new TradeException("This trade is not active.");
        }
        existingTrade.setUser2ConfirmedTrade(true);
        Trade updTrade = tradeRepository.update(existingTrade);
        return updTrade;
    }

    /**
     * Offer a set of pokemon as part of an active trade between two users.
     *
     * <p>Only valid if the user has not yet offered Pokemon.
     *
     * @param fromUserId sender of the offer
     * @param pokemonToOffer pokemon offered
     * @return current Trade object
     */
    @Nonnull
    public Trade offerPokemon(String fromUserId, ObjectId pokemonToOffer) throws TradeException {
        Trade existingTrade = getTrade(fromUserId);

        if (existingTrade == null) {
            // If the trade already exists, nothing to do here
            log.error("{} offered pokemon on a trade but no trade exists", fromUserId);
            throw new TradeException("This trade is not active.");
        }

        if (!existingTrade.getUser2ConfirmedTrade()) {
            log.error("{} offered pokemon on a trade which hasn't started yet", fromUserId);
            throw new TradeException("The trade hasn't started.");
        }

        if (existingTrade.getUser1Id().equals(fromUserId)) {
            ObjectId existingOffer = existingTrade.getUser1Offer();
            if (existingOffer != null) {
                log.error("{} offered pokemon on a trade more than once", fromUserId);
                throw new TradeException("Cannot change/update offer on a trade");
            }
            log.info("{} (user 1) offered to trade {}", fromUserId, pokemonToOffer);
            existingTrade.setUser1Offer(pokemonToOffer);
        } else {
            // getUser2Id().equals(fromUserId)
            ObjectId existingOffer = existingTrade.getUser2Offer();
            if (existingOffer != null) {
                log.error("{} offered pokemon on a trade more than once", fromUserId);
                throw new TradeException("Cannot change/update offer on a trade");
            }
            log.info("{} (user 2) offered to trade {}", fromUserId, pokemonToOffer);
            existingTrade.setUser2Offer(pokemonToOffer);
        }

        tradeRepository.update(existingTrade);
        return existingTrade;
    }

    /**
     * Accepts the trade if it exists
     *
     * @param acceptingUserId a user to accept the trade
     * @return
     */
    @Nonnull
    public Trade acceptTrade(String acceptingUserId) throws TradeException {
        Trade existingTrade = getTrade(acceptingUserId);

        if (existingTrade == null) {
            // If the trade already exists, nothing to do here
            log.error("{} accepted a trade but no trade exists", acceptingUserId);
            throw new TradeException("This trade is not active.");
        }

        if (existingTrade.getUser1Id().equals(acceptingUserId)) {
            Boolean accepted = existingTrade.getUser1Accept();
            if (accepted) {
                log.error("{} accepted the same trade more than once", acceptingUserId);
                throw new TradeException("You have already accepted the trade");
            }
            existingTrade.setUser1Accept(true);
        } else {
            // getUser2Id().equals(fromUserId)
            Boolean accepted = existingTrade.getUser2Accept();
            if (accepted) {
                log.error("{} accepted the same trade more than once", acceptingUserId);
                throw new TradeException("You have already accepted the trade");
            }
            existingTrade.setUser2Accept(true);
        }

        tradeRepository.update(existingTrade);
        return existingTrade;
    }

    /**
     * Cancel the trade between these two users if it exists.
     *
     * @param cancellingUserId the user that requested cancellation
     * @return trade to be cancelled
     */
    public Trade cancelTrade(String cancellingUserId) throws TradeException {
        Trade existingTrade = getTrade(cancellingUserId);

        if (existingTrade == null) {
            log.error("{} tried to cancel a trade that does not exist", cancellingUserId);
            throw new TradeException("The trade is not active.");
        }

        log.info("{} cancelled their trade", cancellingUserId);

        tradeRepository.delete(existingTrade.getId());
        return existingTrade;
    }

    /**
     * Completes the trade between two users.
     *
     * @param tradeId an ID of the trade between two users
     * @return completed Trade
     * @throws TradeException
     */
    public Trade completeTrade(ObjectId tradeId) throws TradeException {
        Trade trade = tradeRepository.get(tradeId);
        if (trade == null) {
            log.error("Attempt to complete a trade that does not exist");
            throw new TradeException("The trade is not active.");
        }
        ObjectId user1Offer = trade.getUser1Offer();
        ObjectId user2Offer = trade.getUser2Offer();
        if (user1Offer == null || user2Offer == null) {
            log.error("Attempt to complete a trade where some offers are missing");
            throw new TradeException("The trade is not active.");
        }
        if (!trade.getUser1Accept() || !trade.getUser2Accept()) {
            log.error("Attempt to complete a trade where not everybody has confirmed it");
            throw new TradeException("The trade is not active.");
        }
        ArrayList<ObjectId> user1Party = inventoryController.getPartyForUser(trade.getUser1Id());
        ArrayList<ObjectId> user2Party = inventoryController.getPartyForUser(trade.getUser2Id());
        if (!user1Party.contains(user1Offer)) {
            log.error("User 1 no longer possesses their pokemon");
            throw new ForceCancelTradeException(
                    trade.getUser1Id(),
                    trade.getUser2Id(),
                    "You no longer possess the pokemon you offered. The trade will be cancelled.",
                    "The other party no longer possess the pokemon they offered. The trade will be cancelled.");
        }
        if (!user2Party.contains(user2Offer)) {
            log.error("User 2 no longer possesses their pokemon");
            throw new ForceCancelTradeException(
                    trade.getUser2Id(),
                    trade.getUser1Id(),
                    "You no longer possess the pokemon you offered. The trade will be cancelled.",
                    "The other party no longer possesses the pokemon they offered. The trade will be cancelled.");
        }
        user1Party.remove(user1Offer);
        user2Party.remove(user2Offer);
        user1Party.add(user2Offer);
        user2Party.add(user1Offer);
        inventoryController.setPartyForUser(trade.getUser1Id(), user1Party);
        inventoryController.setPartyForUser(trade.getUser2Id(), user2Party);
        tradeRepository.delete(tradeId);
        return trade;
    }

    /**
     * Stores ID of Message that is pending.
     *
     * @param trade current trade
     * @param messageId message id that needs to be stored
     */
    public void addPendingMessageId(ObjectId tradeId, String messageId) {
        Trade trade = tradeRepository.get(tradeId); // Fetch the latest data
        if (trade != null && trade.getPendingMessageIds().add(messageId)) {
            tradeRepository.update(trade);
        }
    }

    /**
     * Removes ID of Message that is pending.
     *
     * @param trade current trade
     * @param messageId message id that needs to be removed
     */
    public void removePendingMessageId(ObjectId tradeId, String messageId) {
        Trade trade = tradeRepository.get(tradeId); // Fetch the latest data
        if (trade.getPendingMessageIds().remove(messageId)) {
            tradeRepository.update(trade);
        }
    }
}
