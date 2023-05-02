package edu.northeastern.cs5500.starterbot.command;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.controller.PokedexController;
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
public final class CatchCommand_Factory implements Factory<CatchCommand> {
  private final Provider<PokedexController> pokedexControllerProvider;

  public CatchCommand_Factory(Provider<PokedexController> pokedexControllerProvider) {
    this.pokedexControllerProvider = pokedexControllerProvider;
  }

  @Override
  public CatchCommand get() {
    return newInstance(pokedexControllerProvider.get());
  }

  public static CatchCommand_Factory create(Provider<PokedexController> pokedexControllerProvider) {
    return new CatchCommand_Factory(pokedexControllerProvider);
  }

  public static CatchCommand newInstance(PokedexController pokedexController) {
    return new CatchCommand(pokedexController);
  }
}
