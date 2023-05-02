package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class BattleCommand_MembersInjector implements MembersInjector<BattleCommand> {
  private final Provider<BattleController> battleControllerProvider;

  public BattleCommand_MembersInjector(Provider<BattleController> battleControllerProvider) {
    this.battleControllerProvider = battleControllerProvider;
  }

  public static MembersInjector<BattleCommand> create(
      Provider<BattleController> battleControllerProvider) {
    return new BattleCommand_MembersInjector(battleControllerProvider);
  }

  @Override
  public void injectMembers(BattleCommand instance) {
    injectBattleController(instance, battleControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleCommand.battleController")
  public static void injectBattleController(BattleCommand instance,
      BattleController battleController) {
    instance.battleController = battleController;
  }
}
