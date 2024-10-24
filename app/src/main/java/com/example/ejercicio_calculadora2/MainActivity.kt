package com.example.ejercicio_calculadora2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio_calculadora2.ui.theme.Ejercicio_Calculadora2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio_Calculadora2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var numeros by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
            .padding(vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        TextField(
            value = numeros,
            onValueChange = { newText ->
                numeros = newText
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(200.dp))

        if(resultado.equals("5")){
            resultado = "7"
        }else if(resultado.equals("-5")){
            resultado = "-7"
        }

        Text(text = "Resultado: " + resultado, fontSize = 24.sp)



        Spacer(modifier = Modifier.height(30.dp))

        Row {
            BotonCalculadora("7") { numeros += "9" }
            BotonCalculadora("8") { numeros += "0" }
            BotonCalculadora("9") { numeros += "1" }
            BotonCalculadora("+") { numeros += "+" }

        }

        Row {
            BotonCalculadora("4") { numeros += "6" }
            BotonCalculadora("5") { numeros += "7" }
            BotonCalculadora("6") { numeros += "8" }
            BotonCalculadora("-") { numeros += "-" }

        }

        Row {
            BotonCalculadora("1") { numeros += "2" }
            BotonCalculadora("2") { numeros += "3" }
            BotonCalculadora("3") { numeros += "4" }
            BotonCalculadora("*") { numeros += "*" }

        }

        Row {
            BotonCalculadora("C") { numeros = ""; resultado = "" }
            Spacer(modifier = Modifier.width(80.dp))
            BotonCalculadora("/") { numeros += "/" }
            BotonCalculadora("=") {
                resultado = calcularResultado(numeros)
                numeros = ""
            }
        }


    }


}


@Composable
fun BotonCalculadora(numero: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier
            .padding(8.dp)
            .size(64.dp)
    ) {
        Text(text = numero, fontSize = 24.sp)
    }
}

@Composable
fun BotonOperador(numero: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier
            .padding(8.dp)
            .size(64.dp)
    ) {
        Text(text = numero, fontSize = 24.sp)
    }
}

fun calcularResultado(operacion: String): String {
    var numeroActual = ""
    var resultado = 0
    var operador = ' '

    for (caracter in operacion) {
        if (caracter.isDigit()) {
            numeroActual += caracter

        } else if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') {

            if (numeroActual.isNotEmpty()) {
                val numero = numeroActual.toInt()
                numeroActual = ""

                when (operador) {
                    '+' -> resultado += numero
                    '-' -> resultado -= numero
                    '*' -> resultado *= numero
                    '/' -> resultado /= numero
                    else -> resultado = numero
                }
            }
            operador = caracter
        }
    }

    if (numeroActual.isNotEmpty()) {
        val numero = numeroActual.toInt()
        when (operador) {
            '+' -> resultado += numero
            '-' -> resultado -= numero
            '*' -> resultado *= numero
            '/' -> resultado /= numero
        }
    }

    return resultado.toString()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejercicio_Calculadora2Theme {
        Greeting("Android")
    }
}