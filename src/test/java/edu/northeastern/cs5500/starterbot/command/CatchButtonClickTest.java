package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import java.util.ArrayList;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.junit.jupiter.api.Test;

public class CatchButtonClickTest {
    public static Pokemon generateBulbasaur() {
        ArrayList<String> pokemonTypes = new ArrayList<>();
        pokemonTypes.add("grass");
        pokemonTypes.add("poison");
        Pokemon bulbasaur = new Pokemon();
        bulbasaur.setPokedexNumber(1);
        bulbasaur.setName("Bulbasaur");
        bulbasaur.setPokemonTypes(pokemonTypes);
        bulbasaur.setGender("Male");
        bulbasaur.setLevel(1);
        bulbasaur.setExperiencePoints(0);
        bulbasaur.setFainted(false);

        return bulbasaur;
    }

    @Test
    void testPokemonJoinedPartyMessage() {
        CatchButtonClick buttonClick = new CatchButtonClick();
        Message actualMessage = buttonClick.pokemonJoinedPartyMessage(generateBulbasaur());
        Pokemon bulbasaur = generateBulbasaur();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        String title = (String.format("%s has joined your party!", bulbasaur.getName()));
        embedBuilder.setTitle(title);
        embedBuilder.addField("Type: ", bulbasaur.getPokemonTypes().get(0), true);
        if (bulbasaur.getPokemonTypes().size() > 1) {
            embedBuilder.addField("Secondary Type: ", bulbasaur.getPokemonTypes().get(1), true);
        }
        embedBuilder.addField("Gender: ", bulbasaur.getGender(), true);
        embedBuilder.addField("Level: ", String.valueOf(bulbasaur.getLevel()), true);
        MessageEmbed embed = embedBuilder.build();

        MessageBuilder messageBuilder = new MessageBuilder();
        Message expectedMessage = messageBuilder.setEmbeds(embed).build();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testGeneratePokemonAddedConfirmationMessage() {
        CatchButtonClick buttonClick = new CatchButtonClick();
        Message actualMessage = buttonClick.pokemonReleasedMessage();

        Message expectedMessage =
                new MessageBuilder().setContent("Pokemon has been released").build();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
