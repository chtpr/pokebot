package edu.northeastern.cs5500.starterbot.command;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import edu.northeastern.cs5500.starterbot.controller.BattleController;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
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
public final class BattleButtonClickHandleAccept_MembersInjector implements MembersInjector<BattleButtonClickHandleAccept> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleButtonClickHandleAccept_MembersInjector(
      Provider<BattleController> battleControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  public static MembersInjector<BattleButtonClickHandleAccept> create(
      Provider<BattleController> battleControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleButtonClickHandleAccept_MembersInjector(battleControllerProvider, userInventoryControllerProvider, battleMessageUtilProvider);
  }

  @Override
  public void injectMembers(BattleButtonClickHandleAccept instance) {
    injectBattleController(instance, battleControllerProvider.get());
    injectUserInventoryController(instance, userInventoryControllerProvider.get());
    injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleAccept.battleController")
  public static void injectBattleController(BattleButtonClickHandleAccept instance,
      BattleController battleController) {
    instance.battleController = battleController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleAccept.userInventoryController")
  public static void injectUserInventoryController(BattleButtonClickHandleAccept instance,
      UserInventoryController userInventoryController) {
    instance.userInventoryController = userInventoryController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.command.BattleButtonClickHandleAccept.battleMessageUtil")
  public static void injectBattleMessageUtil(BattleButtonClickHandleAccept instance,
      BattleMessageUtil battleMessageUtil) {
    instance.battleMessageUtil = battleMessageUtil;
  }
}
