package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
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
public final class RepositoryModule_ProvidePokedexEntryRepositoryFactory implements Factory<GenericRepository<PokedexEntry>> {
  private final RepositoryModule module;

  private final Provider<MongoDBRepository<PokedexEntry>> repositoryProvider;

  public RepositoryModule_ProvidePokedexEntryRepositoryFactory(RepositoryModule module,
      Provider<MongoDBRepository<PokedexEntry>> repositoryProvider) {
    this.module = module;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GenericRepository<PokedexEntry> get() {
    return providePokedexEntryRepository(module, repositoryProvider.get());
  }

  public static RepositoryModule_ProvidePokedexEntryRepositoryFactory create(
      RepositoryModule module, Provider<MongoDBRepository<PokedexEntry>> repositoryProvider) {
    return new RepositoryModule_ProvidePokedexEntryRepositoryFactory(module, repositoryProvider);
  }

  public static GenericRepository<PokedexEntry> providePokedexEntryRepository(
      RepositoryModule instance, MongoDBRepository<PokedexEntry> repository) {
    return Preconditions.checkNotNullFromProvides(instance.providePokedexEntryRepository(repository));
  }
}
