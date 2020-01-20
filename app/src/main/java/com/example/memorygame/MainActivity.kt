
package com.example.memorygame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setlisteners()
    }

    private fun setlisteners(){ //Makes the boxes clickable
        val boxes: List<View> =
            listOf(
                box_1, box_2, box_3, box_4,
                box_5, box_6, box_7, box_8,
                box_9, box_10, box_11, box_12,
                box_13, box_14, box_15, box_16,
                background_constrait

            )

        for (item in boxes){
            item.setOnClickListener({revealColor(it)})
        }
    }

    @SuppressLint("ResourceAsColor") //Required to get colors
    private fun revealColor(view: View) {
       when (view.id) {
           R.id.box_1 -> box_1.setBackgroundResource(R.color.red)
           R.id.box_2 -> box_2.setBackgroundResource(R.color.green)
           R.id.box_3 -> box_3.setBackgroundResource(R.color.brown)
           R.id.box_4 -> box_4.setBackgroundResource(R.color.teal)
           R.id.box_5 -> box_5.setBackgroundResource(R.color.blue)
           R.id.box_6 -> box_6.setBackgroundResource(R.color.purple)
           R.id.box_7 -> box_7.setBackgroundResource(R.color.pink)
           R.id.box_8 -> box_8.setBackgroundResource(R.color.yellow)
           R.id.box_9 -> box_9.setBackgroundResource(R.color.red)
           R.id.box_10 -> box_10.setBackgroundResource(R.color.green)
           R.id.box_11 -> box_11.setBackgroundResource(R.color.brown)
           R.id.box_12 -> box_12.setBackgroundResource(R.color.teal)
           R.id.box_13 -> box_13.setBackgroundResource(R.color.blue)
           R.id.box_14 -> box_14.setBackgroundResource(R.color.purple)
           R.id.box_15 -> box_15.setBackgroundResource(R.color.pink)
           R.id.box_16 -> box_16.setBackgroundResource(R.color.yellow)
       }


    }


}
