package com.ramlekhak.di;

import com.ramlekhak.data.AppDatabase;
import com.ramlekhak.data.dao.CountDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideCountDaoFactory implements Factory<CountDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideCountDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CountDao get() {
    return provideCountDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideCountDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCountDaoFactory(databaseProvider);
  }

  public static CountDao provideCountDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCountDao(database));
  }
}
