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
public final class CommandModule_ProvideBattleButtonClickHandleCancelFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<BattleButtonClickHandleCancel> battleButtonClickProvider;

  public CommandModule_ProvideBattleButtonClickHandleCancelFactory(CommandModule module,
      Provider<BattleButtonClickHandleCancel> battleButtonClickProvider) {
    this.module = module;
    this.battleButtonClickProvider = battleButtonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideBattleButtonClickHandleCancel(module, battleButtonClickProvider.get());
  }

  public static CommandModule_ProvideBattleButtonClickHandleCancelFactory create(
      CommandModule module, Provider<BattleButtonClickHandleCancel> battleButtonClickProvider) {
    return new CommandModule_ProvideBattleButtonClickHandleCancelFactory(module, battleButtonClickProvider);
  }

  public static ButtonClickHandler provideBattleButtonClickHandleCancel(CommandModule instance,
      BattleButtonClickHandleCancel battleButtonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideBattleButtonClickHandleCancel(battleButtonClick));
  }
}
