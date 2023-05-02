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
public final class BattleButtonClickHandleConfirm_MembersInjector implements MembersInjector<BattleButtonClickHandleConfirm> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleButtonClickHandleConfirm_MembersInjector(
      Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  public static MembersInjector<BattleButtonClickHandleConfirm> create(
      Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleButtonClickHandleConfirm_MembersInjector(battleControllerProvider, battleMessageUtilProvider);
  }

  @Override
  public void injectMembers(BattleButtonClickHandleConfirm instance) {
    injectBattleController(instance, battleControllerProvider.get());
    injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleConfirm.battleController")
  public static void injectBattleController(BattleButtonClickHandleConfirm instance,
      BattleController battleController) {
    instance.battleController = battleController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleConfirm.battleMessageUtil")
  public static void injectBattleMessageUtil(BattleButtonClickHandleConfirm instance,
      BattleMessageUtil battleMessageUtil) {
    instance.battleMessageUtil = battleMessageUtil;
  }
}
