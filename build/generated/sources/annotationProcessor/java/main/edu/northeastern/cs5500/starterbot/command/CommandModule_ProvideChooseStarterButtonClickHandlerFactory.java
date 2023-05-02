package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class CommandModule_ProvideChooseStarterButtonClickHandlerFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<ChooseStarterButtonClick> buttonClickProvider;

  public CommandModule_ProvideChooseStarterButtonClickHandlerFactory(CommandModule module,
      Provider<ChooseStarterButtonClick> buttonClickProvider) {
    this.module = module;
    this.buttonClickProvider = buttonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideChooseStarterButtonClickHandler(module, buttonClickProvider.get());
  }

  public static CommandModule_ProvideChooseStarterButtonClickHandlerFactory create(
      CommandModule module, Provider<ChooseStarterButtonClick> buttonClickProvider) {
    return new CommandModule_ProvideChooseStarterButtonClickHandlerFactory(module, buttonClickProvider);
  }

  public static ButtonClickHandler provideChooseStarterButtonClickHandler(CommandModule instance,
      ChooseStarterButtonClick buttonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideChooseStarterButtonClickHandler(buttonClick));
  }
}
