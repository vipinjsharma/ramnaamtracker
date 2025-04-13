package com.ramlekhak.ui.writing;

import com.ramlekhak.data.CountRepository;
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
public final class WritingViewModel_Factory implements Factory<WritingViewModel> {
  private final Provider<CountRepository> repositoryProvider;

  public WritingViewModel_Factory(Provider<CountRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public WritingViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static WritingViewModel_Factory create(Provider<CountRepository> repositoryProvider) {
    return new WritingViewModel_Factory(repositoryProvider);
  }

  public static WritingViewModel newInstance(CountRepository repository) {
    return new WritingViewModel(repository);
  }
}
