package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.junit.jupiter.api.Test;

class GreetNewUserCommandTest {
    @Test
    void testEmbedMatchesGreetEmbed() {
        GreetNewUserCommand greetNewUserCommand = new GreetNewUserCommand();
        MessageEmbed embed = greetNewUserCommand.generateGreetUserEmbed();

        assertThat(embed.getTitle()).isNotEmpty();
        assertThat(embed.getDescription()).isNotEmpty();
        assertThat(embed.getImage().toString()).isNotEmpty();
    }
}
