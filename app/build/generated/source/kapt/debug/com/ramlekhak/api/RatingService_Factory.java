package com.ramlekhak.api;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class RatingService_Factory implements Factory<RatingService> {
  @Override
  public RatingService get() {
    return newInstance();
  }

  public static RatingService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RatingService newInstance() {
    return new RatingService();
  }

  private static final class InstanceHolder {
    private static final RatingService_Factory INSTANCE = new RatingService_Factory();
  }
}
