package com.ramlekhak.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\nH\'J\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J$\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\n2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000fH\'J\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\u001e\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0019\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/ramlekhak/data/dao/CountDao;", "", "delete", "", "count", "Lcom/ramlekhak/data/Count;", "(Lcom/ramlekhak/data/Count;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCounts", "Lkotlinx/coroutines/flow/Flow;", "", "getAllCountsList", "getCountForDate", "date", "Ljava/util/Date;", "(Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCountsForMonth", "year", "", "month", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCountsForYear", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStreakCount", "startDate", "getTodayCount", "startOfDay", "endOfDay", "getTotalCount", "getTotalCountSince", "insert", "update", "app_debug"})
@androidx.room.Dao
public abstract interface CountDao {
    
    @androidx.room.Query(value = "SELECT * FROM counts ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.ramlekhak.data.Count>> getAllCounts();
    
    @androidx.room.Query(value = "SELECT * FROM counts ORDER BY date DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllCountsList(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.ramlekhak.data.Count>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(count) FROM counts")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM counts WHERE strftime(\'%Y-%m\', date/1000, \'unixepoch\') = :year || \'-\' || :month")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCountsForMonth(int year, int month, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.ramlekhak.data.Count>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM counts WHERE strftime(\'%Y\', date/1000, \'unixepoch\') = :year")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCountsForYear(int year, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.ramlekhak.data.Count>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM counts WHERE date >= :startOfDay AND date < :endOfDay")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.ramlekhak.data.Count>> getTodayCount(@org.jetbrains.annotations.NotNull
    java.util.Date startOfDay, @org.jetbrains.annotations.NotNull
    java.util.Date endOfDay);
    
    @androidx.room.Query(value = "SELECT * FROM counts WHERE date = :date LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCountForDate(@org.jetbrains.annotations.NotNull
    java.util.Date date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.ramlekhak.data.Count> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.Count count, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM counts WHERE date >= :startDate AND count > 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStreakCount(@org.jetbrains.annotations.NotNull
    java.util.Date startDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(count) FROM counts WHERE date >= :startDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalCountSince(@org.jetbrains.annotations.NotNull
    java.util.Date startDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.Count count, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.Count count, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM counts")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}