package edu.northeastern.cs5500.starterbot.utils;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
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
public final class TradeMessageUtil_MembersInjector implements MembersInjector<TradeMessageUtil> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<UserInventoryController> inventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public TradeMessageUtil_MembersInjector(Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.inventoryControllerProvider = inventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  public static MembersInjector<TradeMessageUtil> create(
      Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new TradeMessageUtil_MembersInjector(tradeControllerProvider, inventoryControllerProvider, pokemonControllerProvider);
  }

  @Override
  public void injectMembers(TradeMessageUtil instance) {
    injectTradeController(instance, tradeControllerProvider.get());
    injectInventoryController(instance, inventoryControllerProvider.get());
    injectPokemonController(instance, pokemonControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil.tradeController")
  public static void injectTradeController(TradeMessageUtil instance,
      TradeController tradeController) {
    instance.tradeController = tradeController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil.inventoryController")
  public static void injectInventoryController(TradeMessageUtil instance,
      UserInventoryController inventoryController) {
    instance.inventoryController = inventoryController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil.pokemonController")
  public static void injectPokemonController(TradeMessageUtil instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }
}
