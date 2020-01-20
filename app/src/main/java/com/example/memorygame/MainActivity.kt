package com.example.memorygame

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

//Declaring the guess variables, so they can for color-matching logic
var guess1: View? = null
var guess2: View? = null
var score: Int = 0


class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor") //Required to get colors

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setlisteners()
    }

    private fun setlisteners() {  // Sets up the boxes as buttons
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

    private fun revealColor(view: View) {  //Sets the boxes to their colors
        when (view.id) {
            R.id.box_1 -> box_1.setBackgroundResource(R.drawable.red)
            R.id.box_2 -> box_2.setBackgroundResource(R.drawable.green)
            R.id.box_3 -> box_3.setBackgroundResource(R.drawable.brown)
            R.id.box_4 -> box_4.setBackgroundResource(R.drawable.teal)
            R.id.box_5 -> box_5.setBackgroundResource(R.drawable.blue)
            R.id.box_6 -> box_6.setBackgroundResource(R.drawable.purple)
            R.id.box_7 -> box_7.setBackgroundResource(R.drawable.pink)
            R.id.box_8 -> box_8.setBackgroundResource(R.drawable.yellow)
            R.id.box_9 -> box_9.setBackgroundResource(R.drawable.red)
            R.id.box_10 -> box_10.setBackgroundResource(R.drawable.green)
            R.id.box_11 -> box_11.setBackgroundResource(R.drawable.brown)
            R.id.box_12 -> box_12.setBackgroundResource(R.drawable.teal)
            R.id.box_13 -> box_13.setBackgroundResource(R.drawable.blue)
            R.id.box_14 -> box_14.setBackgroundResource(R.drawable.purple)
            R.id.box_15 -> box_15.setBackgroundResource(R.drawable.pink)
            R.id.box_16 -> box_16.setBackgroundResource(R.drawable.yellow)
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

            correctCheck()
        }
    }

    private fun correctCheck(){  //Checks if the current guess are right or not
        if (guess1?.background != guess2?.background) {
            Message_text.setText(R.string.Wrong_message)

            hideColors()
        } else {
            score += 5  //Increases score by 5 points
            current_score_text.text = score.toString()

            if (score >= 40) {  //Gives the victory message
                Message_text.setText(R.string.Win_message)
            } else {  //Gives normal correct answer message
                Message_text.setText(R.string.Right_message)
            }

            guess1 = null
            guess2 = null
        }
    }
}
