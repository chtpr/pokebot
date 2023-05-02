package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
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
public final class RepositoryModule_ProvideUserInventoryRepositoryFactory implements Factory<GenericRepository<UserInventory>> {
  private final RepositoryModule module;

  private final Provider<MongoDBRepository<UserInventory>> repositoryProvider;

  public RepositoryModule_ProvideUserInventoryRepositoryFactory(RepositoryModule module,
      Provider<MongoDBRepository<UserInventory>> repositoryProvider) {
    this.module = module;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GenericRepository<UserInventory> get() {
    return provideUserInventoryRepository(module, repositoryProvider.get());
  }

  public static RepositoryModule_ProvideUserInventoryRepositoryFactory create(
      RepositoryModule module, Provider<MongoDBRepository<UserInventory>> repositoryProvider) {
    return new RepositoryModule_ProvideUserInventoryRepositoryFactory(module, repositoryProvider);
  }

  public static GenericRepository<UserInventory> provideUserInventoryRepository(
      RepositoryModule instance, MongoDBRepository<UserInventory> repository) {
    return Preconditions.checkNotNullFromProvides(instance.provideUserInventoryRepository(repository));
  }
}
