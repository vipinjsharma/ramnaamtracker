package com.ramlekhak.data.models;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\'\u001a\u00020\bH\u00c6\u0003J\t\u0010(\u001a\u00020\bH\u00c6\u0003J\t\u0010)\u001a\u00020\u000bH\u00c6\u0003J\t\u0010*\u001a\u00020\rH\u00c6\u0003JO\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\bH\u00d6\u0001J\t\u00100\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u00a8\u00061"}, d2 = {"Lcom/ramlekhak/data/models/User;", "", "id", "", "name", "memberSince", "Ljava/util/Date;", "dailyGoal", "", "monthlyGoal", "selectedTheme", "Lcom/ramlekhak/data/models/Theme;", "selectedLanguage", "Lcom/ramlekhak/data/models/Language;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;IILcom/ramlekhak/data/models/Theme;Lcom/ramlekhak/data/models/Language;)V", "getDailyGoal", "()I", "setDailyGoal", "(I)V", "getId", "()Ljava/lang/String;", "getMemberSince", "()Ljava/util/Date;", "getMonthlyGoal", "setMonthlyGoal", "getName", "setName", "(Ljava/lang/String;)V", "getSelectedLanguage", "()Lcom/ramlekhak/data/models/Language;", "setSelectedLanguage", "(Lcom/ramlekhak/data/models/Language;)V", "getSelectedTheme", "()Lcom/ramlekhak/data/models/Theme;", "setSelectedTheme", "(Lcom/ramlekhak/data/models/Theme;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class User {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String name;
    @org.jetbrains.annotations.NotNull
    private final java.util.Date memberSince = null;
    private int dailyGoal;
    private int monthlyGoal;
    @org.jetbrains.annotations.NotNull
    private com.ramlekhak.data.models.Theme selectedTheme;
    @org.jetbrains.annotations.NotNull
    private com.ramlekhak.data.models.Language selectedLanguage;
    
    public User(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.util.Date memberSince, int dailyGoal, int monthlyGoal, @org.jetbrains.annotations.NotNull
    com.ramlekhak.data.models.Theme selectedTheme, @org.jetbrains.annotations.NotNull
    com.ramlekhak.data.models.Language selectedLanguage) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getMemberSince() {
        return null;
    }
    
    public final int getDailyGoal() {
        return 0;
    }
    
    public final void setDailyGoal(int p0) {
    }
    
    public final int getMonthlyGoal() {
        return 0;
    }
    
    public final void setMonthlyGoal(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.models.Theme getSelectedTheme() {
        return null;
    }
    
    public final void setSelectedTheme(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.models.Theme p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.models.Language getSelectedLanguage() {
        return null;
    }
    
    public final void setSelectedLanguage(@org.jetbrains.annotations.NotNull
    com.ramlekhak.data.models.Language p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.models.Theme component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.models.Language component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.ramlekhak.data.models.User copy(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.util.Date memberSince, int dailyGoal, int monthlyGoal, @org.jetbrains.annotations.NotNull
    com.ramlekhak.data.models.Theme selectedTheme, @org.jetbrains.annotations.NotNull
    com.ramlekhak.data.models.Language selectedLanguage) {
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