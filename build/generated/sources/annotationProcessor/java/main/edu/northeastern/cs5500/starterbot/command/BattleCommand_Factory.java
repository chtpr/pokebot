package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
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
public final class BattleCommand_Factory implements Factory<BattleCommand> {
  private final Provider<BattleController> battleControllerProvider;

  public BattleCommand_Factory(Provider<BattleController> battleControllerProvider) {
    this.battleControllerProvider = battleControllerProvider;
  }

  @Override
  public BattleCommand get() {
    BattleCommand instance = newInstance();
    BattleCommand_MembersInjector.injectBattleController(instance, battleControllerProvider.get());
    return instance;
  }

  public static BattleCommand_Factory create(Provider<BattleController> battleControllerProvider) {
    return new BattleCommand_Factory(battleControllerProvider);
  }

  public static BattleCommand newInstance() {
    return new BattleCommand();
  }
}
