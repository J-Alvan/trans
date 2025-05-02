package com.example.swifttrans.ui.theme.screens.ticket

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.swifttrans.models.TicketInfo
import com.google.gson.Gson
import kotlin.jvm.java

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen(ticketJson: String) {
    val ticket = remember { Gson().fromJson(ticketJson, TicketInfo::class.java) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Ticket") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { padding ->
        Card(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Passenger: ${ticket.name}", fontWeight = FontWeight.Bold)
                Text("Route: ${ticket.route}")
                Text("Date: ${ticket.date}")
                Text("Phone: ${ticket.phone}")
                Divider()
                Text("Amount Paid: ${ticket.amountPaid}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("Payment Method: ${ticket.paymentMethod}")
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { /* Maybe share or save */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Done")
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TicketScreenPreview() {
    TicketScreen("YourTicketJsonStringHere")
}