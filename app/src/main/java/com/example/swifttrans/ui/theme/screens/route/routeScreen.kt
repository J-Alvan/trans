package com.example.swifttrans.ui.theme.screens.route


import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swifttrans.R
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import android.net.Uri



@Composable
fun RouteScreen() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.padding()
        )}
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar()
        SearchBar()
        RoutesList()
        Spacer(modifier = Modifier.weight(1f))
        BottomBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Swift Trans", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        },
        actions = {
            TextButton(onClick = { }) {
                Text("Book", color = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1565C0), titleContentColor = Color.White)
    )
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text("Search routes") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun RoutesList() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        repeat(5) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {  },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Route ${it + 1}", fontWeight = FontWeight.Bold)
                    Text("From Point A to Point B")
                }
            }
        }
    }
}

@Composable
fun BottomBar() {
    val context = LocalContext.current
    NavigationBar(containerColor = Color(0xFF1565C0)) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
            label = { Text("Email") },
            selected = false,
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:ochiengalvan61@gmail.com")
                }
                context.startActivity(intent)
            }
    )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Phone, contentDescription = "Phone") },
            label = { Text("Phone") },
            selected = false,
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:0711343673")
                }
                context.startActivity(intent)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RouteScreenPreview(){
    RouteScreen()
}
