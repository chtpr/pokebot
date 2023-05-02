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
public final class CommandModule_ProvideCatchButtonClickHandlerFactory implements Factory<ButtonClickHandler> {
  private final CommandModule module;

  private final Provider<CatchButtonClick> catchbuttonClickProvider;

  public CommandModule_ProvideCatchButtonClickHandlerFactory(CommandModule module,
      Provider<CatchButtonClick> catchbuttonClickProvider) {
    this.module = module;
    this.catchbuttonClickProvider = catchbuttonClickProvider;
  }

  @Override
  public ButtonClickHandler get() {
    return provideCatchButtonClickHandler(module, catchbuttonClickProvider.get());
  }

  public static CommandModule_ProvideCatchButtonClickHandlerFactory create(CommandModule module,
      Provider<CatchButtonClick> catchbuttonClickProvider) {
    return new CommandModule_ProvideCatchButtonClickHandlerFactory(module, catchbuttonClickProvider);
  }

  public static ButtonClickHandler provideCatchButtonClickHandler(CommandModule instance,
      CatchButtonClick catchbuttonClick) {
    return Preconditions.checkNotNullFromProvides(instance.provideCatchButtonClickHandler(catchbuttonClick));
  }
}
