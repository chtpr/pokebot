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
public final class GreetNewUserCommand_Factory implements Factory<GreetNewUserCommand> {
  @Override
  public GreetNewUserCommand get() {
    return newInstance();
  }

  public static GreetNewUserCommand_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GreetNewUserCommand newInstance() {
    return new GreetNewUserCommand();
  }

  private static final class InstanceHolder {
    private static final GreetNewUserCommand_Factory INSTANCE = new GreetNewUserCommand_Factory();
  }
}
