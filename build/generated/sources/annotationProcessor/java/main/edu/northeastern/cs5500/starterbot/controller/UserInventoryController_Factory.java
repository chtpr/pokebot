package edu.northeastern.cs5500.starterbot.controller;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.model.UserInventory;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
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
public final class UserInventoryController_Factory implements Factory<UserInventoryController> {
  private final Provider<GenericRepository<UserInventory>> userInventoryRepositoryProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  public UserInventoryController_Factory(
      Provider<GenericRepository<UserInventory>> userInventoryRepositoryProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    this.userInventoryRepositoryProvider = userInventoryRepositoryProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
  }

  @Override
  public UserInventoryController get() {
    UserInventoryController instance = newInstance(userInventoryRepositoryProvider.get());
    UserInventoryController_MembersInjector.injectPokemonController(instance, pokemonControllerProvider.get());
    return instance;
  }

  public static UserInventoryController_Factory create(
      Provider<GenericRepository<UserInventory>> userInventoryRepositoryProvider,
      Provider<PokemonController> pokemonControllerProvider) {
    return new UserInventoryController_Factory(userInventoryRepositoryProvider, pokemonControllerProvider);
  }

  public static UserInventoryController newInstance(
      GenericRepository<UserInventory> userInventoryRepository) {
    return new UserInventoryController(userInventoryRepository);
  }
}
