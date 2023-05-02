package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
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
public final class CatchButtonClick_Factory implements Factory<CatchButtonClick> {
  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public CatchButtonClick_Factory(Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  @Override
  public CatchButtonClick get() {
    CatchButtonClick instance = newInstance();
    CatchButtonClick_MembersInjector.injectUserInventoryController(instance, userInventoryControllerProvider.get());
    CatchButtonClick_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    return instance;
  }

  public static CatchButtonClick_Factory create(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new CatchButtonClick_Factory(userInventoryControllerProvider, pokemonControllerProvider);
  }

  public static CatchButtonClick newInstance() {
    return new CatchButtonClick();
  }
}
