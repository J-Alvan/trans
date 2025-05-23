package com.example.swifttrans.ui.theme.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.swifttrans.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val cardModifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(horizontal = 16.dp, vertical = 8.dp)

    val items = listOf("Dashboard", "Routes", "Book", "Profile")
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bus),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.logo),
                                contentDescription = "Swift Trans Logo",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Swift Trans",
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            },
            bottomBar = {
                NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                when (item) {
                                    "Dashboard" -> Icon(Icons.Default.Home, contentDescription = item)
                                    "Routes" -> Icon(Icons.Default.Place, contentDescription = item)
                                    "Book" -> Icon(Icons.Default.Add, contentDescription = item)
                                    "Profile" -> Icon(Icons.Default.AccountCircle, contentDescription = item)
                                }
                            },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                when (item) {
                                    "Routes" -> navController.navigate("routes")
                                    "Book" -> navController.navigate("booking")
                                    "Profile" -> navController.navigate("profile")
                                }
                            }
                        )
                    }
                }
            },
            containerColor = Color.Transparent,
            contentColor = Color.White
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Welcome!",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.White),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Card(
                    modifier = cardModifier.clickable { navController.navigate("routes") },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                ) {
                    CenteredCardText("View Routes")
                }

                Card(
                    modifier = cardModifier.clickable { navController.navigate("booking") },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                ) {
                    CenteredCardText("Book Now")
                }

                Card(
                    modifier = cardModifier.clickable { navController.navigate("register") },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                ) {
                    CenteredCardText("My Trips")
                }

                Card(
                    modifier = cardModifier.clickable { navController.navigate("profile") },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                ) {
                    CenteredCardText("User Profile")
                }
            }
        }
    }
}

@Composable
fun CenteredCardText(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = NavController(LocalContext.current))
}