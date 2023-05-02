package edu.northeastern.cs5500.starterbot.listener;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class MessageListener_Factory implements Factory<MessageListener> {
  private final Provider<Set<SlashCommandHandler>> commandsProvider;

  private final Provider<Set<ButtonClickHandler>> buttonsProvider;

  private final Provider<Set<SelectionMenuHandler>> selectionMenusProvider;

  public MessageListener_Factory(Provider<Set<SlashCommandHandler>> commandsProvider,
      Provider<Set<ButtonClickHandler>> buttonsProvider,
      Provider<Set<SelectionMenuHandler>> selectionMenusProvider) {
    this.commandsProvider = commandsProvider;
    this.buttonsProvider = buttonsProvider;
    this.selectionMenusProvider = selectionMenusProvider;
  }

  @Override
  public MessageListener get() {
    MessageListener instance = newInstance();
    MessageListener_MembersInjector.injectCommands(instance, commandsProvider.get());
    MessageListener_MembersInjector.injectButtons(instance, buttonsProvider.get());
    MessageListener_MembersInjector.injectSelectionMenus(instance, selectionMenusProvider.get());
    return instance;
  }

  public static MessageListener_Factory create(Provider<Set<SlashCommandHandler>> commandsProvider,
      Provider<Set<ButtonClickHandler>> buttonsProvider,
      Provider<Set<SelectionMenuHandler>> selectionMenusProvider) {
    return new MessageListener_Factory(commandsProvider, buttonsProvider, selectionMenusProvider);
  }

  public static MessageListener newInstance() {
    return new MessageListener();
  }
}
