package com.example.pro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun PhoneAuthScreen(
    onSendCode: (String) -> Unit,
    onVerifyCode: (String) -> Unit,
    onResendCode: (String) -> Unit
) {
    var phone by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }

    val step by PhoneAuthUiState.step

    var timeLeft by remember { mutableStateOf(60) }
    var canResend by remember { mutableStateOf(false) }

    LaunchedEffect(step) {
        if (step == 2) {
            timeLeft = 60
            canResend = false
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }
            canResend = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF141E30), Color(0xFF243B55))
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = if (step == 1) "Phone Sign In 📱" else "Verify Code 🔐",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp)
            ) {
                Column(Modifier.padding(20.dp)) {

                    if (step == 1) {
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = { Text("Phone number (+7...)") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { onSendCode(phone) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Send code")
                        }

                    } else {
                        OutlinedTextField(
                            value = code,
                            onValueChange = { code = it },
                            label = { Text("SMS code") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { onVerifyCode(code) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Verify")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        if (!canResend) {
                            Text("Resend in $timeLeft s", color = Color.Gray)
                        } else {
                            Text(
                                "Resend SMS",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable {
                                    onResendCode(phone)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
