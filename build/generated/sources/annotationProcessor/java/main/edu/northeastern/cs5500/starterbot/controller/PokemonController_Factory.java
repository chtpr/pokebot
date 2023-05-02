package edu.northeastern.cs5500.starterbot.controller;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
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
public final class PokemonController_Factory implements Factory<PokemonController> {
  private final Provider<GenericRepository<Pokemon>> pokemonRepositoryProvider;

  private final Provider<PokedexController> pokedexControllerProvider;

  public PokemonController_Factory(Provider<GenericRepository<Pokemon>> pokemonRepositoryProvider,
      Provider<PokedexController> pokedexControllerProvider) {
    this.pokemonRepositoryProvider = pokemonRepositoryProvider;
    this.pokedexControllerProvider = pokedexControllerProvider;
  }

  @Override
  public PokemonController get() {
    return newInstance(pokemonRepositoryProvider.get(), pokedexControllerProvider.get());
  }

  public static PokemonController_Factory create(
      Provider<GenericRepository<Pokemon>> pokemonRepositoryProvider,
      Provider<PokedexController> pokedexControllerProvider) {
    return new PokemonController_Factory(pokemonRepositoryProvider, pokedexControllerProvider);
  }

  public static PokemonController newInstance(GenericRepository<Pokemon> pokemonRepository,
      PokedexController pokedexController) {
    return new PokemonController(pokemonRepository, pokedexController);
  }
}
