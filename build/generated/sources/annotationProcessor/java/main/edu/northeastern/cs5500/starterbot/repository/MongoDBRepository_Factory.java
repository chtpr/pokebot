package edu.northeastern.cs5500.starterbot.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import edu.northeastern.cs5500.starterbot.model.Model;
import edu.northeastern.cs5500.starterbot.service.MongoDBService;
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
public final class MongoDBRepository_Factory<T extends Model> implements Factory<MongoDBRepository<T>> {
  private final Provider<Class<T>> clazzProvider;

  private final Provider<MongoDBService> mongoDBServiceProvider;

  public MongoDBRepository_Factory(Provider<Class<T>> clazzProvider,
      Provider<MongoDBService> mongoDBServiceProvider) {
    this.clazzProvider = clazzProvider;
    this.mongoDBServiceProvider = mongoDBServiceProvider;
  }

  @Override
  public MongoDBRepository<T> get() {
    return newInstance(clazzProvider.get(), mongoDBServiceProvider.get());
  }

  public static <T extends Model> MongoDBRepository_Factory<T> create(
      Provider<Class<T>> clazzProvider, Provider<MongoDBService> mongoDBServiceProvider) {
    return new MongoDBRepository_Factory<T>(clazzProvider, mongoDBServiceProvider);
  }

  public static <T extends Model> MongoDBRepository<T> newInstance(Class<T> clazz,
      MongoDBService mongoDBService) {
    return new MongoDBRepository<T>(clazz, mongoDBService);
  }
}
