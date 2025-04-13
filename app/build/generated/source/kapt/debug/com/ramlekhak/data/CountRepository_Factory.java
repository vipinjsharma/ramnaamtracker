package com.ramlekhak.data;

import com.ramlekhak.data.dao.CountDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class CountRepository_Factory implements Factory<CountRepository> {
  private final Provider<CountDao> countDaoProvider;

  public CountRepository_Factory(Provider<CountDao> countDaoProvider) {
    this.countDaoProvider = countDaoProvider;
  }

  @Override
  public CountRepository get() {
    return newInstance(countDaoProvider.get());
  }

  public static CountRepository_Factory create(Provider<CountDao> countDaoProvider) {
    return new CountRepository_Factory(countDaoProvider);
  }

  public static CountRepository newInstance(CountDao countDao) {
    return new CountRepository(countDao);
  }
}
