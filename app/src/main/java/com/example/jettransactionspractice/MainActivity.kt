package com.example.jettransactionspractice

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.jettransactionspractice.ui.theme.JetTransactionsPracticeTheme
import java.time.temporal.TemporalAmount

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTransactionsPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TotalCard(header: String,amount: Float,color: Color){
    Card(modifier = Modifier.fillMaxWidth().height(100.dp), shape = RoundedCornerShape(25.dp)) {
        Column(modifier = Modifier.background(color).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center) {
            Text(header)
            Text("R${amount}")
        }
    }


}

fun balanceCalculation(){

    val totalIncome = 28020.00F
    var expense = 0.0F
    var balance  = totalIncome - expense


}

@Composable
fun TransactionCard(transaction : Transaction){
    Column() {
        Text("Expense")
        Row() {
            Text(transaction.type)
            Text("R${transaction.amount}")
        }
    }

}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector?,
) {
    AlertDialog(
       // icon = { Icon(icon, contentDescription = "Example Icon") },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Dismiss")
            }
        }
    )
}


@Composable
fun addTransaction( openAlertDialog: MutableState<Boolean>) {

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    println("Confirmation registered")
                },
                dialogTitle = "Alert dialog example",
                dialogText = "This is an example of an alert dialog with buttons.",
//                icon = Icons.Default.Info
                icon = null
            )
        }
    }
}



@Composable
fun MainScreen(name: String, modifier: Modifier = Modifier) {

    var transactions = listOf<Int>()

    var openAlertDialog = remember { mutableStateOf(false) }

    Column(modifier.fillMaxSize().padding(10.dp)){

        Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            TotalCard("Total Balance",28020.00F, Color.Blue)
            TotalCard("Total Income",28020.00F, Color.Green)
            TotalCard("Total Expense",28020.00F, Color.Red)

        }

        Spacer(modifier.height(20.dp))
        Text("Recent Transactions")
        Spacer(modifier.height(20.dp))
        Text("Today")
        LazyColumn() {
            items(transactions) { item->

            }
        }

        Button(onClick = {openAlertDialog.value = true}, colors = ButtonDefaults.buttonColors(Color.Blue), modifier = Modifier.fillMaxWidth()) {
                Text("Add Transaction")
        }

        addTransaction(openAlertDialog)

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    JetTransactionsPracticeTheme {
        MainScreen("Android")
    }
}