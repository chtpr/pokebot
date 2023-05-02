package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TradeButtonClickHandleCancel_Factory implements Factory<TradeButtonClickHandleCancel> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeButtonClickHandleCancel_Factory(Provider<TradeController> tradeControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  @Override
  public TradeButtonClickHandleCancel get() {
    TradeButtonClickHandleCancel instance = newInstance();
    TradeButtonClickHandleCancel_MembersInjector.injectTradeController(instance, tradeControllerProvider.get());
    TradeButtonClickHandleCancel_MembersInjector.injectMsgUtil(instance, msgUtilProvider.get());
    return instance;
  }

  public static TradeButtonClickHandleCancel_Factory create(
      Provider<TradeController> tradeControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeButtonClickHandleCancel_Factory(tradeControllerProvider, msgUtilProvider);
  }

  public static TradeButtonClickHandleCancel newInstance() {
    return new TradeButtonClickHandleCancel();
  }
}
