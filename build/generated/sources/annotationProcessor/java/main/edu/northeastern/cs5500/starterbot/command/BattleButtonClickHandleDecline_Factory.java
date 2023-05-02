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
public final class BattleButtonClickHandleDecline_Factory implements Factory<BattleButtonClickHandleDecline> {
  private final Provider<BattleController> battleControllerProvider;

  private final Provider<BattleMessageUtil> battleMessageUtilProvider;

  public BattleButtonClickHandleDecline_Factory(Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    this.battleControllerProvider = battleControllerProvider;
    this.battleMessageUtilProvider = battleMessageUtilProvider;
  }

  @Override
  public BattleButtonClickHandleDecline get() {
    BattleButtonClickHandleDecline instance = newInstance();
    BattleButtonClickHandleDecline_MembersInjector.injectBattleController(instance, battleControllerProvider.get());
    BattleButtonClickHandleDecline_MembersInjector.injectBattleMessageUtil(instance, battleMessageUtilProvider.get());
    return instance;
  }

  public static BattleButtonClickHandleDecline_Factory create(
      Provider<BattleController> battleControllerProvider,
      Provider<BattleMessageUtil> battleMessageUtilProvider) {
    return new BattleButtonClickHandleDecline_Factory(battleControllerProvider, battleMessageUtilProvider);
  }

  public static BattleButtonClickHandleDecline newInstance() {
    return new BattleButtonClickHandleDecline();
  }
}
