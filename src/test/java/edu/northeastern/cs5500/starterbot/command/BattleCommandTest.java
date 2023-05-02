package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.junit.jupiter.api.Test;

public class BattleCommandTest {

    private static final String TEST_USER = "testUser";

    @Test
    void testNameMatchesData() {
        BattleCommand battleCommand = new BattleCommand();
        String name = battleCommand.getName();
        CommandData commandData = battleCommand.getCommandData();
        assertThat(name).isEqualTo(commandData.getName());
    }

    @Test
    void testEmbedMatchesBattleCommandEmbed() {
        BattleCommand battleCommand = new BattleCommand();
        MessageEmbed actual = battleCommand.renderBattleRequest(TEST_USER);
        assertThat(actual.getDescription()).isNotEmpty();
    }

    @Test
    void testBattleMessageBattleCommandEmbed() {
        BattleCommand battleCommand = new BattleCommand();
        Message actual = battleCommand.renderBattleMessage(TEST_USER);
        assertThat(actual.getActionRows()).isNotEmpty();
    }
}
