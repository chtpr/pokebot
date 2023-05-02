package edu.northeastern.cs5500.starterbot.listener;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.command.ChooseStarterCommand;
import edu.northeastern.cs5500.starterbot.command.GreetNewUserCommand;
import edu.northeastern.cs5500.starterbot.command.GreetReturningUserCommand;
import edu.northeastern.cs5500.starterbot.controller.UserInventoryController;
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
public final class GuildListener_Factory implements Factory<GuildListener> {
  private final Provider<GreetNewUserCommand> greetNewUserCommandProvider;

  private final Provider<ChooseStarterCommand> chooseStarterCommandProvider;

  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<GreetReturningUserCommand> greetReturningUserCommandProvider;

  public GuildListener_Factory(Provider<GreetNewUserCommand> greetNewUserCommandProvider,
      Provider<ChooseStarterCommand> chooseStarterCommandProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<GreetReturningUserCommand> greetReturningUserCommandProvider) {
    this.greetNewUserCommandProvider = greetNewUserCommandProvider;
    this.chooseStarterCommandProvider = chooseStarterCommandProvider;
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.greetReturningUserCommandProvider = greetReturningUserCommandProvider;
  }

  @Override
  public GuildListener get() {
    GuildListener instance = newInstance();
    GuildListener_MembersInjector.injectGreetNewUserCommand(instance, greetNewUserCommandProvider.get());
    GuildListener_MembersInjector.injectChooseStarterCommand(instance, chooseStarterCommandProvider.get());
    GuildListener_MembersInjector.injectUserInventoryController(instance, userInventoryControllerProvider.get());
    GuildListener_MembersInjector.injectGreetReturningUserCommand(instance, greetReturningUserCommandProvider.get());
    return instance;
  }

  public static GuildListener_Factory create(
      Provider<GreetNewUserCommand> greetNewUserCommandProvider,
      Provider<ChooseStarterCommand> chooseStarterCommandProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<GreetReturningUserCommand> greetReturningUserCommandProvider) {
    return new GuildListener_Factory(greetNewUserCommandProvider, chooseStarterCommandProvider, userInventoryControllerProvider, greetReturningUserCommandProvider);
  }

  public static GuildListener newInstance() {
    return new GuildListener();
  }
}
