package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.junit.jupiter.api.Test;

class TradeCommandTest {
    @Test
    void testNameMatchesData() {
        TradeCommand tradeCommand = new TradeCommand();
        String name = tradeCommand.getName();
        CommandData commandData = tradeCommand.getCommandData();
        assertThat(name).isEqualTo(commandData.getName());
    }

    @Test
    void testEmbedMatchesTradeCommandEmbed() {
        TradeCommand tradeCommand = new TradeCommand();
        MessageEmbed actual = tradeCommand.renderTradeRequest("User1");
        assertThat(actual.getDescription()).isNotEmpty();
    }

    @Test
    void testTradeMessageTradeCommandEmbed() {
        TradeCommand tradeCommand = new TradeCommand();
        Message actual = tradeCommand.renderTradeMessage("User1");
        assertThat(actual.getActionRows()).isNotEmpty();
    }
}
