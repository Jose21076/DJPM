package com.example.calculator
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.calculator.components.CalcButton

class Calculate(var num1: String, var num2: String) {
    fun divide(): String {
        return (num1.toFloat() / num2.toFloat()).toString()
    }
    fun multiply(): String {
        return (num1.toFloat() * num2.toFloat()).toString()
    }
    fun subtract(): String {
        return (num1.toFloat() - num2.toFloat()).toString()
    }
    fun add(): String {
        return (num1.toFloat() + num2.toFloat()).toString()
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier.background( color = Color.Black)){
    var prev_num by remember { mutableStateOf("")}
    var num by remember {mutableStateOf("")}
    var opr by remember {mutableStateOf("")}
    var calc: Calculate

    fun oprPress(op: String){
        prev_num = num
        num = ""
        opr = op
    }

    fun numPress( n: String){
        num += n
    }

    fun setDisplay (value:Float) : String{
        if (value % 1 == 0.toFloat()) return value.toInt().toString()
        else return value.toString()
    }

    Column(modifier = modifier.padding(16.dp).fillMaxSize()) {
        Text(modifier = Modifier.weight(.15f)
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .border(5.dp,color = Color.DarkGray),
            textAlign = TextAlign.Right,
            text = num + " ",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.weight(.2f)){
            CalcButton(modifier = Modifier.weight(1f),
                label = "7",
                onClick = {numPress("7")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "8",
                onClick = {numPress("8")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "9",
                onClick = {numPress("9")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "/",
                isOperation = true,
                onClick = { oprPress("/") }
            )
        }
        Row(modifier = Modifier.weight(.2f)){
            CalcButton(modifier = Modifier.weight(1f),
                label = "4",
                onClick = {numPress("4")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "5",
                onClick = {numPress("5")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "6",
                onClick = {numPress("6")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "x",
                isOperation = true,
                onClick = { oprPress("X") }
            )
        }
        Row(modifier = Modifier.weight(.2f)){
            CalcButton(modifier = Modifier.weight(1f),
                label = "1",
                onClick = {numPress("1")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "2",
                onClick = {numPress("2")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "3",
                onClick = {numPress("3")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "-",
                isOperation = true,
                onClick = {oprPress("-") }
            )
        }
        Row(modifier = Modifier.weight(.2f)){
            CalcButton(modifier = Modifier.weight(1f),
                label = ".",
                onClick = {
                    if(num == "") num = "0."
                    else if (!num.contains('.')) num = num + "."
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "0",
                onClick = {numPress("0")}
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "=",
                isOperation = true,
                onClick = {
                    if (opr != "" && num != "") {
                        when (opr) {
                            "/" -> {
                                calc = Calculate(prev_num, num)
                                num = calc.divide()
                            }

                            "X" -> {
                                calc = Calculate(prev_num, num)
                                num = calc.multiply()
                            }

                            "-" -> {
                                calc = Calculate(prev_num, num)
                                num = calc.subtract()
                            }

                            "+" -> {
                                calc = Calculate(prev_num, num)
                                num = calc.add()
                            }
                        }
                        num = setDisplay(num.toFloat())
                    }
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onClick = { oprPress("+") }
            )
        }
        Row(modifier = Modifier.weight(.2f)){
            CalcButton(modifier = Modifier.weight(1f),
                label = "C",
                isEraser = true,
                onClick = {
                    num = ""
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CalcButton(modifier = Modifier.weight(1f),
                label = "CE",
                isEraser = true,
                onClick = {
                    prev_num = ""
                    num = ""
                    opr = ""
                    calc = Calculate("","")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}