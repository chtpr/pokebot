package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class TradeButtonClickHandleCancel_MembersInjector implements MembersInjector<TradeButtonClickHandleCancel> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeButtonClickHandleCancel_MembersInjector(
      Provider<TradeController> tradeControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  public static MembersInjector<TradeButtonClickHandleCancel> create(
      Provider<TradeController> tradeControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeButtonClickHandleCancel_MembersInjector(tradeControllerProvider, msgUtilProvider);
  }

  @Override
  public void injectMembers(TradeButtonClickHandleCancel instance) {
    injectTradeController(instance, tradeControllerProvider.get());
    injectMsgUtil(instance, msgUtilProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleCancel.tradeController")
  public static void injectTradeController(TradeButtonClickHandleCancel instance,
      TradeController tradeController) {
    instance.tradeController = tradeController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.TradeButtonClickHandleCancel.msgUtil")
  public static void injectMsgUtil(TradeButtonClickHandleCancel instance,
      TradeMessageUtil msgUtil) {
    instance.msgUtil = msgUtil;
  }
}
