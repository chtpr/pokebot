package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
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
public final class RepositoryModule_ProvidePokemonRepositoryFactory implements Factory<GenericRepository<Pokemon>> {
  private final RepositoryModule module;

  private final Provider<MongoDBRepository<Pokemon>> repositoryProvider;

  public RepositoryModule_ProvidePokemonRepositoryFactory(RepositoryModule module,
      Provider<MongoDBRepository<Pokemon>> repositoryProvider) {
    this.module = module;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GenericRepository<Pokemon> get() {
    return providePokemonRepository(module, repositoryProvider.get());
  }

  public static RepositoryModule_ProvidePokemonRepositoryFactory create(RepositoryModule module,
      Provider<MongoDBRepository<Pokemon>> repositoryProvider) {
    return new RepositoryModule_ProvidePokemonRepositoryFactory(module, repositoryProvider);
  }

  public static GenericRepository<Pokemon> providePokemonRepository(RepositoryModule instance,
      MongoDBRepository<Pokemon> repository) {
    return Preconditions.checkNotNullFromProvides(instance.providePokemonRepository(repository));
  }
}
