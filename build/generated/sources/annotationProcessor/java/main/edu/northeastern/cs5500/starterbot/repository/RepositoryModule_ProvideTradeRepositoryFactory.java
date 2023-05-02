package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.Trade;
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
public final class RepositoryModule_ProvideTradeRepositoryFactory implements Factory<GenericRepository<Trade>> {
  private final RepositoryModule module;

  private final Provider<MongoDBRepository<Trade>> repositoryProvider;

  public RepositoryModule_ProvideTradeRepositoryFactory(RepositoryModule module,
      Provider<MongoDBRepository<Trade>> repositoryProvider) {
    this.module = module;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GenericRepository<Trade> get() {
    return provideTradeRepository(module, repositoryProvider.get());
  }

  public static RepositoryModule_ProvideTradeRepositoryFactory create(RepositoryModule module,
      Provider<MongoDBRepository<Trade>> repositoryProvider) {
    return new RepositoryModule_ProvideTradeRepositoryFactory(module, repositoryProvider);
  }

  public static GenericRepository<Trade> provideTradeRepository(RepositoryModule instance,
      MongoDBRepository<Trade> repository) {
    return Preconditions.checkNotNullFromProvides(instance.provideTradeRepository(repository));
  }
}
