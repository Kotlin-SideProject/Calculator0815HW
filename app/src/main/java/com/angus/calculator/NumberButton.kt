package com.angus.calculator

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class NumberButton @JvmOverloads constructor(context: Context,
                                             attributeSet: AttributeSet? = null) : AppCompatButton(context, attributeSet){

    var content = ""
    var isNumber : Boolean = false
    var position = 0

}