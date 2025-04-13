package com.ramlekhak.viewmodel;

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
public final class StatisticsViewModel_Factory implements Factory<StatisticsViewModel> {
  private final Provider<CountRepository> repositoryProvider;

  public StatisticsViewModel_Factory(Provider<CountRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public StatisticsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static StatisticsViewModel_Factory create(Provider<CountRepository> repositoryProvider) {
    return new StatisticsViewModel_Factory(repositoryProvider);
  }

  public static StatisticsViewModel newInstance(CountRepository repository) {
    return new StatisticsViewModel(repository);
  }
}
