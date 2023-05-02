package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.Battle;
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
public final class RepositoryModule_ProvideBattleRepositoryFactory implements Factory<GenericRepository<Battle>> {
  private final RepositoryModule module;

  private final Provider<MongoDBRepository<Battle>> repositoryProvider;

  public RepositoryModule_ProvideBattleRepositoryFactory(RepositoryModule module,
      Provider<MongoDBRepository<Battle>> repositoryProvider) {
    this.module = module;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GenericRepository<Battle> get() {
    return provideBattleRepository(module, repositoryProvider.get());
  }

  public static RepositoryModule_ProvideBattleRepositoryFactory create(RepositoryModule module,
      Provider<MongoDBRepository<Battle>> repositoryProvider) {
    return new RepositoryModule_ProvideBattleRepositoryFactory(module, repositoryProvider);
  }

  public static GenericRepository<Battle> provideBattleRepository(RepositoryModule instance,
      MongoDBRepository<Battle> repository) {
    return Preconditions.checkNotNullFromProvides(instance.provideBattleRepository(repository));
  }
}
