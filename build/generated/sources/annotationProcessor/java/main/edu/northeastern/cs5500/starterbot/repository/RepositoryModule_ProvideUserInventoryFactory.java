package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
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
public final class RepositoryModule_ProvideUserInventoryFactory implements Factory<Class<UserInventory>> {
  private final RepositoryModule module;

  public RepositoryModule_ProvideUserInventoryFactory(RepositoryModule module) {
    this.module = module;
  }

  @Override
  public Class<UserInventory> get() {
    return provideUserInventory(module);
  }

  public static RepositoryModule_ProvideUserInventoryFactory create(RepositoryModule module) {
    return new RepositoryModule_ProvideUserInventoryFactory(module);
  }

  public static Class<UserInventory> provideUserInventory(RepositoryModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideUserInventory());
  }
}
