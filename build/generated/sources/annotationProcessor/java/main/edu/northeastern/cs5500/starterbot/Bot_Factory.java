package edu.northeastern.cs5500.starterbot;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.listener.GuildListener;
import edu.northeastern.cs5500.starterbot.listener.MessageListener;
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
public final class Bot_Factory implements Factory<Bot> {
  private final Provider<MessageListener> messageListenerProvider;

  private final Provider<GuildListener> guildListenerProvider;

  public Bot_Factory(Provider<MessageListener> messageListenerProvider,
      Provider<GuildListener> guildListenerProvider) {
    this.messageListenerProvider = messageListenerProvider;
    this.guildListenerProvider = guildListenerProvider;
  }

  @Override
  public Bot get() {
    Bot instance = newInstance();
    Bot_MembersInjector.injectMessageListener(instance, messageListenerProvider.get());
    Bot_MembersInjector.injectGuildListener(instance, guildListenerProvider.get());
    return instance;
  }

  public static Bot_Factory create(Provider<MessageListener> messageListenerProvider,
      Provider<GuildListener> guildListenerProvider) {
    return new Bot_Factory(messageListenerProvider, guildListenerProvider);
  }

  public static Bot newInstance() {
    return new Bot();
  }
}
