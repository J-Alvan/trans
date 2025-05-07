package com.example.swifttrans.ui.theme.screens.register

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.swifttrans.R
import kotlinx.coroutines.launch
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.swifttrans.data.AuthViewModel
import com.example.swifttrans.navigation.ROUTE_LOGIN


@Composable
fun RegisterScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Validation function
    fun validateInputs(): Boolean {
        var isValid = true

        nameError = if (name.isBlank()) {
            isValid = false
            "Name is required"
        } else null

        emailError = if (email.isBlank()) {
            isValid = false
            "Email is required"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false
            "Invalid email format"
        } else null

        passwordError = if (password.isBlank()) {
            isValid = false
            "Password is required"
        } else if (password.length < 6) {
            isValid = false
            "Password must be at least 6 characters"
        } else null

        confirmPasswordError = if (confirmPassword.isBlank()) {
            isValid = false
            "Confirm password is required"
        } else if (confirmPassword != password) {
            isValid = false
            "Passwords do not match"
        } else null

        return isValid
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Background Image with 50% opacity
            Image(
                painter = rememberAsyncImagePainter(R.drawable.bus),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                contentScale = ContentScale.Crop
            )

            // Semi-transparent black overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            // Registration Form
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo and Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Swift Trans Logo",
                        modifier = Modifier
                            .size(48.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Swift Trans",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        fontSize = 36.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Card for input fields
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Name Field
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it; nameError = null },
                            label = { Text("Name") },
                            leadingIcon = {
                                Icon(Icons.Default.Person, contentDescription = null)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            isError = nameError != null,
                            supportingText = {
                                nameError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color.Gray,
                                errorBorderColor = MaterialTheme.colorScheme.error
                            )
                        )

                        // Email Field
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it; emailError = null },
                            label = { Text("Email", color = Color.Black) },
                            leadingIcon = {
                                Icon(Icons.Default.Email, contentDescription = null)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth(),
                            isError = emailError != null,
                            supportingText = {
                                emailError?.let {
                                    Text(
                                        it,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color.Gray,
                                errorBorderColor = MaterialTheme.colorScheme.error
                            )
                        )

                        // Password Field
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it; passwordError = null },
                            label = { Text("Password", color = Color.Black) },
                            leadingIcon = {
                                Icon(Icons.Default.Lock, contentDescription = null)
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth(),
                            isError = passwordError != null,
                            supportingText = {
                                passwordError?.let {
                                    Text(
                                        it,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color.Gray,
                                errorBorderColor = MaterialTheme.colorScheme.error
                            )
                        )

                        // Confirm Password Field
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it; confirmPasswordError = null },
                            label = { Text("Confirm Password", color = Color.Black) },
                            leadingIcon = {
                                Icon(Icons.Default.Lock, contentDescription = null)
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth(),
                            isError = confirmPasswordError != null,
                            supportingText = {
                                confirmPasswordError?.let {
                                    Text(
                                        it,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color.Gray,
                                errorBorderColor = MaterialTheme.colorScheme.error
                            )
                        )

                        // Register Button
                        Button(
                            onClick = {
                                authViewModel.signup(name, email, password,navController, context)

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Register", color = Color.White, fontSize = 18.sp)
                        }
                        Text(
                            text = buildAnnotatedString { append("If already registered, Login Here") },
                            modifier = Modifier.wrapContentWidth().align
                                (Alignment.CenterHorizontally).clickable {
                                navController.navigate(ROUTE_LOGIN)
                            })
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = NavController(LocalContext.current))
}