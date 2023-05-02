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
public final class CommandModule_ProvideTradeButtonClickHandleCancelFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<TradeButtonClickHandleCancel> tradeButtonClickProvider;

  public CommandModule_ProvideTradeButtonClickHandleCancelFactory(CommandModule module,
      Provider<TradeButtonClickHandleCancel> tradeButtonClickProvider) {
    this.module = module;
    this.tradeButtonClickProvider = tradeButtonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideTradeButtonClickHandleCancel(module, tradeButtonClickProvider.get());
  }

  public static CommandModule_ProvideTradeButtonClickHandleCancelFactory create(
      CommandModule module, Provider<TradeButtonClickHandleCancel> tradeButtonClickProvider) {
    return new CommandModule_ProvideTradeButtonClickHandleCancelFactory(module, tradeButtonClickProvider);
  }

  public static ButtonClickHandler provideTradeButtonClickHandleCancel(CommandModule instance,
      TradeButtonClickHandleCancel tradeButtonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideTradeButtonClickHandleCancel(tradeButtonClick));
  }
}
