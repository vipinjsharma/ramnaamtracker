package com.ramlekhak.data;

/**
 * Database view for monthly count aggregation
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lcom/ramlekhak/data/MonthlyCount;", "", "month", "", "total", "", "(Ljava/lang/String;I)V", "getMonth", "()Ljava/lang/String;", "getTotal", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.DatabaseView(value = "\n    SELECT \n        strftime(\'%Y-%m\', date/1000, \'unixepoch\') as month,\n        COALESCE(SUM(count), 0) as total\n    FROM counts \n    GROUP BY month\n    ORDER BY month ASC\n    ")
public final class MonthlyCount {
    @androidx.room.ColumnInfo(name = "month")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String month = null;
    @androidx.room.ColumnInfo(name = "total")
    private final int total = 0;
    
    public MonthlyCount(@org.jetbrains.annotations.NotNull
    java.lang.String month, int total) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMonth() {
        return null;
    }
    
    public final int getTotal() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.MonthlyCount copy(@org.jetbrains.annotations.NotNull
    java.lang.String month, int total) {
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