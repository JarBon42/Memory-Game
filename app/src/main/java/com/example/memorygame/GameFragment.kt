package com.example.memorygame


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*
import timber.log.Timber
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.timerTask


/**
 * A simple [Fragment] subclass.
 */

//Declaring the guess variables, so they can for color-matching logic
var guess1: View? = null
var guess2: View? = null

//Declaring the HashMap as a HashMap so it can hold values
var colorMap: HashMap<TextView, Int> = HashMap<TextView, Int>()
var matchMap: HashMap<TextView, Boolean> = HashMap()
var currentColorMap: HashMap<TextView, Int> = HashMap()


var score: Int = 0

const val KEY_COLORMAP = "currentColorMap_key"
const val KEY_MATCHMAP = "matchMap_key"
const val KEY_SCORE = "score_key"

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        Timber.i("onViewCreate called")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){
            score = savedInstanceState.getInt(KEY_SCORE, 0)

            colorMap = savedInstanceState.get(KEY_COLORMAP) as HashMap<TextView, Int>
            matchMap = savedInstanceState.get(KEY_MATCHMAP) as HashMap<TextView, Boolean>


        }



    }
    private fun setlisteners() {
        val boxes: List<TextView> =
            listOf( //Records clickables
                box_1, box_2, box_3, box_4,
                box_5, box_6, box_7, box_8,
                box_9, box_10, box_11, box_12,
                box_13, box_14, box_15, box_16

            )

        for (item in boxes) {
            item.setOnClickListener {
                revealColor(item)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY_COLORMAP, colorMap)
        outState.putSerializable(KEY_MATCHMAP, matchMap)
        outState.putInt(KEY_SCORE, score)

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
    */

    private fun revealColor(view: TextView) {  //Sets the boxes to their colors

        var color = colorMap[view]
        view.setBackgroundResource(color!!)

     /*
        when (view?.id) {

            R.id.box_1 -> view.setBackgroundResource(R.color.red)
            R.id.box_2 -> view.setBackgroundResource(R.color.green)
            R.id.box_3 -> view.setBackgroundResource(R.color.brown)
            R.id.box_4 -> view.setBackgroundResource(R.color.teal)
            R.id.box_5 -> view.setBackgroundResource(R.color.blue)
            R.id.box_6 -> view.setBackgroundResource(R.color.purple)
            R.id.box_7 -> view.setBackgroundResource(R.color.pink)
            R.id.box_8 -> view.setBackgroundResource(R.color.yellow)
            R.id.box_9 -> view.setBackgroundResource(R.color.red)
            R.id.box_10 -> view.setBackgroundResource(R.color.green)
            R.id.box_11 -> view.setBackgroundResource(R.color.brown)
            R.id.box_12 -> view.setBackgroundResource(R.color.teal)
            R.id.box_13 -> view.setBackgroundResource(R.color.blue)
            R.id.box_14 -> view.setBackgroundResource(R.color.purple)
            R.id.box_15 -> view.setBackgroundResource(R.color.pink)
            R.id.box_16 -> view.setBackgroundResource(R.color.yellow)
        }*/

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

            var correctColor = colorMap.get(guess1)!!

            //Removes clickability from correct boxes
            guess1?.isClickable = false
            guess2?.isClickable = false

            //Adds correct matches to a hashMap
            matchMap.put(guess1 as TextView,true)
            matchMap.put(guess2 as TextView,true)

            //Adds correct colors to a hashMap
            currentColorMap.put(guess1 as TextView,correctColor)
            currentColorMap.put(guess2 as TextView,correctColor)

            guess1 = null
            guess2 = null
        }
    }

    override fun onStart() {
        super.onStart()

        if(colorMap.isNullOrEmpty()){

            //Adds boxes to colorMap
            colorMap.put(box_1, R.color.red)
            colorMap.put(box_2, R.color.green)
            colorMap.put(box_3, R.color.brown)
            colorMap.put(box_4, R.color.teal)
            colorMap.put(box_5, R.color.blue)
            colorMap.put(box_6, R.color.purple)
            colorMap.put(box_7, R.color.pink)
            colorMap.put(box_8, R.color.yellow)
            colorMap.put(box_9, R.color.red)
            colorMap.put(box_10, R.color.green)
            colorMap.put(box_11, R.color.brown)
            colorMap.put(box_12, R.color.teal)
            colorMap.put(box_13, R.color.blue)
            colorMap.put(box_14, R.color.purple)
            colorMap.put(box_15, R.color.pink)
            colorMap.put(box_16, R.color.yellow)


        }
        if(matchMap.isNullOrEmpty()){
            //Adds boxes to match records
            matchMap.put(box_1, false)
            matchMap.put(box_2, false)
            matchMap.put(box_3, false)
            matchMap.put(box_4, false)
            matchMap.put(box_5, false)
            matchMap.put(box_6, true)
            matchMap.put(box_7, false)
            matchMap.put(box_8, false)
            matchMap.put(box_9, true)
            matchMap.put(box_10, false)
            matchMap.put(box_11, false)
            matchMap.put(box_12, false)
            matchMap.put(box_13, false)
            matchMap.put(box_14, false)
            matchMap.put(box_15, false)
            matchMap.put(box_16, false)

        }



        current_score_text.text = score.toString()

        for((box, correct ) in matchMap){
           if(correct)
           {
               val color = colorMap[box]
               box.setBackgroundResource(color!!)
           }
        }
        setlisteners()  // Sets up the boxes as buttons

        Timber.i("onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }
}
