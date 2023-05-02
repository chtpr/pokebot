package edu.northeastern.cs5500.starterbot.repository;

import dagger.Module;
import dagger.Provides;
import edu.northeastern.cs5500.starterbot.model.Battle;
import edu.northeastern.cs5500.starterbot.model.PokedexEntry;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.Trade;
import edu.northeastern.cs5500.starterbot.model.UserInventory;

@Module
public class RepositoryModule {

    @Provides
    public GenericRepository<UserInventory> provideUserInventoryRepository(
            MongoDBRepository<UserInventory> repository) {
        return repository;
    }

    @Provides
    public GenericRepository<Pokemon> providePokemonRepository(
            MongoDBRepository<Pokemon> repository) {
        return repository;
    }

    @Provides
    public GenericRepository<PokedexEntry> providePokedexEntryRepository(
            MongoDBRepository<PokedexEntry> repository) {
        return repository;
    }

    @Provides
    public GenericRepository<Trade> provideTradeRepository(MongoDBRepository<Trade> repository) {
        return repository;
    }

    @Provides
    public GenericRepository<Battle> provideBattleRepository(MongoDBRepository<Battle> repository) {
        return repository;
    }

    @Provides
    public Class<UserInventory> provideUserInventory() {
        return UserInventory.class;
    }

    @Provides
    public Class<Pokemon> providePokemon() {
        return Pokemon.class;
    }

    @Provides
    public Class<PokedexEntry> providePokedexEntry() {
        return PokedexEntry.class;
    }

    @Provides
    public Class<Trade> provideTrade() {
        return Trade.class;
    }

    @Provides
    public Class<Battle> provideBattle() {
        return Battle.class;
    }
}
