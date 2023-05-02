package edu.northeastern.cs5500.starterbot.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TradeMessageUtil_Factory implements Factory<TradeMessageUtil> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<UserInventoryController> inventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public TradeMessageUtil_Factory(Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.inventoryControllerProvider = inventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  @Override
  public TradeMessageUtil get() {
    TradeMessageUtil instance = newInstance();
    TradeMessageUtil_MembersInjector.injectTradeController(instance, tradeControllerProvider.get());
    TradeMessageUtil_MembersInjector.injectInventoryController(instance, inventoryControllerProvider.get());
    TradeMessageUtil_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    return instance;
  }

  public static TradeMessageUtil_Factory create(Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new TradeMessageUtil_Factory(tradeControllerProvider, inventoryControllerProvider, pokemonControllerProvider);
  }

  public static TradeMessageUtil newInstance() {
    return new TradeMessageUtil();
  }
}
