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
public final class DeletePokemonCommand_Factory implements Factory<DeletePokemonCommand> {
  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public DeletePokemonCommand_Factory(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  @Override
  public DeletePokemonCommand get() {
    return newInstance(userInventoryControllerProvider.get(), pokemonControllerProvider.get());
  }

  public static DeletePokemonCommand_Factory create(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new DeletePokemonCommand_Factory(userInventoryControllerProvider, pokemonControllerProvider);
  }

  public static DeletePokemonCommand newInstance(UserInventoryController userInventoryController,
      PokemonController pokemonController) {
    return new DeletePokemonCommand(userInventoryController, pokemonController);
  }
}
