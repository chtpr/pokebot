package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.junit.jupiter.api.Test;

class BeginPlayingCommandTest {
    @Test
    void testEmbedMatchesBeginPlayingEmbed() {
        BeginPlayingCommand beginPlayingCommand = new BeginPlayingCommand();
        MessageEmbed embed = beginPlayingCommand.generateBeginPlayingEmbed();

        assertThat(embed.getTitle()).isNotEmpty();
        assertThat(embed.getDescription()).isNotEmpty();
        assertThat(embed.getImage().toString()).isNotEmpty();
    }
}
