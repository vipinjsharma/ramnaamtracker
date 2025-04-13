package com.ramlekhak.viewmodel;

import android.app.Application;
import com.ramlekhak.data.CountRepository;
import com.ramlekhak.data.UserPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<CountRepository> repositoryProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public HomeViewModel_Factory(Provider<Application> applicationProvider,
      Provider<CountRepository> repositoryProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    this.applicationProvider = applicationProvider;
    this.repositoryProvider = repositoryProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(applicationProvider.get(), repositoryProvider.get(), userPreferencesProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<CountRepository> repositoryProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new HomeViewModel_Factory(applicationProvider, repositoryProvider, userPreferencesProvider);
  }

  public static HomeViewModel newInstance(Application application, CountRepository repository,
      UserPreferences userPreferences) {
    return new HomeViewModel(application, repository, userPreferences);
  }
}
