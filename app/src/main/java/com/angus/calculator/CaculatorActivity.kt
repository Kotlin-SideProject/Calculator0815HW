package com.angus.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_caculator.*
import kotlinx.android.synthetic.main.single_button.view.*
import java.util.ArrayList

class CaculatorActivity : AppCompatActivity(){

    private lateinit var buttons: MutableList<NumberButton>
    private lateinit var numberIndex: ArrayList<Int>
    private lateinit var calculatorContent: ArrayList<String>
    var msg = StringBuilder()

    companion object{
        val TAG = "CaculatorActivity.kt"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caculator)
         calculatorContent =
            arrayListOf<String>(
                "AC", "Clear", "%", "/",
                "1", "2", "3", "*",
                "4", "5", "6", "-",
                "7", "8", "9", "+",
                "0", "00", ".", "=")
        numberIndex = arrayListOf<Int>(4, 5, 6, 8, 9, 10, 12, 13, 14, 16, 17, 18)
        //setting content
        buttons = mutableListOf<NumberButton>()
        for (i in 0..19){
            val button = NumberButton(this)
            button.content = calculatorContent.get(i)
            button.position = i
            if (numberIndex.contains(i)){
                button.isNumber = true
            }
            buttons.add(button)
//            Log.d(TAG, "buttons: ${buttons[i].isNumber}")
        }
        recycler.layoutManager = GridLayoutManager(this@CaculatorActivity, 4)
        recycler.setHasFixedSize(true)

        var adapter = object : RecyclerView.Adapter<CalculatorHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculatorHolder {
                val view = LayoutInflater.from(this@CaculatorActivity)
                    .inflate(R.layout.single_button2, parent, false)
                return CalculatorHolder(view)
            }

            override fun onBindViewHolder(holder: CalculatorHolder, position: Int) {
                holder.button.text = buttons.get(position).content
                Log.d(TAG, "content: ${buttons.get(position).content}")
                holder.button.isSelected = !buttons.get(position).isNumber
                holder.button.position = buttons.get(position).position
                holder.button.setOnClickListener{
                    var outputNumber : Int
                    var number  = (it as NumberButton)
                    Log.d(TAG, "number: ${number.isNumber} ${number.content} ${number.position}")
                    when ((it as NumberButton).position){
                        in 4..6 -> {
                            msg.append(it.content)
                            displayView.text = msg.toString()
                            Log.d(TAG, "msg: ${it.content}")
                        }
                        in 8..10-> {
                            msg.append(it.content)
                            displayView.text = msg.toString()
                        }
                        in 12..14-> {
                            msg.append(it.content)
                            displayView.text = msg.toString()
                        }
                        in 16..18->{
                            msg.append(it.content)
                            displayView.text = msg.toString()
                        }
                    }
                }
//                Log.d(TAG, "buttons: ${holder.button.isNumber}")
            }

            override fun getItemCount(): Int {
                return calculatorContent.size
            }

        }
        recycler.adapter = adapter
    }

    class CalculatorHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        lateinit var button : NumberButton
        init {
            button = itemView.findViewById(R.id.button2)
        }
    }

}