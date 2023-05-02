package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil;
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
public final class BattleSelectionMenuHandler_MembersInjector implements MembersInjector<BattleSelectionMenuHandler> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleSelectionMenuHandler_MembersInjector(
      Provider<BattleController> battleControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  public static MembersInjector<BattleSelectionMenuHandler> create(
      Provider<BattleController> battleControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleSelectionMenuHandler_MembersInjector(battleControllerProvider, pokemonControllerProvider, battleMessageUtilProvider);
  }

  @Override
  public void injectMembers(BattleSelectionMenuHandler instance) {
    injectBattleController(instance, battleControllerProvider.get());
    injectPokemonController(instance, pokemonControllerProvider.get());
    injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleSelectionMenuHandler.battleController")
  public static void injectBattleController(BattleSelectionMenuHandler instance,
      BattleController battleController) {
    instance.battleController = battleController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleSelectionMenuHandler.pokemonController")
  public static void injectPokemonController(BattleSelectionMenuHandler instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleSelectionMenuHandler.battleMessageUtil")
  public static void injectBattleMessageUtil(BattleSelectionMenuHandler instance,
      BattleMessageUtil battleMessageUtil) {
    instance.battleMessageUtil = battleMessageUtil;
  }
}
