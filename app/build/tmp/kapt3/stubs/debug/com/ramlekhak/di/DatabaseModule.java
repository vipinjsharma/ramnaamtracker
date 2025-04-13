package com.ramlekhak.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/ramlekhak/di/DatabaseModule;", "", "()V", "provideCountDao", "Lcom/ramlekhak/data/dao/CountDao;", "database", "Lcom/ramlekhak/data/AppDatabase;", "provideDatabase", "context", "Landroid/content/Context;", "provideRepository", "Lcom/ramlekhak/data/CountRepository;", "countDao", "provideUserPreferences", "Lcom/ramlekhak/data/UserPreferences;", "prefs", "Landroid/content/SharedPreferences;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull
    public static final com.ramlekhak.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.AppDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.dao.CountDao provideCountDao(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.AppDatabase database) {
        return null;
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.CountRepository provideRepository(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.dao.CountDao countDao) {
        return null;
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.UserPreferences provideUserPreferences(@javax.inject.Named(value = "default_preferences")
    @org.jetbrains.annotations.NotNull
    android.content.SharedPreferences prefs) {
        return null;
    }
}