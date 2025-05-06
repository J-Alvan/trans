package com.example.swifttrans.ui.theme.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter


//import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.bus),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(
                text ="Swift Trans Login",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
//                    isLoading = true
//                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener { task ->
//                            isLoading = false
//                            if (task.isSuccessful) {
//                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
//                                navController.navigate("home") // Navigate to home screen
//                            } else {
//                                Toast.makeText(context, "Login Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
//                            }
//                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Login")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = {
                navController.navigate("register")
            }) {
                Text("Don’t have an account? Register")
            }
        }
    }
}}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen(navController = NavController(LocalContext.current))
//}