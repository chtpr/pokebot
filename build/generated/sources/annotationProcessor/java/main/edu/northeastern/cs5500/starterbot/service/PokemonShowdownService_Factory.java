package edu.northeastern.cs5500.starterbot.service;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class PokemonShowdownService_Factory implements Factory<PokemonShowdownService> {
  @Override
  public PokemonShowdownService get() {
    return newInstance();
  }

  public static PokemonShowdownService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PokemonShowdownService newInstance() {
    return new PokemonShowdownService();
  }

  private static final class InstanceHolder {
    private static final PokemonShowdownService_Factory INSTANCE = new PokemonShowdownService_Factory();
  }
}
