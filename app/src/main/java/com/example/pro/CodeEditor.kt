package com.example.pro

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CodeEditor() {
    var code by remember { mutableStateOf("<h1>Hello!</h1>") }

    Text("Code Editor", fontSize = 22.sp)

    OutlinedTextField(
        value = code,
        onValueChange = { code = it },
        modifier = Modifier.fillMaxWidth().height(180.dp)
    )

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
        },
        update = {
            it.loadDataWithBaseURL(null, code, "text/html", "UTF-8", null)
        },
        modifier = Modifier.fillMaxWidth().height(200.dp)
    )
}
