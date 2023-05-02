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
public final class TradeButtonClickHandleDecline_Factory implements Factory<TradeButtonClickHandleDecline> {
  private final Provider<TradeController> tradeControllerProvider;

  private final Provider<TradeMessageUtil> msgUtilProvider;

  public TradeButtonClickHandleDecline_Factory(Provider<TradeController> tradeControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    this.tradeControllerProvider = tradeControllerProvider;
    this.msgUtilProvider = msgUtilProvider;
  }

  @Override
  public TradeButtonClickHandleDecline get() {
    TradeButtonClickHandleDecline instance = newInstance();
    TradeButtonClickHandleDecline_MembersInjector.injectTradeController(instance, tradeControllerProvider.get());
    TradeButtonClickHandleDecline_MembersInjector.injectMsgUtil(instance, msgUtilProvider.get());
    return instance;
  }

  public static TradeButtonClickHandleDecline_Factory create(
      Provider<TradeController> tradeControllerProvider,
      Provider<TradeMessageUtil> msgUtilProvider) {
    return new TradeButtonClickHandleDecline_Factory(tradeControllerProvider, msgUtilProvider);
  }

  public static TradeButtonClickHandleDecline newInstance() {
    return new TradeButtonClickHandleDecline();
  }
}
