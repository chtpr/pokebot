package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.junit.jupiter.api.Test;

class ChooseStarterCommandTest {

    static final int NUMBER_OF_CHOOSE_STARTER_BUTTONS = 3;
    static final int NUMBER_OF_ACTION_ROWS = 1;

    @Test
    void testEmbedMatchesChooseStarterEmbed() {
        ChooseStarterCommand chooseStarterCommand = new ChooseStarterCommand();
        Message actualMessage = chooseStarterCommand.generateChooseStarterMessage();

        assertThat(actualMessage.getActionRows().size()).isEqualTo(NUMBER_OF_ACTION_ROWS);
        assertThat(actualMessage.getButtons().size()).isEqualTo(NUMBER_OF_CHOOSE_STARTER_BUTTONS);

        MessageEmbed embed = actualMessage.getEmbeds().get(0);
        assertThat(embed.getTitle()).isNotEmpty();
        assertThat(embed.getDescription()).isNotEmpty();
        assertThat(embed.getImage().toString()).isNotEmpty();
    }
}
