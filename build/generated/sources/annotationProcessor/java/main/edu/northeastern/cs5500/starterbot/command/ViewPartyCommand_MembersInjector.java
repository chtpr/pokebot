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
public final class ViewPartyCommand_MembersInjector implements MembersInjector<ViewPartyCommand> {
  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public ViewPartyCommand_MembersInjector(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  public static MembersInjector<ViewPartyCommand> create(
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new ViewPartyCommand_MembersInjector(userInventoryControllerProvider, pokemonControllerProvider);
  }

  @Override
  public void injectMembers(ViewPartyCommand instance) {
    injectUserInventoryController(instance, userInventoryControllerProvider.get());
    injectPokemonController(instance, pokemonControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.ViewPartyCommand.userInventoryController")
  public static void injectUserInventoryController(ViewPartyCommand instance,
      UserInventoryController userInventoryController) {
    instance.userInventoryController = userInventoryController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.ViewPartyCommand.pokemonController")
  public static void injectPokemonController(ViewPartyCommand instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }
}
