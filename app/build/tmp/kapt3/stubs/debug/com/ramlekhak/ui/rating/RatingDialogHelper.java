package com.ramlekhak.ui.rating;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 )2\u00020\u0001:\u0002)*B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0017\u001a\u00020\u0010J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0002J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u000fH\u0002J\u001a\u0010\u001e\u001a\u00020\u00102\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eJ\u0010\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\nH\u0002J\b\u0010\"\u001a\u00020\u0010H\u0002J\u000e\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020\u0010J\b\u0010\'\u001a\u00020\u0010H\u0002J\b\u0010(\u001a\u00020\u0019H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/ramlekhak/ui/rating/RatingDialogHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "closeButton", "Landroid/widget/ImageView;", "commentInput", "Landroid/widget/EditText;", "currentRating", "", "dialog", "Landroid/app/Dialog;", "onRatingSubmittedListener", "Lkotlin/Function1;", "Lcom/ramlekhak/ui/rating/RatingDialogHelper$RatingData;", "", "prefs", "Landroid/content/SharedPreferences;", "starViews", "", "submitButton", "Landroid/widget/Button;", "dismiss", "hasRatedRecently", "", "initDialog", "resetDialog", "saveRatingRecord", "ratingData", "setOnRatingSubmittedListener", "listener", "setRating", "rating", "setupListeners", "shouldShowRatingDialog", "usageCount", "", "showDialog", "submitRating", "validateRating", "Companion", "RatingData", "app_debug"})
public final class RatingDialogHelper {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable
    private android.app.Dialog dialog;
    private android.widget.ImageView closeButton;
    private java.util.List<? extends android.widget.ImageView> starViews;
    private android.widget.EditText commentInput;
    private android.widget.Button submitButton;
    private float currentRating = 0.0F;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super com.ramlekhak.ui.rating.RatingDialogHelper.RatingData, kotlin.Unit> onRatingSubmittedListener;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREFS_NAME = "RatingPreferences";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_LAST_RATING = "last_rating";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_LAST_RATING_TIME = "last_rating_time";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_HAS_RATED = "has_rated";
    private static final int USAGE_THRESHOLD = 5;
    @org.jetbrains.annotations.NotNull
    public static final com.ramlekhak.ui.rating.RatingDialogHelper.Companion Companion = null;
    
    public RatingDialogHelper(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final void setOnRatingSubmittedListener(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.ramlekhak.ui.rating.RatingDialogHelper.RatingData, kotlin.Unit> listener) {
    }
    
    public final void showDialog() {
    }
    
    private final void initDialog() {
    }
    
    private final void setupListeners() {
    }
    
    private final void setRating(float rating) {
    }
    
    private final boolean validateRating() {
        return false;
    }
    
    private final void submitRating() {
    }
    
    private final void resetDialog() {
    }
    
    private final void saveRatingRecord(com.ramlekhak.ui.rating.RatingDialogHelper.RatingData ratingData) {
    }
    
    public final boolean shouldShowRatingDialog(int usageCount) {
        return false;
    }
    
    private final boolean hasRatedRecently() {
        return false;
    }
    
    public final void dismiss() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/ramlekhak/ui/rating/RatingDialogHelper$Companion;", "", "()V", "KEY_HAS_RATED", "", "KEY_LAST_RATING", "KEY_LAST_RATING_TIME", "PREFS_NAME", "USAGE_THRESHOLD", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/ramlekhak/ui/rating/RatingDialogHelper$RatingData;", "", "rating", "", "comment", "", "timestamp", "Ljava/util/Date;", "(FLjava/lang/String;Ljava/util/Date;)V", "getComment", "()Ljava/lang/String;", "getRating", "()F", "getTimestamp", "()Ljava/util/Date;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class RatingData {
        private final float rating = 0.0F;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String comment = null;
        @org.jetbrains.annotations.NotNull
        private final java.util.Date timestamp = null;
        
        public RatingData(float rating, @org.jetbrains.annotations.NotNull
        java.lang.String comment, @org.jetbrains.annotations.NotNull
        java.util.Date timestamp) {
            super();
        }
        
        public final float getRating() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getComment() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.Date getTimestamp() {
            return null;
        }
        
        public final float component1() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.Date component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.ramlekhak.ui.rating.RatingDialogHelper.RatingData copy(float rating, @org.jetbrains.annotations.NotNull
        java.lang.String comment, @org.jetbrains.annotations.NotNull
        java.util.Date timestamp) {
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
}