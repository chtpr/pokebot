package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

class TradeButtonClickHandleDeclineTest {
    @Test
    void testNameMatchesData() {
        TradeButtonClickHandleDecline tradeButtonClickHandler = new TradeButtonClickHandleDecline();
        String name = tradeButtonClickHandler.getName();
        String expected = "decline_trade";
        assertThat(name).isEqualTo(expected);
    }
}
