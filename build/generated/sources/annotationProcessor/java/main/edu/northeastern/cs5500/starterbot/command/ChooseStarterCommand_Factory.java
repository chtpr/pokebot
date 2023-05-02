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
public final class ChooseStarterCommand_Factory implements Factory<ChooseStarterCommand> {
  @Override
  public ChooseStarterCommand get() {
    return newInstance();
  }

  public static ChooseStarterCommand_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ChooseStarterCommand newInstance() {
    return new ChooseStarterCommand();
  }

  private static final class InstanceHolder {
    private static final ChooseStarterCommand_Factory INSTANCE = new ChooseStarterCommand_Factory();
  }
}
