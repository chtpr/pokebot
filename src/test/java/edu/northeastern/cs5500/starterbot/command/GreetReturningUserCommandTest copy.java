package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.junit.jupiter.api.Test;

class GreetReturningUserCommandTest {
    @Test
    void testEmbedMatchesGreetEmbed() {
        final String userName = "some_username";
        GreetReturningUserCommand greetReturningUserCommand = new GreetReturningUserCommand();
        MessageEmbed embed = greetReturningUserCommand.generateGreetUserEmbed(userName);

        assertThat(embed.getTitle()).isNotEmpty();
        assertThat(embed.getDescription()).isNotEmpty();
        assertThat(embed.getImage().toString()).isNotEmpty();
    }
}
