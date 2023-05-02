package edu.northeastern.cs5500.starterbot.controller;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.model.Battle;
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
public final class BattleController_Factory implements Factory<BattleController> {
  private final Provider<GenericRepository<Battle>> battleRepositoryProvider;

  private final Provider<PokemonController> pokemonControllerProvider;

  private final Provider<UserInventoryController> userInventoryControllerProvider;

  public BattleController_Factory(Provider<GenericRepository<Battle>> battleRepositoryProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider) {
    this.battleRepositoryProvider = battleRepositoryProvider;
    this.pokemonControllerProvider = pokemonControllerProvider;
    this.userInventoryControllerProvider = userInventoryControllerProvider;
  }

  @Override
  public BattleController get() {
    return newInstance(battleRepositoryProvider.get(), pokemonControllerProvider.get(), userInventoryControllerProvider.get());
  }

  public static BattleController_Factory create(
      Provider<GenericRepository<Battle>> battleRepositoryProvider,
      Provider<PokemonController> pokemonControllerProvider,
      Provider<UserInventoryController> userInventoryControllerProvider) {
    return new BattleController_Factory(battleRepositoryProvider, pokemonControllerProvider, userInventoryControllerProvider);
  }

  public static BattleController newInstance(GenericRepository<Battle> battleRepository,
      PokemonController pokemonController, UserInventoryController userInventoryController) {
    return new BattleController(battleRepository, pokemonController, userInventoryController);
  }
}
