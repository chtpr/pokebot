package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import edu.northeastern.cs5500.starterbot.exception.TradeException;
import edu.northeastern.cs5500.starterbot.model.Trade;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

public class TradeControllerTest {
    static final String USER_1_ID = "23h5ikoqaehokljhaoe";
    static final String USER_2_ID = "2kjfksdjdkhokljhaoe";
    static final String USER_3_ID = "29kjfksdjdkhokljhaoe";

    private TradeController getTradeController() {
        TradeController tradeController =
                new TradeController(
                        new InMemoryRepository<Trade>(),
                        new UserInventoryController(new InMemoryRepository<>()));
        return tradeController;
    }

    @Test
    void testCannotTradeWithSelf() {
        try {
            TradeController tradeController = getTradeController();
            tradeController.beginTrade(USER_1_ID, USER_1_ID);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("You cannot trade with yourself!");
        }
    }

    @Test
    void testCannotTradeUser1GotNoParty() {
        try {
            TradeController tradeController = getTradeController();
            List<ObjectId> user2Party =
                    tradeController.inventoryController.getPartyForUser(USER_2_ID);
            assertThat(user2Party).isEmpty();
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            assertThat(trade).isNotNull();
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("You don't have any pokemons to trade");
        }
    }

    @Test
    void testCannotTradeUser2GotNoParty() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list = new ArrayList<>();
            ObjectId object = new ObjectId();
            list.add(object);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list);
            List<ObjectId> user1Party =
                    tradeController.inventoryController.getPartyForUser(USER_1_ID);
            assertThat(user1Party).isNotNull();
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            assertThat(trade).isNotNull();
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("That trainer doesn't have any pokemons to trade");
        }
    }

    @Test
    void testBeginTradeSuccess() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
        Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        assertThat(trade).isNotNull();
        ArrayList<Trade> list = tradeController.getAllTrades();
        assertThat(list).isNotEmpty();
    }

    @Test
    void testGetNotFoundTrade() {
        TradeController tradeController = getTradeController();
        assertThat(tradeController.getTrade(USER_1_ID)).isNull();
    }

    @Test
    void testSuccessfullyGetTrade1() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
        Trade expectedTrade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        Trade actualTrade = tradeController.getTrade(USER_1_ID);
        assertThat(expectedTrade).isEqualTo(actualTrade);
    }

    @Test
    void testSuccessfullyGetTrade2() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
        Trade expectedTrade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        Trade actualTrade = tradeController.getTrade(USER_2_ID);
        assertThat(expectedTrade).isEqualTo(actualTrade);
    }

    @Test
    void testAcceptTradeRequestOnTradeNotExist() throws TradeException {

        try {
            TradeController tradeController = getTradeController();
            tradeController.acceptTradeRequest(USER_1_ID);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("This trade is not active.");
        }
    }

    @Test
    void testAcceptTradeRequestSuccess() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
        Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        assertThat(trade).isNotNull();
        tradeController.acceptTradeRequest(USER_2_ID);
    }

    @Test
    void testOfferPokemonOnNonexistTrade() {
        try {
            TradeController tradeController = getTradeController();
            ObjectId pokemon = new ObjectId();
            tradeController.offerPokemon(USER_1_ID, pokemon);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("This trade is not active.");
        }
    }

    @Test
    void testOfferPokemonOnNotConfirmedTrade() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            assertThat(trade).isNotNull();
            tradeController.offerPokemon(USER_2_ID, object2);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("The trade hasn't started.");
        }
    }

    @Test
    void testOfferPokemonSuccess() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
        Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        trade.setUser2ConfirmedTrade(true);
        assertThat(trade).isNotNull();
        tradeController.offerPokemon(USER_1_ID, object2);
    }

    @Test
    void testAcceptNonexistTrade() {
        try {
            TradeController tradeController = getTradeController();
            ObjectId pokemon = new ObjectId();
            assertThat(pokemon).isNotNull();
            tradeController.acceptTrade(USER_1_ID);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("This trade is not active.");
        }
    }

    @Test
    void testUser1AcceptsTradeAgain() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            trade.setUser1Accept(true);
            tradeController.acceptTrade(USER_1_ID);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("You have already accepted the trade");
        }
    }

    @Test
    void testUser2AcceptsTradeAgain() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            trade.setUser2Accept(true);
            tradeController.acceptTrade(USER_2_ID);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("You have already accepted the trade");
        }
    }

    @Test
    void testAcceptTradeSuccess() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> party1 = new ArrayList<>();
        ArrayList<ObjectId> party2 = new ArrayList<>();
        ObjectId pokemon1 = new ObjectId();
        ObjectId pokemon2 = new ObjectId();
        party1.add(pokemon1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, party1);
        party2.add(pokemon2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, party2);
        Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        trade.setUser1Offer(pokemon1);
        trade.setUser2Offer(pokemon2);
        trade.setUser1Accept(true);
        tradeController.acceptTrade(USER_2_ID);
    }

    @Test
    void testCancelNotExistTrade() {
        try {
            TradeController tradeController = getTradeController();
            ObjectId pokemon = new ObjectId();
            assertThat(pokemon).isNotNull();
            tradeController.cancelTrade(USER_1_ID);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("The trade is not active.");
        }
    }

    @Test
    void testCancelTradeSuccess() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
        Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        trade.setUser1Accept(true);
        tradeController.cancelTrade(USER_2_ID);
    }

    @Test
    void testCannotCompleteTradeWhichNotExist() {
        try {
            TradeController tradeController = getTradeController();
            ObjectId tradeId = new ObjectId();
            tradeController.completeTrade(tradeId);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("The trade is not active.");
        }
    }

    @Test
    void testCannotCompleteTradeOfferFromUser2Missing() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            trade.setUser2Offer(null);
            tradeController.completeTrade(trade.getId());
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("The trade is not active.");
        }
    }

    @Test
    void testCannotCompleteTradeOfferFromUserMissing() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            trade.setUser1Offer(null);
            tradeController.completeTrade(trade.getId());
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("The trade is not active.");
        }
    }

    @Test
    void testCannotCompleteTradeMissingConfirmation() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            trade.setUser1Offer(object1);
            trade.setUser2Offer(object2);
            trade.setUser2Accept(false);
            tradeController.completeTrade(trade.getId());
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isEqualTo("The trade is not active.");
        }
    }

    @Test
    void testCannotCompleteTradeUser2NoLongerHavePokemon() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            ObjectId tradeId = trade.getId();
            trade.setUser1Offer(object1);
            trade.setUser2Offer(object2);
            trade.setUser1Accept(true);
            trade.setUser2Accept(true);
            list2.clear();
            tradeController.completeTrade(tradeId);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    void testCannotCompleteTradeUser1NoLongerHavePokemon() {
        try {
            TradeController tradeController = getTradeController();
            ArrayList<ObjectId> list1 = new ArrayList<>();
            ArrayList<ObjectId> list2 = new ArrayList<>();
            ObjectId object1 = new ObjectId();
            ObjectId object2 = new ObjectId();
            list1.add(object1);
            tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
            list2.add(object2);
            tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
            Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
            ObjectId tradeId = trade.getId();
            trade.setUser1Offer(object1);
            trade.setUser2Offer(object2);
            trade.setUser1Accept(true);
            trade.setUser2Accept(true);
            list1.clear();
            tradeController.completeTrade(tradeId);
            fail("Expected TradeException, but no exception was thrown");
        } catch (TradeException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    void testCompleteTradeSuccess() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
        Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        ObjectId tradeId = trade.getId();
        trade.setUser1Offer(object1);
        trade.setUser2Offer(object2);
        trade.setUser1Accept(true);
        trade.setUser2Accept(true);
        tradeController.completeTrade(tradeId);
        assertThat(tradeController.getTrade(USER_1_ID)).isNull();
    }

    @Test
    void testPokemonsSwitchedProperly() throws TradeException {
        TradeController tradeController = getTradeController();
        ArrayList<ObjectId> list1 = new ArrayList<>();
        ArrayList<ObjectId> list2 = new ArrayList<>();
        ObjectId object1 = new ObjectId();
        ObjectId object2 = new ObjectId();
        list1.add(object1);
        tradeController.inventoryController.setPartyForUser(USER_1_ID, list1);
        list2.add(object2);
        tradeController.inventoryController.setPartyForUser(USER_2_ID, list2);
        Trade trade = tradeController.beginTrade(USER_1_ID, USER_2_ID);
        ObjectId tradeId = trade.getId();
        trade.setUser1Offer(object1);
        trade.setUser2Offer(object2);
        trade.setUser1Accept(true);
        trade.setUser2Accept(true);
        tradeController.completeTrade(tradeId);
        assertThat(tradeController.inventoryController.getPartyForUser(USER_1_ID).contains(object2))
                .isTrue();
        assertThat(tradeController.inventoryController.getPartyForUser(USER_2_ID).contains(object1))
                .isTrue();
    }
}
