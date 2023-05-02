package edu.northeastern.cs5500.starterbot.controller;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class UserInventoryController_MembersInjector implements MembersInjector<UserInventoryController> {
  private final Provider<PokemonController> pokemonControllerProvider;

  public UserInventoryController_MembersInjector(
      Provider<PokemonController> pokemonControllerProvider) {
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  public static MembersInjector<UserInventoryController> create(
      Provider<PokemonController> pokemonControllerProvider) {
    return new UserInventoryController_MembersInjector(pokemonControllerProvider);
  }

  @Override
  public void injectMembers(UserInventoryController instance) {
    injectPokemonController(instance, pokemonControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.controller.UserInventoryController.pokemonController")
  public static void injectPokemonController(UserInventoryController instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }
}
