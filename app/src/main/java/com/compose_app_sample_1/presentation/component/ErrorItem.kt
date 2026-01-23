package com.compose_app_sample_1.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun ErrorItem(message: String) {
    Text(
        text = message,
        modifier = Modifier
            .padding(16.dp)
            .testTag("error"),
        color = Color.Red
    )
}