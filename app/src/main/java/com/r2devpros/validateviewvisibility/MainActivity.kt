package com.r2devpros.validateviewvisibility

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.r2devpros.validateviewvisibility.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var layout: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layout.mainScroll.setOnScrollChangeListener { _, _, _, _, _ ->
            validateVisibility()
        }
    }

    private fun validateVisibility() {
        val elementsArray = arrayListOf<TextView>().apply {
            add(layout.element1)
            add(layout.element2)
            add(layout.element3)
            add(layout.element4)
            add(layout.element5)
            add(layout.element6)
        }

        elementsArray.forEach { textView ->
            val currentElement = resources.getResourceEntryName(textView.id)
            val result =
                textView.isVisibleOnScreen(layout.mainScroll.width, layout.mainScroll.height)

            when (currentElement) {
                "element1" -> {
                    layout.element1Status.text = result.toString()
                }
                "element2" -> {
                    layout.element2Status.text = result.toString()
                }
                "element3" -> {
                    layout.element3Status.text = result.toString()
                }
                "element4" -> {
                    layout.element4Status.text = result.toString()
                }
                "element5" -> {
                    layout.element5Status.text = result.toString()
                }
                else -> {
                    layout.element6Status.text = result.toString()
                }
            }
        }
    }

    /*
         private fun View?.isVisibleOnScreen(screenWidth: Int, screenHeight: Int): Boolean {
             if (this == null) {
                 return false
             }

             if (!this.isShown) {
                 return false
             }

             val actualPosition = Rect()
             this.getGlobalVisibleRect(actualPosition)
              return (this.height == actualPosition.height() && this.width == actualPosition.width())
         }
    */
    private fun View?.isVisibleOnScreen(screenWidth: Int, screenHeight: Int): Boolean {
        if (this == null) {
            return false
        }

        if (!this.isShown) {
            return false
        }

        val actualPosition = Rect()
        this.getGlobalVisibleRect(actualPosition)
        val screen = Rect(0, 0, screenWidth, screenHeight)
        return actualPosition.intersect(screen)
    }
}