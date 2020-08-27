package com.angus.calculator

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

open class CalculatorButton @JvmOverloads constructor(context: Context,
                                                      attributeSet: AttributeSet? = null) : AppCompatButton(context, attributeSet){


    open var isNumber = false
    open var content = ""
    open var position = 0
}