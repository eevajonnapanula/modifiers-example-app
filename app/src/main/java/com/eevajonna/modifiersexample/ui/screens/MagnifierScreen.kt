package com.eevajonna.modifiersexample.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MagnifierStyle
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.eevajonna.modifiersexample.ui.common.Title

@Composable
fun MagnifierScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Title("With Magnifier-modifier:")
        Magnifier()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Magnifier() {
    var magnifierCenter by remember { mutableStateOf(Offset.Unspecified) }

    if (!MagnifierStyle.Default.isSupported) {
        Text("Magnifier is not supported on this platform.")
    } else {
        Box(
            Modifier
                .magnifier(
                    sourceCenter = { magnifierCenter },
                    zoom = 3f,
                    style = MagnifierStyle(size = DpSize(height = 200.dp, width = 300.dp)),
                )
                .pointerInput(Unit) {
                    detectDragGestures(
                        // Show the magnifier in the initial position
                        onDragStart = { magnifierCenter = it },
                        // Magnifier follows the pointer during a drag event
                        onDrag = { _, delta -> magnifierCenter = magnifierCenter.plus(delta) },
                        // Hide the magnifier when a user ends drag movement.
                        onDragEnd = { magnifierCenter = Offset.Unspecified },
                        onDragCancel = { magnifierCenter = Offset.Unspecified },
                    )
                },
        ) {
            Text("Try magnifying this text by dragging a pointer (finger, mouse, other) over the text.")
        }
    }
}
