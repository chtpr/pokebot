package edu.northeastern.cs5500.starterbot.listener;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import edu.northeastern.cs5500.starterbot.command.ButtonClickHandler;
import edu.northeastern.cs5500.starterbot.command.SelectionMenuHandler;
import edu.northeastern.cs5500.starterbot.command.SlashCommandHandler;
import java.util.Set;
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
public final class MessageListener_MembersInjector implements MembersInjector<MessageListener> {
  private final Provider<Set<SlashCommandHandler>> commandsProvider;

  private final Provider<Set<ButtonClickHandler>> buttonsProvider;

  private final Provider<Set<SelectionMenuHandler>> selectionMenusProvider;

  public MessageListener_MembersInjector(Provider<Set<SlashCommandHandler>> commandsProvider,
      Provider<Set<ButtonClickHandler>> buttonsProvider,
      Provider<Set<SelectionMenuHandler>> selectionMenusProvider) {
    this.commandsProvider = commandsProvider;
    this.buttonsProvider = buttonsProvider;
    this.selectionMenusProvider = selectionMenusProvider;
  }

  public static MembersInjector<MessageListener> create(
      Provider<Set<SlashCommandHandler>> commandsProvider,
      Provider<Set<ButtonClickHandler>> buttonsProvider,
      Provider<Set<SelectionMenuHandler>> selectionMenusProvider) {
    return new MessageListener_MembersInjector(commandsProvider, buttonsProvider, selectionMenusProvider);
  }

  @Override
  public void injectMembers(MessageListener instance) {
    injectCommands(instance, commandsProvider.get());
    injectButtons(instance, buttonsProvider.get());
    injectSelectionMenus(instance, selectionMenusProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.listener.MessageListener.commands")
  public static void injectCommands(MessageListener instance, Set<SlashCommandHandler> commands) {
    instance.commands = commands;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.listener.MessageListener.buttons")
  public static void injectButtons(MessageListener instance, Set<ButtonClickHandler> buttons) {
    instance.buttons = buttons;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.listener.MessageListener.selectionMenus")
  public static void injectSelectionMenus(MessageListener instance,
      Set<SelectionMenuHandler> selectionMenus) {
    instance.selectionMenus = selectionMenus;
  }
}
