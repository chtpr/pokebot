package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class TradeButtonClickHandleAccept_MembersInjector implements MembersInjector<TradeButtonClickHandleAccept> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<UserInventoryController> inventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeButtonClickHandleAccept_MembersInjector(
      Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.inventoryControllerProvider = inventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  public static MembersInjector<TradeButtonClickHandleAccept> create(
      Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeButtonClickHandleAccept_MembersInjector(tradeControllerProvider, inventoryControllerProvider, pokemonControllerProvider, msgUtilProvider);
  }

  @Override
  public void injectMembers(TradeButtonClickHandleAccept instance) {
    injectTradeController(instance, tradeControllerProvider.get());
    injectInventoryController(instance, inventoryControllerProvider.get());
    injectPokemonController(instance, pokemonControllerProvider.get());
    injectMsgUtil(instance, msgUtilProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleAccept.tradeController")
  public static void injectTradeController(TradeButtonClickHandleAccept instance,
      TradeController tradeController) {
    instance.tradeController = tradeController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleAccept.inventoryController")
  public static void injectInventoryController(TradeButtonClickHandleAccept instance,
      UserInventoryController inventoryController) {
    instance.inventoryController = inventoryController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleAccept.pokemonController")
  public static void injectPokemonController(TradeButtonClickHandleAccept instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleAccept.msgUtil")
  public static void injectMsgUtil(TradeButtonClickHandleAccept instance,
      TradeMessageUtil msgUtil) {
    instance.msgUtil = msgUtil;
  }
}
