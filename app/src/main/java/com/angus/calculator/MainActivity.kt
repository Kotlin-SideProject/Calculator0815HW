package com.angus.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_games.view.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var gameTypes: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         gameTypes = arrayListOf(
                "計算機",
                "猜數字進階版",
                "終極密碼")

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        var adapter = object : RecyclerView.Adapter<GameHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
                var view = LayoutInflater.from(this@MainActivity).inflate(R.layout.row_games, parent, false)
                return GameHolder(view)
            }

            override fun getItemCount(): Int {
                return gameTypes.size
                Log.d("MainActivity.kt", "${gameTypes.size}");
            }

            override fun onBindViewHolder(holder: GameHolder, position: Int) {
                holder.game.text = gameTypes[position]
            }

        }

        recycler.adapter = adapter

    }


    class GameHolder(view : View) : RecyclerView.ViewHolder(view) {
        lateinit var game : TextView
        init {
            game = view.textView
        }
    }
}

