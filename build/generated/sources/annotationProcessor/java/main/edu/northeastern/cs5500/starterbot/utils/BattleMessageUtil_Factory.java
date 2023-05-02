package edu.northeastern.cs5500.starterbot.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
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
public final class BattleMessageUtil_Factory implements Factory<BattleMessageUtil> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public BattleMessageUtil_Factory(Provider<BattleController> battleControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  @Override
  public BattleMessageUtil get() {
    BattleMessageUtil instance = newInstance();
    BattleMessageUtil_MembersInjector.injectBattleController(instance, battleControllerProvider.get());
    BattleMessageUtil_MembersInjector.injectUserInventoryController(instance, userInventoryControllerProvider.get());
    BattleMessageUtil_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    return instance;
  }

  public static BattleMessageUtil_Factory create(
      Provider<BattleController> battleControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new BattleMessageUtil_Factory(battleControllerProvider, userInventoryControllerProvider, pokemonControllerProvider);
  }

  public static BattleMessageUtil newInstance() {
    return new BattleMessageUtil();
  }
}
