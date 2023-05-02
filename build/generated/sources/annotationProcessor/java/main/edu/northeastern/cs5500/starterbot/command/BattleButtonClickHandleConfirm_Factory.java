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
public final class BattleButtonClickHandleConfirm_Factory implements Factory<BattleButtonClickHandleConfirm> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleButtonClickHandleConfirm_Factory(Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  @Override
  public BattleButtonClickHandleConfirm get() {
    BattleButtonClickHandleConfirm instance = newInstance();
    BattleButtonClickHandleConfirm_MembersInjector.injectBattleController(instance, battleControllerProvider.get());
    BattleButtonClickHandleConfirm_MembersInjector.injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
    return instance;
  }

  public static BattleButtonClickHandleConfirm_Factory create(
      Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleButtonClickHandleConfirm_Factory(battleControllerProvider, battleMessageUtilProvider);
  }

  public static BattleButtonClickHandleConfirm newInstance() {
    return new BattleButtonClickHandleConfirm();
  }
}
