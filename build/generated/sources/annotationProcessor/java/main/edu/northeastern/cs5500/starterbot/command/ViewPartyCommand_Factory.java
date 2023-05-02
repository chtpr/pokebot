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
public final class ViewPartyCommand_Factory implements Factory<ViewPartyCommand> {
  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public ViewPartyCommand_Factory(Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  @Override
  public ViewPartyCommand get() {
    ViewPartyCommand instance = newInstance();
    ViewPartyCommand_MembersInjector.injectUserInventoryController(instance, userInventoryControllerProvider.get());
    ViewPartyCommand_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    return instance;
  }

  public static ViewPartyCommand_Factory create(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new ViewPartyCommand_Factory(userInventoryControllerProvider, pokemonControllerProvider);
  }

  public static ViewPartyCommand newInstance() {
    return new ViewPartyCommand();
  }
}
