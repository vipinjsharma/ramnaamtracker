package com.ramlekhak.utils;

/**
 * Utility class for date operations.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\fJ\u0016\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0013J\u000e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u0017\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u0019\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010\u001a\u001a\u00020\u0007J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001a\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0007J\u0018\u0010\"\u001a\u00020\u001c2\b\u0010#\u001a\u0004\u0018\u00010\u00072\u0006\u0010$\u001a\u00020\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/ramlekhak/utils/DateUtils;", "", "()V", "calendar", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "addDays", "Ljava/util/Date;", "date", "days", "", "formatDate", "", "pattern", "getDateMinusDays", "getEndOfDay", "getEndOfMonth", "getEndOfWeek", "getMonthlyProgress", "", "counts", "Lcom/ramlekhak/data/Count;", "getPreviousDay", "getStartOfDay", "getStartOfMonth", "getStartOfWeek", "getYesterday", "isNewDay", "", "lastTimestamp", "", "isSameDay", "date1", "date2", "shouldResetCount", "lastDate", "currentDate", "app_debug"})
public final class DateUtils {
    private static final java.util.Calendar calendar = null;
    @org.jetbrains.annotations.NotNull
    public static final com.ramlekhak.utils.DateUtils INSTANCE = null;
    
    private DateUtils() {
        super();
    }
    
    /**
     * Get the start of the current day (midnight)
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getStartOfDay(@org.jetbrains.annotations.NotNull
    java.util.Date date) {
        return null;
    }
    
    /**
     * Get the end of the current day (23:59:59.999)
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getEndOfDay(@org.jetbrains.annotations.NotNull
    java.util.Date date) {
        return null;
    }
    
    /**
     * Get yesterday's date
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getYesterday() {
        return null;
    }
    
    /**
     * Get the previous day
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getPreviousDay(@org.jetbrains.annotations.NotNull
    java.util.Date date) {
        return null;
    }
    
    /**
     * Add days to a date
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Date addDays(@org.jetbrains.annotations.NotNull
    java.util.Date date, int days) {
        return null;
    }
    
    /**
     * Check if two dates are the same day
     */
    public final boolean isSameDay(@org.jetbrains.annotations.Nullable
    java.util.Date date1, @org.jetbrains.annotations.Nullable
    java.util.Date date2) {
        return false;
    }
    
    /**
     * Format date as a readable string
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String formatDate(@org.jetbrains.annotations.NotNull
    java.util.Date date, @org.jetbrains.annotations.NotNull
    java.lang.String pattern) {
        return null;
    }
    
    /**
     * Check if it's a new day since the last recorded timestamp
     */
    public final boolean isNewDay(long lastTimestamp) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Integer> getMonthlyProgress(@org.jetbrains.annotations.NotNull
    java.util.List<com.ramlekhak.data.Count> counts) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getStartOfWeek(@org.jetbrains.annotations.NotNull
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getEndOfWeek(@org.jetbrains.annotations.NotNull
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getStartOfMonth(@org.jetbrains.annotations.NotNull
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getEndOfMonth(@org.jetbrains.annotations.NotNull
    java.util.Date date) {
        return null;
    }
    
    public final boolean shouldResetCount(@org.jetbrains.annotations.Nullable
    java.util.Date lastDate, @org.jetbrains.annotations.NotNull
    java.util.Date currentDate) {
        return false;
    }
    
    /**
     * Get a date that is a specified number of days before the given date
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getDateMinusDays(@org.jetbrains.annotations.NotNull
    java.util.Date date, int days) {
        return null;
    }
}