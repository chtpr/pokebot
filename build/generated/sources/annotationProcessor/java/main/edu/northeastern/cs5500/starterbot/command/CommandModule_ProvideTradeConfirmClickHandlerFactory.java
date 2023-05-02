package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class CommandModule_ProvideTradeConfirmClickHandlerFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<TradeButtonClickHandleConfirm> tradeButtonClickProvider;

  public CommandModule_ProvideTradeConfirmClickHandlerFactory(CommandModule module,
      Provider<TradeButtonClickHandleConfirm> tradeButtonClickProvider) {
    this.module = module;
    this.tradeButtonClickProvider = tradeButtonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideTradeConfirmClickHandler(module, tradeButtonClickProvider.get());
  }

  public static CommandModule_ProvideTradeConfirmClickHandlerFactory create(CommandModule module,
      Provider<TradeButtonClickHandleConfirm> tradeButtonClickProvider) {
    return new CommandModule_ProvideTradeConfirmClickHandlerFactory(module, tradeButtonClickProvider);
  }

  public static ButtonClickHandler provideTradeConfirmClickHandler(CommandModule instance,
      TradeButtonClickHandleConfirm tradeButtonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideTradeConfirmClickHandler(tradeButtonClick));
  }
}
