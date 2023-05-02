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
public final class CommandModule_ProvideTradeButtonClickHandleDeclineFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<TradeButtonClickHandleDecline> tradeButtonClickProvider;

  public CommandModule_ProvideTradeButtonClickHandleDeclineFactory(CommandModule module,
      Provider<TradeButtonClickHandleDecline> tradeButtonClickProvider) {
    this.module = module;
    this.tradeButtonClickProvider = tradeButtonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideTradeButtonClickHandleDecline(module, tradeButtonClickProvider.get());
  }

  public static CommandModule_ProvideTradeButtonClickHandleDeclineFactory create(
      CommandModule module, Provider<TradeButtonClickHandleDecline> tradeButtonClickProvider) {
    return new CommandModule_ProvideTradeButtonClickHandleDeclineFactory(module, tradeButtonClickProvider);
  }

  public static ButtonClickHandler provideTradeButtonClickHandleDecline(CommandModule instance,
      TradeButtonClickHandleDecline tradeButtonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideTradeButtonClickHandleDecline(tradeButtonClick));
  }
}
