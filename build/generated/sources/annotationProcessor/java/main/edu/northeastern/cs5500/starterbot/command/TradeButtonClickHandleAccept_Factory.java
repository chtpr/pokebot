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
public final class TradeButtonClickHandleAccept_Factory implements Factory<TradeButtonClickHandleAccept> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<UserInventoryController> inventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeButtonClickHandleAccept_Factory(Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.inventoryControllerProvider = inventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  @Override
  public TradeButtonClickHandleAccept get() {
    TradeButtonClickHandleAccept instance = newInstance();
    TradeButtonClickHandleAccept_MembersInjector.injectTradeController(instance, tradeControllerProvider.get());
    TradeButtonClickHandleAccept_MembersInjector.injectInventoryController(instance, inventoryControllerProvider.get());
    TradeButtonClickHandleAccept_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    TradeButtonClickHandleAccept_MembersInjector.injectMsgUtil(instance, msgUtilProvider.get());
    return instance;
  }

  public static TradeButtonClickHandleAccept_Factory create(
      Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeButtonClickHandleAccept_Factory(tradeControllerProvider, inventoryControllerProvider, pokemonControllerProvider, msgUtilProvider);
  }

  public static TradeButtonClickHandleAccept newInstance() {
    return new TradeButtonClickHandleAccept();
  }
}
