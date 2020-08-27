package com.angus.calculator

import android.content.Context
import android.util.AttributeSet

class OperandButton(context: Context, attributeSet: AttributeSet? = null) :
    CalculatorButton(context, attributeSet) {

    override var isNumber = true

}