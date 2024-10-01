package com.example.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment

class Calculate{
    var num1: String = ""
    var num2: String = ""
    constructor(num1: String, num2: String){
        this.num1 = num1
        this.num2 = num2
    }

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
fun CalculatorScreen(modifier: Modifier = Modifier){

    var prev_num by remember { mutableStateOf("")}
    var num by remember {mutableStateOf("")}
    var opr by remember {mutableStateOf("")}
    var calc: Calculate

    Column(modifier = modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(text = num)
        Row{
            Button(onClick={ num = num + "7"}){
                Text(text = "7")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={num = num + "8"}){
                Text(text = "8")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={num = num + "9"}){
                Text(text = "9")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={
                //calc = Calculate(num,"")
                prev_num = num
                num = ""
                opr = "/"
            }){
                Text(text = "/")
            }
        }
        Row{
            Button(onClick={num = num + "4"}){
                Text(text = "4")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={num = num + "5"}){
                Text(text = "5")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={num = num + "6"}){
                Text(text = "6")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={
                //calc = Calculate(num,"")
                prev_num = num
                num = ""
                opr = "X"
            }){
                Text(text = "X")
            }
        }
        Row{
            Button(onClick={num = num + "1"}){
                Text(text = "1")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={num = num + "2"}){
                Text(text = "2")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={num = num + "3"}){
                Text(text = "3")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={
                //calc = Calculate(num,"")
                prev_num = num
                num = ""
                opr = "-"
            }){
                Text(text = "-")
            }
        }
        Row{
            Button(onClick={num = num + "."}){
                Text(text = ".")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={num = num + "0"}){
                Text(text = "0")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={
                when (opr){
                    "/" -> {
                        calc = Calculate(prev_num,num)
                        num = calc.divide()
                    }
                    "*" ->{
                        calc = Calculate(prev_num,num)
                        num = calc.multiply()
                    }
                    "-" ->{
                        calc = Calculate(prev_num,num)
                        num = calc.subtract()
                    }
                    "+" ->{
                        calc = Calculate(prev_num,num)
                        num = calc.add()
                    }
                }
                /*when (opr){
                    "/" -> num = (prev_num.toFloat() / num.toFloat()).toString()
                    "*" -> num = (prev_num.toFloat() * num.toFloat()).toString()
                    "-" -> num = (prev_num.toFloat() - num.toFloat()).toString()
                    "+" -> num = (prev_num.toFloat() + num.toFloat()).toString()
                }*/
            }){
                Text(text = "=")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick={
                //calc = Calculate(num,"")
                prev_num = num
                num = ""
                opr = "+"
            }){
                Text(text = "+")
            }
        }
        Row{
            Button(onClick = {
                num = ""
            }) { Text(text = "C") }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = {
                num = ""
                calc = Calculate("","")
                //prev_num = ""
            }) { Text(text = "CE") }
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