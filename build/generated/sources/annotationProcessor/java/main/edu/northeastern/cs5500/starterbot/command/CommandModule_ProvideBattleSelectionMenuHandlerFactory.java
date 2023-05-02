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
public final class CommandModule_ProvideBattleSelectionMenuHandlerFactory implements Factory<SelectionMenuHandler> {
  private final CommandModule module;

  private final Provider<BattleSelectionMenuHandler> battleSelectionMenuHandlerProvider;

  public CommandModule_ProvideBattleSelectionMenuHandlerFactory(CommandModule module,
      Provider<BattleSelectionMenuHandler> battleSelectionMenuHandlerProvider) {
    this.module = module;
    this.battleSelectionMenuHandlerProvider = battleSelectionMenuHandlerProvider;
  }

  @Override
  public SelectionMenuHandler get() {
    return provideBattleSelectionMenuHandler(module, battleSelectionMenuHandlerProvider.get());
  }

  public static CommandModule_ProvideBattleSelectionMenuHandlerFactory create(CommandModule module,
      Provider<BattleSelectionMenuHandler> battleSelectionMenuHandlerProvider) {
    return new CommandModule_ProvideBattleSelectionMenuHandlerFactory(module, battleSelectionMenuHandlerProvider);
  }

  public static SelectionMenuHandler provideBattleSelectionMenuHandler(CommandModule instance,
      BattleSelectionMenuHandler battleSelectionMenuHandler) {
    return Preconditions.checkNotNullFromProvides(instance.provideBattleSelectionMenuHandler(battleSelectionMenuHandler));
  }
}
