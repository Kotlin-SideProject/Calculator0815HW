package com.angus.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.sql.ResultSet

class SecretViewModel : ViewModel() {
    var lower = MutableLiveData<Int>()
    var upper = MutableLiveData<Int>()
    var times = MutableLiveData<Int>()
    var result = MutableLiveData<Result>()
    var secret = (1..100).random()
    var isEnd = false

    init {
        lower.value = 1
        upper.value = 100
        times.value = 0
    }

    fun guess(number: Int) {
        times.value = times.value!! + 1
        result.value = when {
            number - secret > 0 -> {
                if (number < upper.value!!){
                    upper.value =  number
                }
                Result.SMALLER
            }
            number - secret < 0 -> {
                if (number > lower.value!!){
                    lower.value = number
                }
                Result.BIGGER
            }
            else ->{
                isEnd = true
                Result.BINGO//when 語法會將最後的值回傳，因此不能將sEnd放在最後
            }

        }
    }

    fun reset(){
        secret = (1..100).random()
        upper.value = 100
        lower.value = 1
        times.value = 0
        isEnd = false
    }

    enum class Result(val value: String) {
        BIGGER("Bigger"), SMALLER("Smaller"), BINGO("You got it!")
    }
}