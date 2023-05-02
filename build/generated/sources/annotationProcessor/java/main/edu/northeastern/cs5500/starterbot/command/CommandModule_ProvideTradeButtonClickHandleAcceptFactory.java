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
public final class CommandModule_ProvideTradeButtonClickHandleAcceptFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<TradeButtonClickHandleAccept> tradeButtonClickProvider;

  public CommandModule_ProvideTradeButtonClickHandleAcceptFactory(CommandModule module,
      Provider<TradeButtonClickHandleAccept> tradeButtonClickProvider) {
    this.module = module;
    this.tradeButtonClickProvider = tradeButtonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideTradeButtonClickHandleAccept(module, tradeButtonClickProvider.get());
  }

  public static CommandModule_ProvideTradeButtonClickHandleAcceptFactory create(
      CommandModule module, Provider<TradeButtonClickHandleAccept> tradeButtonClickProvider) {
    return new CommandModule_ProvideTradeButtonClickHandleAcceptFactory(module, tradeButtonClickProvider);
  }

  public static ButtonClickHandler provideTradeButtonClickHandleAccept(CommandModule instance,
      TradeButtonClickHandleAccept tradeButtonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideTradeButtonClickHandleAccept(tradeButtonClick));
  }
}
