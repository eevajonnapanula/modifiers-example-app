package com.eevajonna.modifiersexample.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eevajonna.modifiersexample.ui.theme.ModifiersExampleTheme

@Composable
fun Properties() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
    ) {
        Text("Role: Switch")
    }
}

@Preview(showSystemUi = true)
@Composable
fun PropertiesPreview() {
    ModifiersExampleTheme {
        Column(Modifier.padding(16.dp)) {
            Properties()
        }
    }
}
