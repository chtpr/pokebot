package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
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
public final class TradeButtonClickHandleConfirm_MembersInjector implements MembersInjector<TradeButtonClickHandleConfirm> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeButtonClickHandleConfirm_MembersInjector(
      Provider<TradeController> tradeControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  public static MembersInjector<TradeButtonClickHandleConfirm> create(
      Provider<TradeController> tradeControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeButtonClickHandleConfirm_MembersInjector(tradeControllerProvider, pokemonControllerProvider, msgUtilProvider);
  }

  @Override
  public void injectMembers(TradeButtonClickHandleConfirm instance) {
    injectTradeController(instance, tradeControllerProvider.get());
    injectPokemonController(instance, pokemonControllerProvider.get());
    injectMsgUtil(instance, msgUtilProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleConfirm.tradeController")
  public static void injectTradeController(TradeButtonClickHandleConfirm instance,
      TradeController tradeController) {
    instance.tradeController = tradeController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleConfirm.pokemonController")
  public static void injectPokemonController(TradeButtonClickHandleConfirm instance,
      PokemonController pokemonController) {
    instance.pokemonController = pokemonController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleConfirm.msgUtil")
  public static void injectMsgUtil(TradeButtonClickHandleConfirm instance,
      TradeMessageUtil msgUtil) {
    instance.msgUtil = msgUtil;
  }
}
