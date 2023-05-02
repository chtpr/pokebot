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
public final class InspectPokemonCommand_Factory implements Factory<InspectPokemonCommand> {
  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public InspectPokemonCommand_Factory(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  @Override
  public InspectPokemonCommand get() {
    InspectPokemonCommand instance = newInstance();
    InspectPokemonCommand_MembersInjector.injectUserInventoryController(instance, userInventoryControllerProvider.get());
    InspectPokemonCommand_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    return instance;
  }

  public static InspectPokemonCommand_Factory create(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new InspectPokemonCommand_Factory(userInventoryControllerProvider, pokemonControllerProvider);
  }

  public static InspectPokemonCommand newInstance() {
    return new InspectPokemonCommand();
  }
}
