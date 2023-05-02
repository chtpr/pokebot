package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
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
public final class RepositoryModule_ProvidePokemonFactory implements Factory<Class<Pokemon>> {
  private final RepositoryModule module;

  public RepositoryModule_ProvidePokemonFactory(RepositoryModule module) {
    this.module = module;
  }

  @Override
  public Class<Pokemon> get() {
    return providePokemon(module);
  }

  public static RepositoryModule_ProvidePokemonFactory create(RepositoryModule module) {
    return new RepositoryModule_ProvidePokemonFactory(module);
  }

  public static Class<Pokemon> providePokemon(RepositoryModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.providePokemon());
  }
}
