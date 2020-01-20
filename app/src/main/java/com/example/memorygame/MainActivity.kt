
package com.example.memorygame

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
                box_13, box_14, box_15, box_16
            )

        for (item in boxes){

        }
    }

    private fun


}
