package com.ramlekhak.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.ramlekhak.R

class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var path = Path()
    private var paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.primary_orange)
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 12f
        isAntiAlias = true
    }

    private var paths = mutableListOf<Path>()
    private var bitmap: Bitmap? = null
    private var canvas: Canvas? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bitmap?.let { canvas.drawBitmap(it, 0f, 0f, null) }
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {
                canvas?.drawPath(path, paint)
                paths.add(path)
                path = Path()
            }
        }
        invalidate()
        return true
    }

    fun clear() {
        path = Path()
        paths.clear()
        bitmap?.eraseColor(Color.TRANSPARENT)
        invalidate()
    }

    fun clearCanvas() {
        clear()
    }

    fun getBitmap(): Bitmap? = bitmap?.copy(Bitmap.Config.ARGB_8888, true)

    fun autoDraw() {
        // Define the RAM character strokes
        clear()
        val centerX = width / 2f
        val centerY = height / 2f
        val size = minOf(width, height) / 3f

        // Draw राम
        path.apply {
            // र
            moveTo(centerX - size, centerY - size/2)
            lineTo(centerX - size/2, centerY - size/2)
            lineTo(centerX - size/2, centerY + size/2)
            
            // ा
            moveTo(centerX - size/4, centerY - size/3)
            lineTo(centerX, centerY - size/3)
            lineTo(centerX, centerY + size/3)
            
            // म
            moveTo(centerX + size/4, centerY - size/2)
            lineTo(centerX + size, centerY - size/2)
            quadTo(centerX + size, centerY, centerX + size/2, centerY)
            lineTo(centerX + size/2, centerY + size/2)
        }

        canvas?.drawPath(path, paint)
        paths.add(path)
        path = Path()
        invalidate()
    }
} 