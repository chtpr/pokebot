package edu.northeastern.cs5500.starterbot;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class Bot_MembersInjector implements MembersInjector<Bot> {
  private final Provider<MessageListener> messageListenerProvider;

  private final Provider<GuildListener> guildListenerProvider;

  public Bot_MembersInjector(Provider<MessageListener> messageListenerProvider,
      Provider<GuildListener> guildListenerProvider) {
    this.messageListenerProvider = messageListenerProvider;
    this.guildListenerProvider = guildListenerProvider;
  }

  public static MembersInjector<Bot> create(Provider<MessageListener> messageListenerProvider,
      Provider<GuildListener> guildListenerProvider) {
    return new Bot_MembersInjector(messageListenerProvider, guildListenerProvider);
  }

  @Override
  public void injectMembers(Bot instance) {
    injectMessageListener(instance, messageListenerProvider.get());
    injectGuildListener(instance, guildListenerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.Bot.messageListener")
  public static void injectMessageListener(Bot instance, MessageListener messageListener) {
    instance.messageListener = messageListener;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.starterbot.Bot.guildListener")
  public static void injectGuildListener(Bot instance, GuildListener guildListener) {
    instance.guildListener = guildListener;
  }
}
