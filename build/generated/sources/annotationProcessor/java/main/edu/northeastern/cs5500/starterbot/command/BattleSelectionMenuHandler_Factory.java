package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class BattleSelectionMenuHandler_Factory implements Factory<BattleSelectionMenuHandler> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleSelectionMenuHandler_Factory(Provider<BattleController> battleControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  @Override
  public BattleSelectionMenuHandler get() {
    BattleSelectionMenuHandler instance = newInstance();
    BattleSelectionMenuHandler_MembersInjector.injectBattleController(instance, battleControllerProvider.get());
    BattleSelectionMenuHandler_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    BattleSelectionMenuHandler_MembersInjector.injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
    return instance;
  }

  public static BattleSelectionMenuHandler_Factory create(
      Provider<BattleController> battleControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleSelectionMenuHandler_Factory(battleControllerProvider, pokemonControllerProvider, battleMessageUtilProvider);
  }

  public static BattleSelectionMenuHandler newInstance() {
    return new BattleSelectionMenuHandler();
  }
}
