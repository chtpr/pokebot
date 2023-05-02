package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class BattleButtonClickHandleDeclineTest {
    @Test
    void testNameMatchesData() {
        BattleButtonClickHandleDecline battleButtonClickHandler =
                new BattleButtonClickHandleDecline();
        String name = battleButtonClickHandler.getName();
        assertThat(name).isEqualTo(BattleButtonClickHandleDecline.DECLINE_BATTLE);
    }
}
