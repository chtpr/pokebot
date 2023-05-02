package edu.northeastern.cs5500.starterbot.utils;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class BattleMessageUtil_MembersInjector implements MembersInjector<BattleMessageUtil> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public BattleMessageUtil_MembersInjector(Provider<BattleController> battleControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  public static MembersInjector<BattleMessageUtil> create(
      Provider<BattleController> battleControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new BattleMessageUtil_MembersInjector(battleControllerProvider, userInventoryControllerProvider, pokemonControllerProvider);
  }

  @Override
  public void injectMembers(BattleMessageUtil instance) {
    injectBattleController(instance, battleControllerProvider.get());
    injectUserInventoryController(instance, userInventoryControllerProvider.get());
    injectPokemonController(instance, pokemonControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil.battleController")
  public static void injectBattleController(BattleMessageUtil instance,
      BattleController battleController) {
    instance.battleController = battleController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil.userInventoryController")
  public static void injectUserInventoryController(BattleMessageUtil instance,
      UserInventoryController userInventoryController) {
    instance.userInventoryController = userInventoryController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil.pokemonController")
  public static void injectPokemonController(BattleMessageUtil instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }
}
