package edu.northeastern.cs5500.starterbot.controller;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.model.Trade;
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
public final class TradeController_Factory implements Factory<TradeController> {
  private final Provider<GenericRepository<Trade>> tradeRepositoryProvider;

  private final Provider<UserInventoryController> inventoryControllerProvider;

  public TradeController_Factory(Provider<GenericRepository<Trade>> tradeRepositoryProvider,
      Provider<UserInventoryController> inventoryControllerProvider) {
    this.tradeRepositoryProvider = tradeRepositoryProvider;
    this.inventoryControllerProvider = inventoryControllerProvider;
  }

  @Override
  public TradeController get() {
    return newInstance(tradeRepositoryProvider.get(), inventoryControllerProvider.get());
  }

  public static TradeController_Factory create(
      Provider<GenericRepository<Trade>> tradeRepositoryProvider,
      Provider<UserInventoryController> inventoryControllerProvider) {
    return new TradeController_Factory(tradeRepositoryProvider, inventoryControllerProvider);
  }

  public static TradeController newInstance(GenericRepository<Trade> tradeRepository,
      UserInventoryController inventoryController) {
    return new TradeController(tradeRepository, inventoryController);
  }
}
