package com.ramlekhak.ui.drawing

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paths = ArrayList<Pair<Path, Paint>>()
    private val currentPath = Path()
    private val currentPaint = Paint().apply {
        color = Color.WHITE
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 12f
    }
    
    private var startX = 0f
    private var startY = 0f
    
    // Store the current bitmap
    private var currentBitmap: Bitmap? = null
    
    init {
        // Set background color to dark
        setBackgroundColor(Color.parseColor("#252525"))
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.reset()
                currentPath.moveTo(x, y)
                startX = x
                startY = y
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.quadTo(startX, startY, (x + startX) / 2, (y + startY) / 2)
                startX = x
                startY = y
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                currentPath.lineTo(x, y)
                // Save the path
                paths.add(Pair(Path(currentPath), Paint(currentPaint)))
                currentPath.reset()
                invalidate()
            }
        }
        return false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        // Draw bitmap if available
        currentBitmap?.let {
            // Calculate position to center the bitmap
            val left = (width - it.width) / 2f
            val top = (height - it.height) / 2f
            
            // Draw the bitmap
            canvas.drawBitmap(it, left, top, null)
        }
        
        // Draw all saved paths
        for ((path, paint) in paths) {
            canvas.drawPath(path, paint)
        }
        
        // Draw current path
        canvas.drawPath(currentPath, currentPaint)
    }

    /**
     * Clear all drawn paths
     */
    fun clear() {
        paths.clear()
        currentPath.reset()
        currentBitmap = null
        invalidate()
    }

    /**
     * Get the current drawing as a bitmap
     */
    fun getBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.parseColor("#252525"))
        draw(canvas)
        return bitmap
    }
    
    /**
     * Set the drawing from a bitmap
     */
    fun setBitmap(bitmap: Bitmap) {
        // Clear current drawing
        clear()
        
        // Store the bitmap for drawing
        currentBitmap = bitmap
        
        // Force redraw
        invalidate()
    }
} 