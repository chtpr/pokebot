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
public final class ChooseStarterButtonClick_Factory implements Factory<ChooseStarterButtonClick> {
  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public ChooseStarterButtonClick_Factory(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  @Override
  public ChooseStarterButtonClick get() {
    return newInstance(userInventoryControllerProvider.get(), pokemonControllerProvider.get());
  }

  public static ChooseStarterButtonClick_Factory create(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new ChooseStarterButtonClick_Factory(userInventoryControllerProvider, pokemonControllerProvider);
  }

  public static ChooseStarterButtonClick newInstance(
      UserInventoryController userInventoryController, PokemonController pokemonController) {
    return new ChooseStarterButtonClick(userInventoryController, pokemonController);
  }
}
