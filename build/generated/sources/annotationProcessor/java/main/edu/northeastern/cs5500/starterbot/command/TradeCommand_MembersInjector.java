package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class TradeCommand_MembersInjector implements MembersInjector<TradeCommand> {
  private final Provider<TradeController> tradeControllerProvider;

  public TradeCommand_MembersInjector(Provider<TradeController> tradeControllerProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
  }

  public static MembersInjector<TradeCommand> create(
      Provider<TradeController> tradeControllerProvider) {
    return new TradeCommand_MembersInjector(tradeControllerProvider);
  }

  @Override
  public void injectMembers(TradeCommand instance) {
    injectTradeController(instance, tradeControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeCommand.tradeController")
  public static void injectTradeController(TradeCommand instance, TradeController tradeController) {
    instance.tradeController = tradeController;
  }
}
