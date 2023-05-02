package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.starterbot.model.Battle;
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
public final class RepositoryModule_ProvideBattleFactory implements Factory<Class<Battle>> {
  private final RepositoryModule module;

  public RepositoryModule_ProvideBattleFactory(RepositoryModule module) {
    this.module = module;
  }

  @Override
  public Class<Battle> get() {
    return provideBattle(module);
  }

  public static RepositoryModule_ProvideBattleFactory create(RepositoryModule module) {
    return new RepositoryModule_ProvideBattleFactory(module);
  }

  public static Class<Battle> provideBattle(RepositoryModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideBattle());
  }
}
