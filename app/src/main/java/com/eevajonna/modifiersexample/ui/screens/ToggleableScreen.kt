package com.eevajonna.modifiersexample.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eevajonna.modifiersexample.ui.common.BodyText
import com.eevajonna.modifiersexample.ui.common.Title
import com.eevajonna.modifiersexample.ui.theme.ModifiersExampleTheme

@Composable
fun ToggleableScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Title("Without Toggleable-modifier:")
        ToggleableExample(false)
        BodyText(text = "Selected option can be switched only by pressing the toggle.")
        Title("With Toggleable-modifier:")
        ToggleableExample(true)
        BodyText(text = "Now pressing the whole row toggles the selection.")
    }
}

@Composable
fun ToggleableExample(isToggleable: Boolean) {
    var selected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        SwitchRow(text = "Toggleable", checked = selected, onChange = { selected = it }, isToggleable)
    }
}

@Composable
fun SwitchRow(text: String, checked: Boolean, onChange: (Boolean) -> Unit, isToggleable: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isToggleable) {
                    Modifier.toggleable(
                        value = checked,
                        role = Role.Switch,
                        onValueChange = { onChange(it) },
                    )
                } else {
                    Modifier
                },
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onPrimaryContainer)
        Switch(checked = checked, onCheckedChange = { onChange(it) }, modifier = Modifier.clearAndSetSemantics {})
    }
}

@Preview(showSystemUi = true)
@Composable
fun ToggleableScreenPreview() {
    ModifiersExampleTheme {
        ToggleableScreen()
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ToggleableScreenPreviewDark() {
    ModifiersExampleTheme {
        ToggleableScreen()
    }
}
