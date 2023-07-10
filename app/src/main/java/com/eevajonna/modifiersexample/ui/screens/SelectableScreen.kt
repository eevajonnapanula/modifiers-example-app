package com.eevajonna.modifiersexample.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
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
fun SelectableScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Title("Without Selectable-modifier:")
        SelectableExample(false)
        BodyText(text = "Selected option can be switched only by pressing the radio input.")
        Title("With Selectable-modifier:")
        SelectableExample(true)
        BodyText(text = "Now pressing the whole row switches the selection.")
    }
}

@Composable
fun SelectableExample(isSelectable: Boolean) {
    val inputs = listOf("Option A", "Option B")
    var selected by remember { mutableStateOf(inputs[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .selectableGroup(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        inputs.map { input ->
            RadioInputRow(text = input, checked = selected == input, onChange = { selected = it }, isSelectable)
            if (inputs.last() != input) {
                Divider()
            }
        }
    }
}

@Composable
fun RadioInputRow(text: String, checked: Boolean, onChange: (String) -> Unit, isSelectable: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isSelectable) {
                    Modifier.selectable(
                        selected = checked,
                        enabled = true,
                        role = Role.RadioButton,
                        onClick = { onChange(text) },
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
        RadioButton(
            modifier = Modifier.then(if (isSelectable) Modifier.clearAndSetSemantics { } else Modifier),
            selected = checked,
            onClick = { onChange(text) },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.tertiary,
                unselectedColor = MaterialTheme.colorScheme.tertiary,
            ),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SelectableScreenPreview() {
    ModifiersExampleTheme {
        SelectableScreen()
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SelectableScreenPreviewDark() {
    ModifiersExampleTheme {
        SelectableScreen()
    }
}
