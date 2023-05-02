package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TradeButtonClickHandleConfirm_Factory implements Factory<TradeButtonClickHandleConfirm> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeButtonClickHandleConfirm_Factory(Provider<TradeController> tradeControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  @Override
  public TradeButtonClickHandleConfirm get() {
    TradeButtonClickHandleConfirm instance = newInstance();
    TradeButtonClickHandleConfirm_MembersInjector.injectTradeController(instance, tradeControllerProvider.get());
    TradeButtonClickHandleConfirm_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    TradeButtonClickHandleConfirm_MembersInjector.injectMsgUtil(instance, msgUtilProvider.get());
    return instance;
  }

  public static TradeButtonClickHandleConfirm_Factory create(
      Provider<TradeController> tradeControllerProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeButtonClickHandleConfirm_Factory(tradeControllerProvider, pokemonControllerProvider, msgUtilProvider);
  }

  public static TradeButtonClickHandleConfirm newInstance() {
    return new TradeButtonClickHandleConfirm();
  }
}
