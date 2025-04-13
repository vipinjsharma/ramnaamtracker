package com.ramlekhak.di;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata({
    "javax.inject.Named",
    "dagger.hilt.android.qualifiers.ApplicationContext"
})
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
public final class PreferencesModule_ProvideAppPreferencesFactory implements Factory<SharedPreferences> {
  private final Provider<Context> contextProvider;

  public PreferencesModule_ProvideAppPreferencesFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SharedPreferences get() {
    return provideAppPreferences(contextProvider.get());
  }

  public static PreferencesModule_ProvideAppPreferencesFactory create(
      Provider<Context> contextProvider) {
    return new PreferencesModule_ProvideAppPreferencesFactory(contextProvider);
  }

  public static SharedPreferences provideAppPreferences(Context context) {
    return Preconditions.checkNotNullFromProvides(PreferencesModule.INSTANCE.provideAppPreferences(context));
  }
}
