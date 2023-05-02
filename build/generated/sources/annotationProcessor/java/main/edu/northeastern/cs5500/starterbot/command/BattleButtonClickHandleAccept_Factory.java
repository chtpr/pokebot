package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class BattleButtonClickHandleAccept_Factory implements Factory<BattleButtonClickHandleAccept> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleButtonClickHandleAccept_Factory(Provider<BattleController> battleControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  @Override
  public BattleButtonClickHandleAccept get() {
    BattleButtonClickHandleAccept instance = newInstance();
    BattleButtonClickHandleAccept_MembersInjector.injectBattleController(instance, battleControllerProvider.get());
    BattleButtonClickHandleAccept_MembersInjector.injectUserInventoryController(instance, userInventoryControllerProvider.get());
    BattleButtonClickHandleAccept_MembersInjector.injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
    return instance;
  }

  public static BattleButtonClickHandleAccept_Factory create(
      Provider<BattleController> battleControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleButtonClickHandleAccept_Factory(battleControllerProvider, userInventoryControllerProvider, battleMessageUtilProvider);
  }

  public static BattleButtonClickHandleAccept newInstance() {
    return new BattleButtonClickHandleAccept();
  }
}
