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
public final class GreetReturningUserCommand_Factory implements Factory<GreetReturningUserCommand> {
  @Override
  public GreetReturningUserCommand get() {
    return newInstance();
  }

  public static GreetReturningUserCommand_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GreetReturningUserCommand newInstance() {
    return new GreetReturningUserCommand();
  }

  private static final class InstanceHolder {
    private static final GreetReturningUserCommand_Factory INSTANCE = new GreetReturningUserCommand_Factory();
  }
}
