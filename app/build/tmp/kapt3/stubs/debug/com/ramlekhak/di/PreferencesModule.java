package com.ramlekhak.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/ramlekhak/di/PreferencesModule;", "", "()V", "provideAppPreferences", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "provideDefaultSharedPreferences", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class PreferencesModule {
    @org.jetbrains.annotations.NotNull
    public static final com.ramlekhak.di.PreferencesModule INSTANCE = null;
    
    private PreferencesModule() {
        super();
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @javax.inject.Named(value = "default_preferences")
    @org.jetbrains.annotations.NotNull
    public final android.content.SharedPreferences provideDefaultSharedPreferences(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @javax.inject.Singleton
    @dagger.Provides
    @javax.inject.Named(value = "app_preferences")
    @org.jetbrains.annotations.NotNull
    public final android.content.SharedPreferences provideAppPreferences(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
}