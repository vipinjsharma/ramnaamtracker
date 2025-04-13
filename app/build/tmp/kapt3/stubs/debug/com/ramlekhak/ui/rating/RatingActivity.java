package com.ramlekhak.ui.rating;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R#\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/ramlekhak/ui/rating/RatingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "laterButton", "Landroid/widget/Button;", "ratingBar", "Landroid/widget/RatingBar;", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "sharedPreferences$delegate", "Lkotlin/Lazy;", "statusText", "Landroid/widget/TextView;", "submitButton", "titleText", "checkPreviousRating", "", "incrementAppUsageCount", "initializeViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openPlayStore", "saveRating", "rating", "", "setupClickListeners", "Companion", "app_debug"})
public final class RatingActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.widget.RatingBar ratingBar;
    private android.widget.Button submitButton;
    private android.widget.Button laterButton;
    private android.widget.TextView statusText;
    private android.widget.TextView titleText;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy sharedPreferences$delegate = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREFS_NAME = "RamLekhakPrefs";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_APP_RATING = "app_rating";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_APP_RATED = "app_rated";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_APP_USAGE_COUNT = "app_usage_count";
    @org.jetbrains.annotations.NotNull
    public static final com.ramlekhak.ui.rating.RatingActivity.Companion Companion = null;
    
    public RatingActivity() {
        super();
    }
    
    private final android.content.SharedPreferences getSharedPreferences() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeViews() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void saveRating(float rating) {
    }
    
    private final void openPlayStore() {
    }
    
    private final void checkPreviousRating() {
    }
    
    private final void incrementAppUsageCount() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/ramlekhak/ui/rating/RatingActivity$Companion;", "", "()V", "KEY_APP_RATED", "", "KEY_APP_RATING", "KEY_APP_USAGE_COUNT", "PREFS_NAME", "shouldShowRatingPrompt", "", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean shouldShowRatingPrompt(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return false;
        }
    }
}