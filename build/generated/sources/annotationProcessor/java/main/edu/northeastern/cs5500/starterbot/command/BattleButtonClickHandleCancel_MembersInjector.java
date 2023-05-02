package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.utils.BattleMessageUtil;
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
public final class BattleButtonClickHandleCancel_MembersInjector implements MembersInjector<BattleButtonClickHandleCancel> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleButtonClickHandleCancel_MembersInjector(
      Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  public static MembersInjector<BattleButtonClickHandleCancel> create(
      Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleButtonClickHandleCancel_MembersInjector(battleControllerProvider, battleMessageUtilProvider);
  }

  @Override
  public void injectMembers(BattleButtonClickHandleCancel instance) {
    injectBattleController(instance, battleControllerProvider.get());
    injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleCancel.battleController")
  public static void injectBattleController(BattleButtonClickHandleCancel instance,
      BattleController battleController) {
    instance.battleController = battleController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleCancel.battleMessageUtil")
  public static void injectBattleMessageUtil(BattleButtonClickHandleCancel instance,
      BattleMessageUtil battleMessageUtil) {
    instance.battleMessageUtil = battleMessageUtil;
  }
}
