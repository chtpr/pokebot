package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

class TradeButtonClickHandleCancelTest {
    @Test
    void testNameMatchesData() {
        TradeButtonClickHandleCancel tradeButtonClickHandler = new TradeButtonClickHandleCancel();
        String name = tradeButtonClickHandler.getName();
        String expected = "cancel_trade";
        assertThat(name).isEqualTo(expected);
    }
}
