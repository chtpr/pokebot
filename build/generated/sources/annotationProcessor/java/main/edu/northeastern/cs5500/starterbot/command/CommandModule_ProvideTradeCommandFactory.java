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
public final class CommandModule_ProvideTradeCommandFactory implements Factory<SlashCommandHandler> {
  private final CommandModule module;

  private final Provider<TradeCommand> tradeCommandProvider;

  public CommandModule_ProvideTradeCommandFactory(CommandModule module,
      Provider<TradeCommand> tradeCommandProvider) {
    this.module = module;
    this.tradeCommandProvider = tradeCommandProvider;
  }

  @Override
  public SlashCommandHandler get() {
    return provideTradeCommand(module, tradeCommandProvider.get());
  }

  public static CommandModule_ProvideTradeCommandFactory create(CommandModule module,
      Provider<TradeCommand> tradeCommandProvider) {
    return new CommandModule_ProvideTradeCommandFactory(module, tradeCommandProvider);
  }

  public static SlashCommandHandler provideTradeCommand(CommandModule instance,
      TradeCommand tradeCommand) {
    return Preconditions.checkNotNullFromProvides(instance.provideTradeCommand(tradeCommand));
  }
}
