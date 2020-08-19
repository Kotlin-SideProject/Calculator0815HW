package com.angus.calculator

import android.app.Activity
import java.util.*

class SecretGame {
    init {
        reset()
    }
    var isEnd = false
    var secret = 0
    var times = 0
    var level = 1 //level 1 = easy, 2 = median , 3 = hard

    fun diff(number: Int) : Int {
        times++
        isEnd = if (number == secret) true else false
        return number - secret
    }

    fun reset() {
        when (level){
            1 -> secret = Random().nextInt(10) + 1
            2 -> secret = Random().nextInt(20) + 1
            3 -> secret = Random().nextInt(50) + 1
        }
        times = 0
    }
}