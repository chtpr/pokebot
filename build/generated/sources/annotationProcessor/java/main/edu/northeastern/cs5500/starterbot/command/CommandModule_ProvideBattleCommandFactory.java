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
public final class CommandModule_ProvideBattleCommandFactory implements Factory<SlashCommandHandler> {
  private final CommandModule module;

  private final Provider<BattleCommand> battleCommandProvider;

  public CommandModule_ProvideBattleCommandFactory(CommandModule module,
      Provider<BattleCommand> battleCommandProvider) {
    this.module = module;
    this.battleCommandProvider = battleCommandProvider;
  }

  @Override
  public SlashCommandHandler get() {
    return provideBattleCommand(module, battleCommandProvider.get());
  }

  public static CommandModule_ProvideBattleCommandFactory create(CommandModule module,
      Provider<BattleCommand> battleCommandProvider) {
    return new CommandModule_ProvideBattleCommandFactory(module, battleCommandProvider);
  }

  public static SlashCommandHandler provideBattleCommand(CommandModule instance,
      BattleCommand battleCommand) {
    return Preconditions.checkNotNullFromProvides(instance.provideBattleCommand(battleCommand));
  }
}
