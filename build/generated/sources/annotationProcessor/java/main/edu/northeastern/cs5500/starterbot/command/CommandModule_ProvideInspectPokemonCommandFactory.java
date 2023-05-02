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
public final class CommandModule_ProvideInspectPokemonCommandFactory implements Factory<SlashCommandHandler> {
  private final CommandModule module;

  private final Provider<InspectPokemonCommand> inspectPokemonCommandProvider;

  public CommandModule_ProvideInspectPokemonCommandFactory(CommandModule module,
      Provider<InspectPokemonCommand> inspectPokemonCommandProvider) {
    this.module = module;
    this.inspectPokemonCommandProvider = inspectPokemonCommandProvider;
  }

  @Override
  public SlashCommandHandler get() {
    return provideInspectPokemonCommand(module, inspectPokemonCommandProvider.get());
  }

  public static CommandModule_ProvideInspectPokemonCommandFactory create(CommandModule module,
      Provider<InspectPokemonCommand> inspectPokemonCommandProvider) {
    return new CommandModule_ProvideInspectPokemonCommandFactory(module, inspectPokemonCommandProvider);
  }

  public static SlashCommandHandler provideInspectPokemonCommand(CommandModule instance,
      InspectPokemonCommand inspectPokemonCommand) {
    return Preconditions.checkNotNullFromProvides(instance.provideInspectPokemonCommand(inspectPokemonCommand));
  }
}
