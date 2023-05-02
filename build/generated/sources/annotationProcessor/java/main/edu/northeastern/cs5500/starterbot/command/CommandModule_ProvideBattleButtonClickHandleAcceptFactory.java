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
public final class CommandModule_ProvideBattleButtonClickHandleAcceptFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<BattleButtonClickHandleAccept> battleButtonClickProvider;

  public CommandModule_ProvideBattleButtonClickHandleAcceptFactory(CommandModule module,
      Provider<BattleButtonClickHandleAccept> battleButtonClickProvider) {
    this.module = module;
    this.battleButtonClickProvider = battleButtonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideBattleButtonClickHandleAccept(module, battleButtonClickProvider.get());
  }

  public static CommandModule_ProvideBattleButtonClickHandleAcceptFactory create(
      CommandModule module, Provider<BattleButtonClickHandleAccept> battleButtonClickProvider) {
    return new CommandModule_ProvideBattleButtonClickHandleAcceptFactory(module, battleButtonClickProvider);
  }

  public static ButtonClickHandler provideBattleButtonClickHandleAccept(CommandModule instance,
      BattleButtonClickHandleAccept battleButtonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideBattleButtonClickHandleAccept(battleButtonClick));
  }
}
