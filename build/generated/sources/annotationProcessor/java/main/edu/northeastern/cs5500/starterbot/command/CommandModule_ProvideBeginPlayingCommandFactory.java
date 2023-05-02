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
public final class CommandModule_ProvideBeginPlayingCommandFactory implements Factory<BeginPlayingCommand> {
  private final CommandModule module;

  private final Provider<BeginPlayingCommand> beginPlayingCommandProvider;

  public CommandModule_ProvideBeginPlayingCommandFactory(CommandModule module,
      Provider<BeginPlayingCommand> beginPlayingCommandProvider) {
    this.module = module;
    this.beginPlayingCommandProvider = beginPlayingCommandProvider;
  }

  @Override
  public BeginPlayingCommand get() {
    return provideBeginPlayingCommand(module, beginPlayingCommandProvider.get());
  }

  public static CommandModule_ProvideBeginPlayingCommandFactory create(CommandModule module,
      Provider<BeginPlayingCommand> beginPlayingCommandProvider) {
    return new CommandModule_ProvideBeginPlayingCommandFactory(module, beginPlayingCommandProvider);
  }

  public static BeginPlayingCommand provideBeginPlayingCommand(CommandModule instance,
      BeginPlayingCommand beginPlayingCommand) {
    return Preconditions.checkNotNullFromProvides(instance.provideBeginPlayingCommand(beginPlayingCommand));
  }
}
