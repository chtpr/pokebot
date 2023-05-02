package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class InspectPokemonCommand_MembersInjector implements MembersInjector<InspectPokemonCommand> {
  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public InspectPokemonCommand_MembersInjector(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  public static MembersInjector<InspectPokemonCommand> create(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new InspectPokemonCommand_MembersInjector(userInventoryControllerProvider, pokemonControllerProvider);
  }

  @Override
  public void injectMembers(InspectPokemonCommand instance) {
    injectUserInventoryController(instance, userInventoryControllerProvider.get());
    injectPokemonController(instance, pokemonControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.InspectPokemonCommand.userInventoryController")
  public static void injectUserInventoryController(InspectPokemonCommand instance,
      UserInventoryController userInventoryController) {
    instance.userInventoryController = userInventoryController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.InspectPokemonCommand.pokemonController")
  public static void injectPokemonController(InspectPokemonCommand instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }
}
