package com.angus.calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_guess.*
import java.lang.NumberFormatException

class GuessActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val game = SecretGame()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess)
// test save shared preferences
        val setting = getSharedPreferences("guess", Context.MODE_PRIVATE)
        val times = setting.getInt("TIMES", 0)
        val level : Int = setting.getInt("LEVEL", 1)
        if (level == 1){
            radioButton1.isChecked = true
            textView.text = "Please enter a number (1-10)"
        }
        else if (level == 2){
            radioButton2.isChecked = true
            textView.text = "Please enter a number (1-20)"
        }

        else if (level == 3){
            radioButton3.isChecked = true
            textView.text = "Please enter a number (1-50)"
        }

        if (times > 0) {
            val secret = setting.getInt("SECRET", 0)
            game.secret = secret
            game.times = times
            counter.setText(times.toString())
        }
        radioButton1.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked){
                game.level = 1
                resetGame()
                textView.text = "Please enter a number (1-10)"
            }
        }
        radioButton2.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked){
                game.level = 2
                resetGame()
                textView.text = "Please enter a number (1-20)"
            }
        }
        radioButton3.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked){
                game.level = 3
                resetGame()
                textView.text = "Please enter a number (1-50)"
            }
        }
        replay.setOnClickListener {
            AlertDialog.Builder(this@GuessActivity)
                .setTitle("Reset game")
                .setMessage("Are you sure?")
                .setPositiveButton("OK") { dialog, which ->
                    game.reset()
                }
                .setNeutralButton("Cancel", null)
                .show()
        }
        guess.setOnClickListener {
            try {
                val num = number.text.toString().toInt()
                Log.d("MainActivity", num.toString())
                val diff = game.diff(num)
                var message = if (diff > 0) {
                    "Smaller"
                } else if (diff < 0) {
                    "Bigger"
                } else {
                    "You got it"
                }
                counter.setText(game.times.toString())
                AlertDialog.Builder(this)
                    .setTitle("Guess")
                    .setMessage("$message  ${game.times} Times")
                    .setPositiveButton("OK", null)
                    .show()
                //show message
            } catch (e : NumberFormatException) {
                //error message to user
                Toast.makeText(this, "Format Error", Toast.LENGTH_LONG).show()
            }
        }

        /*val guessButton = findViewById<Button>(R.id.guess)
        guessButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val num = findViewById<EditText>(R.id.number)
                val s = num.text.toString()
                val n = s.toInt()
                println(n)
            }
        })*/
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!game.isEnd) {
            //save data
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putInt("SECRET", game.secret)
                .putInt("TIMES", game.times)
                .putInt("LEVEL", game.level)
                .commit()
        }
    }

    private fun resetGame() {
        game.reset()
        counter.setText(game.times.toString())
    }


}