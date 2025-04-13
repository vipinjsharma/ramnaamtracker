package com.ramlekhak.ui.profile;

import android.content.SharedPreferences;
import com.ramlekhak.data.CountRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<CountRepository> repositoryProvider;

  private final Provider<SharedPreferences> sharedPreferencesProvider;

  public ProfileViewModel_Factory(Provider<CountRepository> repositoryProvider,
      Provider<SharedPreferences> sharedPreferencesProvider) {
    this.repositoryProvider = repositoryProvider;
    this.sharedPreferencesProvider = sharedPreferencesProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(repositoryProvider.get(), sharedPreferencesProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<CountRepository> repositoryProvider,
      Provider<SharedPreferences> sharedPreferencesProvider) {
    return new ProfileViewModel_Factory(repositoryProvider, sharedPreferencesProvider);
  }

  public static ProfileViewModel newInstance(CountRepository repository,
      SharedPreferences sharedPreferences) {
    return new ProfileViewModel(repository, sharedPreferences);
  }
}
