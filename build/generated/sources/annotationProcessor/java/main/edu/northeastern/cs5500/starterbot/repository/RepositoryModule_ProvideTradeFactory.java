package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.Trade;
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
public final class RepositoryModule_ProvideTradeFactory implements Factory<Class<Trade>> {
  private final RepositoryModule module;

  public RepositoryModule_ProvideTradeFactory(RepositoryModule module) {
    this.module = module;
  }

  @Override
  public Class<Trade> get() {
    return provideTrade(module);
  }

  public static RepositoryModule_ProvideTradeFactory create(RepositoryModule module) {
    return new RepositoryModule_ProvideTradeFactory(module);
  }

  public static Class<Trade> provideTrade(RepositoryModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideTrade());
  }
}
