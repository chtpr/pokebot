package edu.northeastern.cs5500.starterbot.listener;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class GuildListener_MembersInjector implements MembersInjector<GuildListener> {
  private final Provider<GreetNewUserCommand> greetNewUserCommandProvider;

  private final Provider<ChooseStarterCommand> chooseStarterCommandProvider;

  private final Provider<UserInventoryController> userInventoryControllerProvider;

  private final Provider<GreetReturningUserCommand> greetReturningUserCommandProvider;

  public GuildListener_MembersInjector(Provider<GreetNewUserCommand> greetNewUserCommandProvider,
      Provider<ChooseStarterCommand> chooseStarterCommandProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<GreetReturningUserCommand> greetReturningUserCommandProvider) {
    this.greetNewUserCommandProvider = greetNewUserCommandProvider;
    this.chooseStarterCommandProvider = chooseStarterCommandProvider;
    this.userInventoryControllerProvider = userInventoryControllerProvider;
    this.greetReturningUserCommandProvider = greetReturningUserCommandProvider;
  }

  public static MembersInjector<GuildListener> create(
      Provider<GreetNewUserCommand> greetNewUserCommandProvider,
      Provider<ChooseStarterCommand> chooseStarterCommandProvider,
      Provider<UserInventoryController> userInventoryControllerProvider,
      Provider<GreetReturningUserCommand> greetReturningUserCommandProvider) {
    return new GuildListener_MembersInjector(greetNewUserCommandProvider, chooseStarterCommandProvider, userInventoryControllerProvider, greetReturningUserCommandProvider);
  }

  @Override
  public void injectMembers(GuildListener instance) {
    injectGreetNewUserCommand(instance, greetNewUserCommandProvider.get());
    injectChooseStarterCommand(instance, chooseStarterCommandProvider.get());
    injectUserInventoryController(instance, userInventoryControllerProvider.get());
    injectGreetReturningUserCommand(instance, greetReturningUserCommandProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.listener.GuildListener.greetNewUserCommand")
  public static void injectGreetNewUserCommand(GuildListener instance,
      GreetNewUserCommand greetNewUserCommand) {
    instance.greetNewUserCommand = greetNewUserCommand;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.listener.GuildListener.chooseStarterCommand")
  public static void injectChooseStarterCommand(GuildListener instance,
      ChooseStarterCommand chooseStarterCommand) {
    instance.chooseStarterCommand = chooseStarterCommand;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.listener.GuildListener.userInventoryController")
  public static void injectUserInventoryController(GuildListener instance,
      UserInventoryController userInventoryController) {
    instance.userInventoryController = userInventoryController;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.listener.GuildListener.greetReturningUserCommand")
  public static void injectGreetReturningUserCommand(GuildListener instance,
      GreetReturningUserCommand greetReturningUserCommand) {
    instance.greetReturningUserCommand = greetReturningUserCommand;
  }
}
