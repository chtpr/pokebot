package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.controller.TradeController;
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
public final class TradeCommand_Factory implements Factory<TradeCommand> {
  private final Provider<TradeController> tradeControllerProvider;

  public TradeCommand_Factory(Provider<TradeController> tradeControllerProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
  }

  @Override
  public TradeCommand get() {
    TradeCommand instance = newInstance();
    TradeCommand_MembersInjector.injectTradeController(instance, tradeControllerProvider.get());
    return instance;
  }

  public static TradeCommand_Factory create(Provider<TradeController> tradeControllerProvider) {
    return new TradeCommand_Factory(tradeControllerProvider);
  }

  public static TradeCommand newInstance() {
    return new TradeCommand();
  }
}
