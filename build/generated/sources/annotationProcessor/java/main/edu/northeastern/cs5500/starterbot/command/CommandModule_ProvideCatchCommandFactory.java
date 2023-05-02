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
public final class CommandModule_ProvideCatchCommandFactory implements Factory<SlashCommandHandler> {
  private final CommandModule module;

  private final Provider<CatchCommand> catchCommandProvider;

  public CommandModule_ProvideCatchCommandFactory(CommandModule module,
      Provider<CatchCommand> catchCommandProvider) {
    this.module = module;
    this.catchCommandProvider = catchCommandProvider;
  }

  @Override
  public SlashCommandHandler get() {
    return provideCatchCommand(module, catchCommandProvider.get());
  }

  public static CommandModule_ProvideCatchCommandFactory create(CommandModule module,
      Provider<CatchCommand> catchCommandProvider) {
    return new CommandModule_ProvideCatchCommandFactory(module, catchCommandProvider);
  }

  public static SlashCommandHandler provideCatchCommand(CommandModule instance,
      CatchCommand catchCommand) {
    return Preconditions.checkNotNullFromProvides(instance.provideCatchCommand(catchCommand));
  }
}
