package com.example.memorygame

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.random
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.timerTask

//Declaring the guess variables, so they can for color-matching logic
var guess1: View? = null
var guess2: View? = null

//Declaring the HashMap as a HashMap so it can hold values
var colorMap: HashMap<TextView, Int> = HashMap<TextView, Int>()


var score: Int = 0

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor") //Required to get colors

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setlisteners()  // Sets up the boxes as buttons

        //Adds boxes to colorMap
        colorMap.run {
            put(box_1, R.color.red)
            put(box_2, R.color.green)
            put(box_3, R.color.brown)
            put(box_4, R.color.teal)
            put(box_5, R.color.blue)
            put(box_6, R.color.purple)
            put(box_7, R.color.pink)
            put(box_8, R.color.yellow)
            put(box_9, R.color.red)
            put(box_10, R.color.green)
            put(box_11, R.color.brown)
            put(box_12, R.color.teal)
            put(box_13, R.color.blue)
            put(box_14, R.color.purple)
            put(box_15, R.color.pink)
            put(box_16, R.color.yellow)
        }
    }

    private fun setlisteners() {
        val boxes: List<View> =
            listOf( //Records clickables
                box_1, box_2, box_3, box_4,
                box_5, box_6, box_7, box_8,
                box_9, box_10, box_11, box_12,
                box_13, box_14, box_15, box_16

            )

        for (item in boxes) {
            item.setOnClickListener {
                revealColor(it)

            }
        }
    }

    /*
    fun randomColor(Squares: List<View>) {
        val colorList : List<Int>  =
            listOf( //Colors to be randomized with
                R.color.red, R.color.green,
                R.color.brown, R.color.teal,
                R.color.blue, R.color.purple,
                R.color.pink, R.color.yellow
            )

        for (color in colorList) {
            var ran1 = random().toInt()
            var ran2 = random().toInt()

            Squares.get(ran1) = Squares[ran1].setBackgroundResource()

        }
    }
    */

    private fun setSilencer() {
        val boxes: List<View> =
            listOf( //Records clickables
                box_1, box_2, box_3, box_4,
                box_5, box_6, box_7, box_8,
                box_9, box_10, box_11, box_12,
                box_13, box_14, box_15, box_16

            )

        for (item in boxes) {
            item.isClickable = false


            Timer().schedule(timerTask {
                //Timer is re-enable clickability on the app
                item.isClickable = true
            }, 2000)
        }
    }

    private fun revealColor(view: View) {  //Sets the boxes to their colors
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

        takeAguess(view)  // Collects current guess
    }

    private fun hideColors() {  //Reset the current guesses to nothing
        guess1?.setBackgroundResource(R.color.white)
        guess2?.setBackgroundResource(R.color.white)

        guess1 = null
        guess2 = null
    }


    private fun takeAguess(playerChoice: View) { //Takes track of Player's attempts
        if (guess1 == null) {
            guess1 = playerChoice

        } else {
            guess2 = playerChoice

            //setSilencer()

            correctCheck()
        }
    }


    private fun correctCheck() {  //Checks if the current guess are right or not
        if (colorMap.get(guess1) != colorMap.get(guess2)) {
            Message_text.setText(R.string.Wrong_message)


            Timer().schedule(timerTask {
                hideColors()  //Timer is used to delay hiding the results after a short moment
            }, 500)

        } else {
            score += 5  //Increases score by 5 points
            current_score_text.text = score.toString()

            if (score >= 40) {  //Gives the victory message
                Message_text.setText(R.string.Win_message)
            } else {  //Gives normal correct answer message
                Message_text.setText(R.string.Right_message)
            }

            guess1?.isClickable = false
            guess2?.isClickable = false

            guess1 = null
            guess2 = null
        }
    }
}
