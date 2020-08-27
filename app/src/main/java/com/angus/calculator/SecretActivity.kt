package com.angus.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_secret.*

class SecretActivity : AppCompatActivity() {
    private lateinit var viewModel: SecretViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)
        //save sharePreferences setting
        getSharedPreferences("guess", MODE_PRIVATE).run {
            var times = this.getInt("TIMES", 0)
            if (times > 0){
                AlertDialog.Builder(this@SecretActivity)
                    .setTitle("Reset Game??")
                    .setPositiveButton("YES"){ _, _ ->
                        viewModel.secret = this.getInt("SECRET", 0)
                        counter.setText("${times}")
                        upper.setText("${this.getInt("UPPER", 100)}")
                        lower.setText("${this.getInt("LOWER", 1)}")
                        secretTitle.setText("please enter ($${this.getInt("LOWER", 1)}~${this.getInt("UPPER", 100)})")
                    }
                    .setNegativeButton("NO", null)
                    .show()

            }
        }
        viewModel = ViewModelProvider(this@SecretActivity).get(SecretViewModel::class.java)
        viewModel.lower.observe(this, Observer {
            lower.setText(it.toString())
            secretTitle.setText("please enter (${it}~${viewModel.upper.value})")
        })
        viewModel.upper.observe(this, Observer {
            upper.setText(it.toString())
            secretTitle.setText("please enter (${viewModel.lower.value}~${it})")
        })
        viewModel.times.observe(this, Observer {
            counter.setText(it.toString())
        })
        viewModel.result.observe(this, Observer {
            updateResult(it)
        })

        replay.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Reset game")
                .setMessage("Are you sure?")
                .setPositiveButton("OK") { dialog, which ->
                    viewModel.reset()
                    secretTitle.setText("please enter (${viewModel.lower.value}~${viewModel.upper.value})")
                }
                .setNeutralButton("Cancel", null)
                .show()

        }

        guess.setOnClickListener{
            var num : Int = number.text.toString().toInt()
            viewModel.guess(num)
        }


    }

    private fun updateResult(result: SecretViewModel.Result?) {
        AlertDialog.Builder(this)
            .setTitle("Guess")
            .setMessage("${result?.value} ${viewModel.times.value} Times")
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onPause() {
        super.onPause()

        //getSharedPreferences setting
        if(!viewModel.isEnd){
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("SECRET", viewModel.secret)
                .putInt("TIMES", viewModel.times.value!!)
                .putInt("UPPER", viewModel.upper.value!!)
                .putInt("LOWER", viewModel.lower.value!!)
                .apply()
        }
    }
}