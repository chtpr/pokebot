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
public final class CommandModule_ProvideTradeSelectionMenuHandlerFactory implements Factory<SelectionMenuHandler> {
  private final CommandModule module;

  private final Provider<TradeSelectionMenuHandler> tradeSelectionMenuHandlerProvider;

  public CommandModule_ProvideTradeSelectionMenuHandlerFactory(CommandModule module,
      Provider<TradeSelectionMenuHandler> tradeSelectionMenuHandlerProvider) {
    this.module = module;
    this.tradeSelectionMenuHandlerProvider = tradeSelectionMenuHandlerProvider;
  }

  @Override
  public SelectionMenuHandler get() {
    return provideTradeSelectionMenuHandler(module, tradeSelectionMenuHandlerProvider.get());
  }

  public static CommandModule_ProvideTradeSelectionMenuHandlerFactory create(CommandModule module,
      Provider<TradeSelectionMenuHandler> tradeSelectionMenuHandlerProvider) {
    return new CommandModule_ProvideTradeSelectionMenuHandlerFactory(module, tradeSelectionMenuHandlerProvider);
  }

  public static SelectionMenuHandler provideTradeSelectionMenuHandler(CommandModule instance,
      TradeSelectionMenuHandler tradeSelectionMenuHandler) {
    return Preconditions.checkNotNullFromProvides(instance.provideTradeSelectionMenuHandler(tradeSelectionMenuHandler));
  }
}
