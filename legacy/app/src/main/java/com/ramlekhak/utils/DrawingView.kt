package com.ramlekhak.utils

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.ramlekhak.R
import kotlin.math.abs

/**
 * Custom View for drawing "राम" on a canvas.
 */
class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Drawing path
    private val drawPath = Path()
    
    // Canvas paint
    private val drawPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.drawing_line_color)
        strokeWidth = 8f
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }
    
    // Canvas and bitmap for drawing
    private var canvasBitmap: Bitmap? = null
    private var drawCanvas: Canvas? = null
    
    // Path for auto-drawing "राम"
    private val ramPath = Path()
    
    // For touch events
    private var startX = 0f
    private var startY = 0f
    private var lastX = 0f
    private var lastY = 0f
    
    // For storing path points for animation
    private val pathPoints = mutableListOf<Pair<Float, Float>>()
    private var isAutoDrawing = false
    private var onDrawingCompleteListener: (() -> Unit)? = null
    
    // For path animation
    private var pathAnimator: ValueAnimator? = null
    private var animationProgress = 0f

    init {
        // Create the "राम" path for auto-drawing
        createRamPath()
        
        // Enable hardware acceleration
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        // Create new bitmap and canvas when size changes
        canvasBitmap?.recycle()
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitmap!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        // Draw the existing bitmap
        canvasBitmap?.let { canvas.drawBitmap(it, 0f, 0f, null) }
        
        // Draw the current path
        canvas.drawPath(drawPath, drawPaint)
        
        // Draw the auto-draw animation if active
        if (isAutoDrawing) {
            val animPath = Path()
            val measure = PathMeasure(ramPath, false)
            measure.getSegment(0f, animationProgress * measure.length, animPath, true)
            canvas.drawPath(animPath, drawPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Ignore touch events during auto-drawing
        if (isAutoDrawing) return false
        
        val touchX = event.x.coerceIn(0f, width.toFloat())
        val touchY = event.y.coerceIn(0f, height.toFloat())
        
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawPath.moveTo(touchX, touchY)
                startX = touchX
                startY = touchY
                lastX = touchX
                lastY = touchY
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                // Use quadratic bezier to smooth the line
                drawPath.quadTo(
                    lastX,
                    lastY,
                    (touchX + lastX) / 2,
                    (touchY + lastY) / 2
                )
                lastX = touchX
                lastY = touchY
            }
            MotionEvent.ACTION_UP -> {
                drawPath.lineTo(touchX, touchY)
                drawCanvas?.drawPath(drawPath, drawPaint)
                drawPath.reset()
                parent.requestDisallowInterceptTouchEvent(false)
            }
            else -> return false
        }
        
        invalidate()
        return true
    }

    /**
     * Clear the canvas and reset paths
     */
    fun clearCanvas() {
        stopAutoDrawAnimation()
        drawPath.reset()
        drawCanvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        isAutoDrawing = false
        invalidate()
    }

    /**
     * Automatically draw "राम" on the canvas
     */
    fun autoDraw() {
        clearCanvas()
        isAutoDrawing = true
        
        // Calculate the center of the view
        val centerX = width / 2f
        val centerY = height / 2f
        
        // Create a matrix to position the path in the center
        val matrix = Matrix()
        val ramBounds = RectF()
        ramPath.computeBounds(ramBounds, true)
        
        // Scale to fit the view
        val scale = (width * 0.7f) / ramBounds.width()
        matrix.setScale(scale, scale)
        
        // Center the path
        matrix.postTranslate(
            centerX - (ramBounds.width() * scale) / 2,
            centerY - (ramBounds.height() * scale) / 2
        )
        
        // Apply the transformation
        val centeredPath = Path(ramPath)
        centeredPath.transform(matrix)
        
        // Start animation
        startAutoDrawAnimation(centeredPath)
    }

    /**
     * Start auto-draw animation
     */
    private fun startAutoDrawAnimation(path: Path) {
        pathAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 3000 // 3 seconds for full animation
            interpolator = LinearInterpolator()
            
            addUpdateListener { animator ->
                animationProgress = animator.animatedValue as Float
                invalidate()
                
                if (animationProgress >= 1f) {
                    // When animation completes, draw the final path to the canvas
                    drawCanvas?.drawPath(path, drawPaint)
                    isAutoDrawing = false
                    notifyDrawingComplete()
                }
            }
            
            start()
        }
    }

    /**
     * Stop auto-draw animation
     */
    private fun stopAutoDrawAnimation() {
        pathAnimator?.cancel()
        pathAnimator = null
        animationProgress = 0f
    }

    /**
     * Create the "राम" path for auto-drawing
     */
    private fun createRamPath() {
        // This is a simplified path for "राम" (Ram in Devanagari)
        // These coordinates create a basic representation and would need refining
        // for a more accurate depiction
        
        ramPath.apply {
            // र (RA)
            moveTo(10f, 50f)
            cubicTo(20f, 40f, 30f, 30f, 40f, 50f)
            lineTo(40f, 90f)
            
            moveTo(10f, 70f)
            lineTo(40f, 70f)
            
            // ा (AA matra)
            moveTo(40f, 40f)
            cubicTo(50f, 40f, 60f, 50f, 60f, 70f)
            cubicTo(60f, 90f, 50f, 100f, 40f, 100f)
            
            // म (MA)
            moveTo(70f, 50f)
            cubicTo(80f, 40f, 90f, 40f, 100f, 50f)
            lineTo(100f, 90f)
            
            moveTo(70f, 70f)
            lineTo(100f, 70f)
            
            // Horizontal line at bottom (optional)
            moveTo(10f, 100f)
            lineTo(100f, 100f)
        }
    }

    fun setOnDrawingCompleteListener(listener: () -> Unit) {
        onDrawingCompleteListener = listener
    }
    
    private fun notifyDrawingComplete() {
        onDrawingCompleteListener?.invoke()
    }
}
