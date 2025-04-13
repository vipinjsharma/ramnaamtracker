package com.ramlekhak.data.models;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010\u0010J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\t\u0010/\u001a\u00020\u0005H\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\fH\u00c6\u0003J\t\u00105\u001a\u00020\fH\u00c6\u0003Jt\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00c6\u0001\u00a2\u0006\u0002\u00107J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010;\u001a\u00020\u0005H\u00d6\u0001J\t\u0010<\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010\u0014R\u001a\u0010\r\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0012\"\u0004\b%\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0012\"\u0004\b\'\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0012\"\u0004\b)\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+\u00a8\u0006="}, d2 = {"Lcom/ramlekhak/data/models/Statistics;", "", "userId", "", "todayCount", "", "totalCount", "currentStreak", "longestStreak", "todayMalas", "totalMalas", "dailyGoalProgress", "", "monthlyGoalProgress", "lastWrittenDate", "", "(Ljava/lang/String;IIIIIIFFLjava/lang/Long;)V", "getCurrentStreak", "()I", "setCurrentStreak", "(I)V", "getDailyGoalProgress", "()F", "setDailyGoalProgress", "(F)V", "getLastWrittenDate", "()Ljava/lang/Long;", "setLastWrittenDate", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getLongestStreak", "setLongestStreak", "getMonthlyGoalProgress", "setMonthlyGoalProgress", "getTodayCount", "setTodayCount", "getTodayMalas", "setTodayMalas", "getTotalCount", "setTotalCount", "getTotalMalas", "setTotalMalas", "getUserId", "()Ljava/lang/String;", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;IIIIIIFFLjava/lang/Long;)Lcom/ramlekhak/data/models/Statistics;", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class Statistics {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String userId = null;
    private int todayCount;
    private int totalCount;
    private int currentStreak;
    private int longestStreak;
    private int todayMalas;
    private int totalMalas;
    private float dailyGoalProgress;
    private float monthlyGoalProgress;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long lastWrittenDate;
    
    public Statistics(@org.jetbrains.annotations.NotNull
    java.lang.String userId, int todayCount, int totalCount, int currentStreak, int longestStreak, int todayMalas, int totalMalas, float dailyGoalProgress, float monthlyGoalProgress, @org.jetbrains.annotations.Nullable
    java.lang.Long lastWrittenDate) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUserId() {
        return null;
    }
    
    public final int getTodayCount() {
        return 0;
    }
    
    public final void setTodayCount(int p0) {
    }
    
    public final int getTotalCount() {
        return 0;
    }
    
    public final void setTotalCount(int p0) {
    }
    
    public final int getCurrentStreak() {
        return 0;
    }
    
    public final void setCurrentStreak(int p0) {
    }
    
    public final int getLongestStreak() {
        return 0;
    }
    
    public final void setLongestStreak(int p0) {
    }
    
    public final int getTodayMalas() {
        return 0;
    }
    
    public final void setTodayMalas(int p0) {
    }
    
    public final int getTotalMalas() {
        return 0;
    }
    
    public final void setTotalMalas(int p0) {
    }
    
    public final float getDailyGoalProgress() {
        return 0.0F;
    }
    
    public final void setDailyGoalProgress(float p0) {
    }
    
    public final float getMonthlyGoalProgress() {
        return 0.0F;
    }
    
    public final void setMonthlyGoalProgress(float p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getLastWrittenDate() {
        return null;
    }
    
    public final void setLastWrittenDate(@org.jetbrains.annotations.Nullable
    java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component10() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final float component8() {
        return 0.0F;
    }
    
    public final float component9() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.models.Statistics copy(@org.jetbrains.annotations.NotNull
    java.lang.String userId, int todayCount, int totalCount, int currentStreak, int longestStreak, int todayMalas, int totalMalas, float dailyGoalProgress, float monthlyGoalProgress, @org.jetbrains.annotations.Nullable
    java.lang.Long lastWrittenDate) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}