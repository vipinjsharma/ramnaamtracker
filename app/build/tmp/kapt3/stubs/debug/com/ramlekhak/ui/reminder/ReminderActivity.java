package com.ramlekhak.ui.reminder;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/ramlekhak/ui/reminder/ReminderActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "dateFormatter", "Ljava/text/SimpleDateFormat;", "reminderDialogHelper", "Lcom/ramlekhak/ui/reminder/ReminderDialogHelper;", "showDialogButton", "Landroid/widget/Button;", "statusTextView", "Landroid/widget/TextView;", "getDayName", "", "day", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupClickListeners", "setupReminderDialog", "updateStatusText", "settings", "Lcom/ramlekhak/ui/reminder/ReminderDialogHelper$ReminderSettings;", "app_debug"})
public final class ReminderActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.ramlekhak.ui.reminder.ReminderDialogHelper reminderDialogHelper;
    private android.widget.Button showDialogButton;
    private android.widget.TextView statusTextView;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormatter = null;
    
    public ReminderActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupReminderDialog() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void updateStatusText(com.ramlekhak.ui.reminder.ReminderDialogHelper.ReminderSettings settings) {
    }
    
    private final java.lang.String getDayName(int day) {
        return null;
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
}