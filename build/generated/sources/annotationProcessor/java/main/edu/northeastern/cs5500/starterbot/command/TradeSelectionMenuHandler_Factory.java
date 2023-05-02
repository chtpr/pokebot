package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
import edu.northeastern.cs5500.starterbot.utils.TradeMessageUtil;
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
public final class TradeSelectionMenuHandler_Factory implements Factory<TradeSelectionMenuHandler> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<UserInventoryController> inventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeSelectionMenuHandler_Factory(Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.inventoryControllerProvider = inventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  @Override
  public TradeSelectionMenuHandler get() {
    TradeSelectionMenuHandler instance = newInstance();
    TradeSelectionMenuHandler_MembersInjector.injectTradeController(instance, tradeControllerProvider.get());
    TradeSelectionMenuHandler_MembersInjector.injectInventoryController(instance, inventoryControllerProvider.get());
    TradeSelectionMenuHandler_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    TradeSelectionMenuHandler_MembersInjector.injectMsgUtil(instance, msgUtilProvider.get());
    return instance;
  }

  public static TradeSelectionMenuHandler_Factory create(
      Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeSelectionMenuHandler_Factory(tradeControllerProvider, inventoryControllerProvider, pokemonControllerProvider, msgUtilProvider);
  }

  public static TradeSelectionMenuHandler newInstance() {
    return new TradeSelectionMenuHandler();
  }
}
