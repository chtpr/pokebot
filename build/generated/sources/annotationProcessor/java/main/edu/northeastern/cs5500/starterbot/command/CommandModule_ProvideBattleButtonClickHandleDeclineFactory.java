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
public final class CommandModule_ProvideBattleButtonClickHandleDeclineFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<BattleButtonClickHandleDecline> battleButtonClickProvider;

  public CommandModule_ProvideBattleButtonClickHandleDeclineFactory(CommandModule module,
      Provider<BattleButtonClickHandleDecline> battleButtonClickProvider) {
    this.module = module;
    this.battleButtonClickProvider = battleButtonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideBattleButtonClickHandleDecline(module, battleButtonClickProvider.get());
  }

  public static CommandModule_ProvideBattleButtonClickHandleDeclineFactory create(
      CommandModule module, Provider<BattleButtonClickHandleDecline> battleButtonClickProvider) {
    return new CommandModule_ProvideBattleButtonClickHandleDeclineFactory(module, battleButtonClickProvider);
  }

  public static ButtonClickHandler provideBattleButtonClickHandleDecline(CommandModule instance,
      BattleButtonClickHandleDecline battleButtonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideBattleButtonClickHandleDecline(battleButtonClick));
  }
}
