package com.example.registrogastosingresos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import com.example.registrogastosingresos.ui.theme.RegistroGastosIngresosTheme

// creamos una modelo de datos, para guardar lo que deseamos que se muestre,para nuestras transacciones

data class Transaccion(
    val titulo: String,
    val fecha: String,
    val monto: Double,
    val tipo: String
)

// Activity es una pantalla de la aplicación
// OnCreate: metodo que se ejcuta al abrir la pantalla

// Esta es la pantalla principal de la appp
class MainActivityGestor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { // este define que UI se va a mostrar en la pantalla
            ListaTransacciones()

                }
            }
        }

// un composable para indicar una función con interfaz de usuario UI
// los datos que se muestran
@Composable
fun ListaTransacciones() {

//creamos una lista con objetos

    val transacciones = listOf(
        Transaccion("compra desayuno", "28-08-2025", 15.00, "GASTO"),
        Transaccion("pago de deuda", "29-08-2025", 45.00, "INGRESO"),
        Transaccion("pago de luz", "29-08-2025", 30.00, "GASTO"),
    )
    // un contenedor base que ya trae una estructura estándar de pantalla
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MIS TRANSACCIONESs") })
        }
    ) { paddingValues ->
        // lista en vertical
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            // recorre la lista de transaciones
            // cada transacion llama al segundo composable, que lo dibuja con card

            items(transacciones) { transaccion ->
                TransaccionItem(transaccion)
            }
        }

    }
}

    // un composable para mostrar en car los datos
    @Composable
    fun TransaccionItem(transaccion: Transaccion) {
        Card(
            modifier = Modifier
                .fillMaxWidth() //ancho
                .padding(vertical = 4.dp), //espacio en vertical
        ) {

            //contenedor en horizontal
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = transaccion.titulo, style = MaterialTheme.typography.titleMedium)
                    Text(text = transaccion.fecha, style = MaterialTheme.typography.bodySmall)
                }
                val montoTexto = if (transaccion.tipo == "INGRESO") "+$${transaccion.monto}" else "-$${transaccion.monto}"
                Text(

                    text = montoTexto,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (transaccion.monto > 0) MaterialTheme.colorScheme.primary // color verde
                    else MaterialTheme.colorScheme.error //color rojo
                )
            }
        }
    }