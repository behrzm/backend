package com.example.pro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun PhoneAuthScreen(
    onSendCode: (String) -> Unit,
    onVerifyCode: (String) -> Unit,
    onResendCode: (String) -> Unit
) {
    var phone by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var step by remember { mutableStateOf(1) }

    // ⏱ таймер
    var timeLeft by remember { mutableStateOf(60) }
    var canResend by remember { mutableStateOf(false) }

    // ⏱ запуск таймера
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = if (step == 1) "Phone verification" else "Enter SMS code",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (step == 1) {
            // ВВОД НОМЕРА
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone number (+7...)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onSendCode(phone)
                    step = 2
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send code")
            }

        } else {
            // ВВОД SMS
            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                label = { Text("SMS code") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onVerifyCode(code) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verify")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!canResend) {
                Text(
                    text = "Resend code in $timeLeft s",
                    color = Color.Gray
                )
            } else {
                Text(
                    text = "Resend SMS",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onResendCode(phone)
                        timeLeft = 60
                        canResend = false
                    }
                )
            }
        }
    }
}
