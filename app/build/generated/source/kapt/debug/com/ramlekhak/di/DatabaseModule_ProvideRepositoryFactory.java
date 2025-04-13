package com.ramlekhak.di;

import com.ramlekhak.data.CountRepository;
import com.ramlekhak.data.dao.CountDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DatabaseModule_ProvideRepositoryFactory implements Factory<CountRepository> {
  private final Provider<CountDao> countDaoProvider;

  public DatabaseModule_ProvideRepositoryFactory(Provider<CountDao> countDaoProvider) {
    this.countDaoProvider = countDaoProvider;
  }

  @Override
  public CountRepository get() {
    return provideRepository(countDaoProvider.get());
  }

  public static DatabaseModule_ProvideRepositoryFactory create(
      Provider<CountDao> countDaoProvider) {
    return new DatabaseModule_ProvideRepositoryFactory(countDaoProvider);
  }

  public static CountRepository provideRepository(CountDao countDao) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRepository(countDao));
  }
}
