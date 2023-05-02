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
public final class CommandModule_ProvideDeletePokemonCommandFactory implements Factory<SlashCommandHandler> {
  private final CommandModule module;

  private final Provider<DeletePokemonCommand> deletePokemonCommandProvider;

  public CommandModule_ProvideDeletePokemonCommandFactory(CommandModule module,
      Provider<DeletePokemonCommand> deletePokemonCommandProvider) {
    this.module = module;
    this.deletePokemonCommandProvider = deletePokemonCommandProvider;
  }

  @Override
  public SlashCommandHandler get() {
    return provideDeletePokemonCommand(module, deletePokemonCommandProvider.get());
  }

  public static CommandModule_ProvideDeletePokemonCommandFactory create(CommandModule module,
      Provider<DeletePokemonCommand> deletePokemonCommandProvider) {
    return new CommandModule_ProvideDeletePokemonCommandFactory(module, deletePokemonCommandProvider);
  }

  public static SlashCommandHandler provideDeletePokemonCommand(CommandModule instance,
      DeletePokemonCommand deletePokemonCommand) {
    return Preconditions.checkNotNullFromProvides(instance.provideDeletePokemonCommand(deletePokemonCommand));
  }
}
