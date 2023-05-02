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
public final class CommandModule_ProvideViewPartyCommandFactory implements Factory<SlashCommandHandler> {
  private final CommandModule module;

  private final Provider<ViewPartyCommand> viewPartyCommandProvider;

  public CommandModule_ProvideViewPartyCommandFactory(CommandModule module,
      Provider<ViewPartyCommand> viewPartyCommandProvider) {
    this.module = module;
    this.viewPartyCommandProvider = viewPartyCommandProvider;
  }

  @Override
  public SlashCommandHandler get() {
    return provideViewPartyCommand(module, viewPartyCommandProvider.get());
  }

  public static CommandModule_ProvideViewPartyCommandFactory create(CommandModule module,
      Provider<ViewPartyCommand> viewPartyCommandProvider) {
    return new CommandModule_ProvideViewPartyCommandFactory(module, viewPartyCommandProvider);
  }

  public static SlashCommandHandler provideViewPartyCommand(CommandModule instance,
      ViewPartyCommand viewPartyCommand) {
    return Preconditions.checkNotNullFromProvides(instance.provideViewPartyCommand(viewPartyCommand));
  }
}
