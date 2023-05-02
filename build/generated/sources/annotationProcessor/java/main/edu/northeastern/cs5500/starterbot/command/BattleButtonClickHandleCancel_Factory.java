package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class BattleButtonClickHandleCancel_Factory implements Factory<BattleButtonClickHandleCancel> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleButtonClickHandleCancel_Factory(Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  @Override
  public BattleButtonClickHandleCancel get() {
    BattleButtonClickHandleCancel instance = newInstance();
    BattleButtonClickHandleCancel_MembersInjector.injectBattleController(instance, battleControllerProvider.get());
    BattleButtonClickHandleCancel_MembersInjector.injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
    return instance;
  }

  public static BattleButtonClickHandleCancel_Factory create(
      Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleButtonClickHandleCancel_Factory(battleControllerProvider, battleMessageUtilProvider);
  }

  public static BattleButtonClickHandleCancel newInstance() {
    return new BattleButtonClickHandleCancel();
  }
}
