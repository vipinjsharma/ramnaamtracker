package com.ramlekhak.ui.settings;

import com.ramlekhak.data.UserPreferences;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SettingsFragment_MembersInjector implements MembersInjector<SettingsFragment> {
  private final Provider<UserPreferences> userPreferencesProvider;

  public SettingsFragment_MembersInjector(Provider<UserPreferences> userPreferencesProvider) {
    this.userPreferencesProvider = userPreferencesProvider;
  }

  public static MembersInjector<SettingsFragment> create(
      Provider<UserPreferences> userPreferencesProvider) {
    return new SettingsFragment_MembersInjector(userPreferencesProvider);
  }

  @Override
  public void injectMembers(SettingsFragment instance) {
    injectUserPreferences(instance, userPreferencesProvider.get());
  }

  @InjectedFieldSignature("com.ramlekhak.ui.settings.SettingsFragment.userPreferences")
  public static void injectUserPreferences(SettingsFragment instance,
      UserPreferences userPreferences) {
    instance.userPreferences = userPreferences;
  }
}
