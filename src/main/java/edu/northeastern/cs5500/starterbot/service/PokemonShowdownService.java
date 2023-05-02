package edu.northeastern.cs5500.starterbot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@Singleton
@Slf4j
public class PokemonShowdownService implements Service {
    public static final String POKEMON_SHOWDOWN_POKEDEX_URI =
            "https://play.pokemonshowdown.com/data/pokedex.json";

    // Fetching the entire pokedex is expensive so let's only do it once
    static Map<String, PokemonShowdownEntry> pokedexCache = null;

    @Inject
    public PokemonShowdownService() {}

    public void register() {
        log.info("Registered PokemonShowdownService");
    }

    @Nullable
    public Map<String, PokemonShowdownEntry> fetchPokedex() {
        if (pokedexCache != null) {
            log.info("Returned Pokedex from cache");
            return pokedexCache;
        }

        try {
            log.info("Downloading Pokedex");
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response =
                    client.execute(new HttpGet(POKEMON_SHOWDOWN_POKEDEX_URI));
            String bodyAsString = EntityUtils.toString(response.getEntity());

            ObjectMapper mapper =
                    new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            TypeReference<HashMap<String, PokemonShowdownEntry>> typeRef =
                    new TypeReference<HashMap<String, PokemonShowdownEntry>>() {};
            Map<String, PokemonShowdownEntry> pokedex = mapper.readValue(bodyAsString, typeRef);

            pokedexCache = pokedex;
            return pokedex;
        } catch (ParseException | IOException e) {
            log.error("Unable to get data from POKEMON_SHOWDOWN_POKEDEX_URI: {}", e);
        }
        return null;
    }
}
