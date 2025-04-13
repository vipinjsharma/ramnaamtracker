package com.ramlekhak.di;

import android.content.SharedPreferences;
import com.ramlekhak.data.UserPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideUserPreferencesFactory implements Factory<UserPreferences> {
  private final Provider<SharedPreferences> prefsProvider;

  public DatabaseModule_ProvideUserPreferencesFactory(Provider<SharedPreferences> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public UserPreferences get() {
    return provideUserPreferences(prefsProvider.get());
  }

  public static DatabaseModule_ProvideUserPreferencesFactory create(
      Provider<SharedPreferences> prefsProvider) {
    return new DatabaseModule_ProvideUserPreferencesFactory(prefsProvider);
  }

  public static UserPreferences provideUserPreferences(SharedPreferences prefs) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideUserPreferences(prefs));
  }
}
