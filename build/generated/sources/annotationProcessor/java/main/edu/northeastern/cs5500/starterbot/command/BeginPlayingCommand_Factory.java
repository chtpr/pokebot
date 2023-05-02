package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BeginPlayingCommand_Factory implements Factory<BeginPlayingCommand> {
  @Override
  public BeginPlayingCommand get() {
    return newInstance();
  }

  public static BeginPlayingCommand_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static BeginPlayingCommand newInstance() {
    return new BeginPlayingCommand();
  }

  private static final class InstanceHolder {
    private static final BeginPlayingCommand_Factory INSTANCE = new BeginPlayingCommand_Factory();
  }
}
