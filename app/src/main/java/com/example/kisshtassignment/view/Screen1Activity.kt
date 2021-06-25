package com.example.kisshtassignment.view

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kisshtassignment.R
import com.example.kisshtassignment.databinding.ActivityScreen1Binding
import com.example.kisshtassignment.model.Position
import java.util.*
import kotlin.collections.ArrayList


class Screen1Activity : AppCompatActivity() {
    var dataBinding: ActivityScreen1Binding? = null

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var random: Random = Random()
    lateinit var bitmap : Bitmap
    lateinit var canvas : Canvas
    var w = 0
    var h = 0
    lateinit var posList : ArrayList<Position>
    var checkCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_screen1)
        posList = ArrayList()
        val vto: ViewTreeObserver = dataBinding!!.llDraw.viewTreeObserver
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                dataBinding!!.llDraw.viewTreeObserver.removeOnPreDrawListener(this)
                h = dataBinding!!.llDraw.measuredHeight
                w = dataBinding!!.llDraw.measuredWidth
                bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
                canvas = Canvas(bitmap)

                return true
            }
        })

    }


    fun onCircleClick(view: View){
        val r = 50
        checkCount ++
        if (checkCount > 30) {
            Toast.makeText(this, "No space on screen to generate shape", Toast.LENGTH_SHORT).show()
            checkCount = 0
        }
        else {
            val p = random.nextInt(w).toFloat()
            val q = random.nextInt(h).toFloat()

            mPaint.style = Paint.Style.FILL
            mPaint.color = Color.RED

            var isDraw = true

            for (item in posList) {
                if ((item.x!! > (p+50) || item.x< (p-50)) && (item.y!! > (q+50) || item.y < (q-50))) {
                    // isDraw = true

                }
                else {
                    isDraw = false
                    break
                }
            }


            if (isDraw) {
                canvas.drawCircle(p, q, r.toFloat(), mPaint)
                val image = ImageView(this)
                image.setImageDrawable(BitmapDrawable(resources, bitmap))
                dataBinding!!.llDraw.addView(image)
                posList.add(Position(p,q))
                checkCount = 0
            }
            else {
                onCircleClick(view)
            }


        }

       
    }



    fun onRectClick(view: View){
        checkCount ++
        if (checkCount > 30) {
            Toast.makeText(this, "No space on screen to generate shape", Toast.LENGTH_SHORT).show()
            checkCount = 0
        }
        else {
            val p = random.nextInt(w-100)
            val q = random.nextInt(h-100)
            mPaint.style = Paint.Style.FILL
            mPaint.color = Color.RED


            var isDraw = true

            for (item in posList) {
                if ((item.x!! > (p+50) || item.x< (p-50)) && (item.y!! > (q+50) || item.y < (q-50))) {
                    // isDraw = true
                }
                else {
                    isDraw = false
                    break
                }
            }

            if (isDraw) {
                canvas.drawRect(Rect(p, q, p+100,q+100), mPaint)
             //   dataBinding!!.drawView.background = BitmapDrawable(resources, bitmap)
                val image = ImageView(this)
                image.setImageDrawable(BitmapDrawable(resources, bitmap))
                dataBinding!!.llDraw.addView(image)
                posList.add(Position(p.toFloat(),q.toFloat()))
                checkCount = 0
            }
            else {
                onRectClick(view)
            }
        }

    }


    fun onUndo(view : View) {
        val count: Int = dataBinding!!.llDraw.childCount
        Log.d("fff", count.toString())
        val view: ImageView = dataBinding!!.llDraw.getChildAt(count-1) as ImageView
        view.setImageDrawable(null)
        posList.removeAt(count-1)
        dataBinding!!.llDraw.removeView(view)
    }


}