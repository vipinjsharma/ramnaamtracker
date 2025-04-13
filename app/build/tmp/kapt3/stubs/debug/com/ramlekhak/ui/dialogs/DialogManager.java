package com.ramlekhak.ui.dialogs;

/**
 * Utility class to manage and display various dialogs in the app
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/ramlekhak/ui/dialogs/DialogManager;", "", "()V", "Companion", "app_debug"})
public final class DialogManager {
    @org.jetbrains.annotations.NotNull
    public static final com.ramlekhak.ui.dialogs.DialogManager.Companion Companion = null;
    
    public DialogManager() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\f"}, d2 = {"Lcom/ramlekhak/ui/dialogs/DialogManager$Companion;", "", "()V", "showFeedbackDialog", "", "context", "Landroid/content/Context;", "showHowToUseDialog", "showPrivacyPolicyDialog", "showRateAppDialog", "showShareProgressDialog", "showTermsAndConditionsDialog", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Shows a dialog for sharing progress with different options
         */
        public final void showShareProgressDialog(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        /**
         * Shows a dialog for rating the app with stars and feedback
         */
        public final void showRateAppDialog(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        /**
         * Shows a dialog for submitting general feedback about the app
         */
        public final void showFeedbackDialog(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        /**
         * Shows the Privacy Policy dialog
         */
        public final void showPrivacyPolicyDialog(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        /**
         * Shows the Terms and Conditions dialog
         */
        public final void showTermsAndConditionsDialog(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        /**
         * Shows the How to Use dialog
         */
        public final void showHowToUseDialog(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
    }
}