package edu.northeastern.cs5500.starterbot.controller;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.service.PokemonShowdownService;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PokedexController_Factory implements Factory<PokedexController> {
  private final Provider<GenericRepository<PokedexEntry>> pokedexRepositoryProvider;

  private final Provider<PokemonShowdownService> pokemonShowdownServiceProvider;

  public PokedexController_Factory(
      Provider<GenericRepository<PokedexEntry>> pokedexRepositoryProvider,
      Provider<PokemonShowdownService> pokemonShowdownServiceProvider) {
    this.pokedexRepositoryProvider = pokedexRepositoryProvider;
    this.pokemonShowdownServiceProvider = pokemonShowdownServiceProvider;
  }

  @Override
  public PokedexController get() {
    return newInstance(pokedexRepositoryProvider.get(), pokemonShowdownServiceProvider.get());
  }

  public static PokedexController_Factory create(
      Provider<GenericRepository<PokedexEntry>> pokedexRepositoryProvider,
      Provider<PokemonShowdownService> pokemonShowdownServiceProvider) {
    return new PokedexController_Factory(pokedexRepositoryProvider, pokemonShowdownServiceProvider);
  }

  public static PokedexController newInstance(GenericRepository<PokedexEntry> pokedexRepository,
      PokemonShowdownService pokemonShowdownService) {
    return new PokedexController(pokedexRepository, pokemonShowdownService);
  }
}
