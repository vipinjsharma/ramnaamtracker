package com.ramlekhak.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0006H\u0007J\b\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0006H\u0007J\u0012\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/ramlekhak/ui/WebAppInterface;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getDeviceLanguage", "", "getSystemTheme", "openPlayStore", "", "shareProgress", "text", "showToast", "message", "vibrate", "duration", "", "app_debug"})
public final class WebAppInterface {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    
    public WebAppInterface(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @android.webkit.JavascriptInterface
    public final void vibrate(long duration) {
    }
    
    @android.webkit.JavascriptInterface
    public final void showToast(@org.jetbrains.annotations.NotNull
    java.lang.String message) {
    }
    
    @android.webkit.JavascriptInterface
    public final void shareProgress(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
    }
    
    @android.webkit.JavascriptInterface
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDeviceLanguage() {
        return null;
    }
    
    @android.webkit.JavascriptInterface
    public final void openPlayStore() {
    }
    
    @android.webkit.JavascriptInterface
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSystemTheme() {
        return null;
    }
}