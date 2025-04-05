
package com.kinn.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kinn.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .height(56.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Calculator",
                                    color = Color(0xFF2196F3),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                ) { padding ->
                    CalculatorScreen(Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("0") }
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var isNewNumber by remember { mutableStateOf(true) }

    fun calculate(): String {
        val num1 = firstNumber.toDoubleOrNull() ?: return "Error"
        val num2 = secondNumber.toDoubleOrNull() ?: return "Error"

        return when (operation) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "×" -> (num1 * num2).toString()
            "÷" -> if (num2 == 0.0) "Error" else (num1 / num2).toString()
            else -> "Error"
        }.let { result ->
            if (result.endsWith(".0")) result.dropLast(2) else result
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = displayText,
            fontSize = 48.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.End
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val buttonModifier = Modifier
                .weight(1f)
                .padding(4.dp)

            // Row 1
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    if (isNewNumber) displayText = "7" else displayText += "7"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("7") }
                Button(onClick = {
                    if (isNewNumber) displayText = "8" else displayText += "8"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("8") }
                Button(onClick = {
                    if (isNewNumber) displayText = "9" else displayText += "9"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("9") }
                Button(onClick = {
                    if (displayText != "0" && operation.isEmpty()) {
                        firstNumber = displayText
                        operation = "×"
                        displayText += " ×"
                        isNewNumber = true
                    }
                }, modifier = buttonModifier) { Text("×") }
            }

            // Row 2
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    if (isNewNumber) displayText = "4" else displayText += "4"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("4") }
                Button(onClick = {
                    if (isNewNumber) displayText = "5" else displayText += "5"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("5") }
                Button(onClick = {
                    if (isNewNumber) displayText = "6" else displayText += "6"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("6") }
                Button(onClick = {
                    if (displayText != "0" && operation.isEmpty()) {
                        firstNumber = displayText
                        operation = "-"
                        displayText += " -"
                        isNewNumber = true
                    }
                }, modifier = buttonModifier) { Text("-") }
            }

            // Row 3
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    if (isNewNumber) displayText = "1" else displayText += "1"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("1") }
                Button(onClick = {
                    if (isNewNumber) displayText = "2" else displayText += "2"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("2") }
                Button(onClick = {
                    if (isNewNumber) displayText = "3" else displayText += "3"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("3") }
                Button(onClick = {
                    if (displayText != "0" && operation.isEmpty()) {
                        firstNumber = displayText
                        operation = "+"
                        displayText += " +"
                        isNewNumber = true
                    }
                }, modifier = buttonModifier) { Text("+") }
            }

            // Row 4
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    if (isNewNumber) displayText = "0" else displayText += "0"
                    isNewNumber = false
                }, modifier = buttonModifier) { Text("0") }
                Button(onClick = {
                    if (!displayText.contains(".") && !isNewNumber) {
                        displayText += "."
                    }
                    isNewNumber = false
                }, modifier = buttonModifier) { Text(".") }
                Button(onClick = {
                    if (displayText.length > 1) {
                        displayText = displayText.dropLast(1)
                    } else {
                        displayText = "0"
                        isNewNumber = true
                    }
                }, modifier = buttonModifier) { Text("⌫") }
                Button(onClick = {
                    if (displayText != "0" && operation.isEmpty()) {
                        firstNumber = displayText
                        operation = "÷"
                        displayText += " ÷"
                        isNewNumber = true
                    }
                }, modifier = buttonModifier) { Text("÷") }
            }

            // Row 5
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = buttonModifier)
                Spacer(modifier = buttonModifier)
                Spacer(modifier = buttonModifier)
                Button(onClick = {
                    if (operation.isNotEmpty()) {
                        secondNumber = displayText.split(" ").last()
                        displayText = calculate()
                        operation = ""
                        firstNumber = displayText
                        isNewNumber = true
                    }
                }, modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) { Text("=", fontSize = 20.sp) }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}