package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
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
public final class RepositoryModule_ProvidePokedexEntryFactory implements Factory<Class<PokedexEntry>> {
  private final RepositoryModule module;

  public RepositoryModule_ProvidePokedexEntryFactory(RepositoryModule module) {
    this.module = module;
  }

  @Override
  public Class<PokedexEntry> get() {
    return providePokedexEntry(module);
  }

  public static RepositoryModule_ProvidePokedexEntryFactory create(RepositoryModule module) {
    return new RepositoryModule_ProvidePokedexEntryFactory(module);
  }

  public static Class<PokedexEntry> providePokedexEntry(RepositoryModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.providePokedexEntry());
  }
}
