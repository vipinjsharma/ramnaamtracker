package com.ramlekhak;

import com.ramlekhak.data.CountRepository;
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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<CountRepository> countRepositoryProvider;

  public MainActivity_MembersInjector(Provider<CountRepository> countRepositoryProvider) {
    this.countRepositoryProvider = countRepositoryProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<CountRepository> countRepositoryProvider) {
    return new MainActivity_MembersInjector(countRepositoryProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectCountRepository(instance, countRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.ramlekhak.MainActivity.countRepository")
  public static void injectCountRepository(MainActivity instance, CountRepository countRepository) {
    instance.countRepository = countRepository;
  }
}
