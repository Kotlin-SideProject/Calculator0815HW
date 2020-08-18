package com.angus.calculator

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import java.util.jar.Attributes

class CalButton @JvmOverloads constructor(context: Context,
                                          attributeSet: AttributeSet? = null) : AppCompatButton(context, attributeSet){


    var isNumber = false
    var content = ""
    var position = 0
}