package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class BattleButtonClickHandleCancelTest {
    @Test
    void testNameMatchesData() {
        BattleButtonClickHandleCancel battleButtonClickHandler =
                new BattleButtonClickHandleCancel();
        String name = battleButtonClickHandler.getName();
        assertThat(name).isEqualTo(BattleButtonClickHandleCancel.CANCEL_BATTLE);
    }
}
