package com.ramlekhak.utils;

/**
 * Custom View for drawing "राम" on a canvas.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\"\u001a\u00020\u0019J\u0006\u0010#\u001a\u00020\u0019J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0002J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020\u000eH\u0014J(\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0007H\u0014J\u0010\u0010-\u001a\u00020\u00142\u0006\u0010.\u001a\u00020/H\u0016J\u0014\u00100\u001a\u00020\u00192\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018J\u0010\u00102\u001a\u00020\u00192\u0006\u00103\u001a\u00020\u0012H\u0002J\b\u00104\u001a\u00020\u0019H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u001c\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u001e0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/ramlekhak/utils/DrawingView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "animationProgress", "", "canvasBitmap", "Landroid/graphics/Bitmap;", "drawCanvas", "Landroid/graphics/Canvas;", "drawPaint", "Landroid/graphics/Paint;", "drawPath", "Landroid/graphics/Path;", "isAutoDrawing", "", "lastX", "lastY", "onDrawingCompleteListener", "Lkotlin/Function0;", "", "pathAnimator", "Landroid/animation/ValueAnimator;", "pathPoints", "", "Lkotlin/Pair;", "ramPath", "startX", "startY", "autoDraw", "clearCanvas", "createRamPath", "notifyDrawingComplete", "onDraw", "canvas", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "setOnDrawingCompleteListener", "listener", "startAutoDrawAnimation", "path", "stopAutoDrawAnimation", "app_debug"})
public final class DrawingView extends android.view.View {
    @org.jetbrains.annotations.NotNull
    private final android.graphics.Path drawPath = null;
    @org.jetbrains.annotations.NotNull
    private final android.graphics.Paint drawPaint = null;
    @org.jetbrains.annotations.Nullable
    private android.graphics.Bitmap canvasBitmap;
    @org.jetbrains.annotations.Nullable
    private android.graphics.Canvas drawCanvas;
    @org.jetbrains.annotations.NotNull
    private final android.graphics.Path ramPath = null;
    private float startX = 0.0F;
    private float startY = 0.0F;
    private float lastX = 0.0F;
    private float lastY = 0.0F;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> pathPoints = null;
    private boolean isAutoDrawing = false;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> onDrawingCompleteListener;
    @org.jetbrains.annotations.Nullable
    private android.animation.ValueAnimator pathAnimator;
    private float animationProgress = 0.0F;
    
    @kotlin.jvm.JvmOverloads
    public DrawingView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @java.lang.Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    }
    
    @java.lang.Override
    protected void onDraw(@org.jetbrains.annotations.NotNull
    android.graphics.Canvas canvas) {
    }
    
    @java.lang.Override
    public boolean onTouchEvent(@org.jetbrains.annotations.NotNull
    android.view.MotionEvent event) {
        return false;
    }
    
    /**
     * Clear the canvas and reset paths
     */
    public final void clearCanvas() {
    }
    
    /**
     * Automatically draw "राम" on the canvas
     */
    public final void autoDraw() {
    }
    
    /**
     * Start auto-draw animation
     */
    private final void startAutoDrawAnimation(android.graphics.Path path) {
    }
    
    /**
     * Stop auto-draw animation
     */
    private final void stopAutoDrawAnimation() {
    }
    
    /**
     * Create the "राम" path for auto-drawing
     */
    private final void createRamPath() {
    }
    
    public final void setOnDrawingCompleteListener(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> listener) {
    }
    
    private final void notifyDrawingComplete() {
    }
    
    @kotlin.jvm.JvmOverloads
    public DrawingView(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads
    public DrawingView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null);
    }
}