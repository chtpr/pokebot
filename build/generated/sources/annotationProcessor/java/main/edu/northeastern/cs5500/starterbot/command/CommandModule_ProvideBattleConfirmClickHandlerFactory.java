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
public final class CommandModule_ProvideBattleConfirmClickHandlerFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<BattleButtonClickHandleConfirm> battleButtonClickProvider;

  public CommandModule_ProvideBattleConfirmClickHandlerFactory(CommandModule module,
      Provider<BattleButtonClickHandleConfirm> battleButtonClickProvider) {
    this.module = module;
    this.battleButtonClickProvider = battleButtonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideBattleConfirmClickHandler(module, battleButtonClickProvider.get());
  }

  public static CommandModule_ProvideBattleConfirmClickHandlerFactory create(CommandModule module,
      Provider<BattleButtonClickHandleConfirm> battleButtonClickProvider) {
    return new CommandModule_ProvideBattleConfirmClickHandlerFactory(module, battleButtonClickProvider);
  }

  public static ButtonClickHandler provideBattleConfirmClickHandler(CommandModule instance,
      BattleButtonClickHandleConfirm battleButtonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideBattleConfirmClickHandler(battleButtonClick));
  }
}
