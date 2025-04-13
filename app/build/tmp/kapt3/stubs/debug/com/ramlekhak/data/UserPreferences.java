package com.ramlekhak.data;

/**
 * Manages user preferences using SharedPreferences.
 */
@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/ramlekhak/data/UserPreferences;", "", "prefs", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "areNotificationsEnabled", "", "getLanguage", "", "getLastResetDate", "", "isDarkThemeEnabled", "isResetAtMidnightEnabled", "setDarkThemeEnabled", "", "enabled", "applyImmediately", "setLanguage", "language", "setLastResetDate", "timestamp", "setNotificationsEnabled", "setResetAtMidnight", "Companion", "app_debug"})
public final class UserPreferences {
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_DARK_THEME = "dark_theme";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_LANGUAGE = "language";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_NOTIFICATIONS = "notifications";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_RESET_AT_MIDNIGHT = "reset_at_midnight";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_LAST_RESET_DATE = "last_reset_date";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREFS_NAME = "ram_naam_prefs";
    @org.jetbrains.annotations.NotNull
    public static final com.ramlekhak.data.UserPreferences.Companion Companion = null;
    
    @javax.inject.Inject
    public UserPreferences(@javax.inject.Named(value = "default_preferences")
    @org.jetbrains.annotations.NotNull
    android.content.SharedPreferences prefs) {
        super();
    }
    
    /**
     * Check if dark theme is enabled
     */
    public final boolean isDarkThemeEnabled() {
        return false;
    }
    
    /**
     * Set dark theme enabled/disabled
     * @param enabled Whether dark theme should be enabled
     * @param applyImmediately Whether to apply the theme change immediately
     */
    public final void setDarkThemeEnabled(boolean enabled, boolean applyImmediately) {
    }
    
    /**
     * Get the app language
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLanguage() {
        return null;
    }
    
    /**
     * Set the app language
     */
    public final void setLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language) {
    }
    
    /**
     * Check if notifications are enabled
     */
    public final boolean areNotificationsEnabled() {
        return false;
    }
    
    /**
     * Set notifications enabled/disabled
     */
    public final void setNotificationsEnabled(boolean enabled) {
    }
    
    /**
     * Check if reset at midnight is enabled
     */
    public final boolean isResetAtMidnightEnabled() {
        return false;
    }
    
    /**
     * Set reset at midnight enabled/disabled
     */
    public final void setResetAtMidnight(boolean enabled) {
    }
    
    /**
     * Get the date of the last count reset
     */
    public final long getLastResetDate() {
        return 0L;
    }
    
    /**
     * Set the date of the last count reset
     */
    public final void setLastResetDate(long timestamp) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/ramlekhak/data/UserPreferences$Companion;", "", "()V", "KEY_DARK_THEME", "", "KEY_LANGUAGE", "KEY_LAST_RESET_DATE", "KEY_NOTIFICATIONS", "KEY_RESET_AT_MIDNIGHT", "PREFS_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}