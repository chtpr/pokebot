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
public final class TradeSelectionMenuHandler_MembersInjector implements MembersInjector<TradeSelectionMenuHandler> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<UserInventoryController> inventoryControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeSelectionMenuHandler_MembersInjector(
      Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.inventoryControllerProvider = inventoryControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  public static MembersInjector<TradeSelectionMenuHandler> create(
      Provider<TradeController> tradeControllerProvider,
      Provider<UserInventoryController> inventoryControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeSelectionMenuHandler_MembersInjector(tradeControllerProvider, inventoryControllerProvider, pokemonControllerProvider, msgUtilProvider);
  }

  @Override
  public void injectMembers(TradeSelectionMenuHandler instance) {
    injectTradeController(instance, tradeControllerProvider.get());
    injectInventoryController(instance, inventoryControllerProvider.get());
    injectPokemonController(instance, pokemonControllerProvider.get());
    injectMsgUtil(instance, msgUtilProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeSelectionMenuHandler.tradeController")
  public static void injectTradeController(TradeSelectionMenuHandler instance,
      TradeController tradeController) {
    instance.tradeController = tradeController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeSelectionMenuHandler.inventoryController")
  public static void injectInventoryController(TradeSelectionMenuHandler instance,
      UserInventoryController inventoryController) {
    instance.inventoryController = inventoryController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeSelectionMenuHandler.pokemonController")
  public static void injectPokemonController(TradeSelectionMenuHandler instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeSelectionMenuHandler.msgUtil")
  public static void injectMsgUtil(TradeSelectionMenuHandler instance, TradeMessageUtil msgUtil) {
    instance.msgUtil = msgUtil;
  }
}
