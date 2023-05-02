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
public final class MongoDBService_Factory implements Factory<MongoDBService> {
  @Override
  public MongoDBService get() {
    return newInstance();
  }

  public static MongoDBService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MongoDBService newInstance() {
    return new MongoDBService();
  }

  private static final class InstanceHolder {
    private static final MongoDBService_Factory INSTANCE = new MongoDBService_Factory();
  }
}
