package com.ramlekhak.data;

import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class UserPreferences_Factory implements Factory<UserPreferences> {
  private final Provider<SharedPreferences> prefsProvider;

  public UserPreferences_Factory(Provider<SharedPreferences> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public UserPreferences get() {
    return newInstance(prefsProvider.get());
  }

  public static UserPreferences_Factory create(Provider<SharedPreferences> prefsProvider) {
    return new UserPreferences_Factory(prefsProvider);
  }

  public static UserPreferences newInstance(SharedPreferences prefs) {
    return new UserPreferences(prefs);
  }
}
