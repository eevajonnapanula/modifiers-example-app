package com.eevajonna.modifiersexample.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eevajonna.modifiersexample.ui.common.BodyText
import com.eevajonna.modifiersexample.ui.common.Title
import com.eevajonna.modifiersexample.ui.theme.ModifiersExampleTheme

@Composable
fun ClickableScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Title("Without Clickable-modifier:")
        Card(isClickable = false)
        BodyText("Clickable area is only the icon.")
        Title("With Clickable-modifier:")
        Card(isClickable = true)
        BodyText("The whole row is clickable.")
    }
}

@Composable
fun Card(isClickable: Boolean) {
    var favorited by remember {
        mutableStateOf(false)
    }

    var icon by remember {
        mutableStateOf(Icons.Default.BookmarkBorder)
    }

    fun onClick() { when (favorited) {
        true -> {
            favorited = false
            icon = Icons.Default.BookmarkBorder
        }
        false -> {
            favorited = true
            icon = Icons.Filled.Bookmark
        }
    } }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .then(
                if (isClickable) {
                    Modifier.clickable(
                        role = Role.Button,
                    ) {
                        onClick()
                    }
                } else {
                    Modifier
                },
            )
            .padding(ClickableScreen.padding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Bookmark this item", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
        if (isClickable) {
            FavouriteIcon(icon = icon, modifier = Modifier.padding(12.dp))
        } else {
            IconButton(onClick = { onClick() }) {
                FavouriteIcon(icon = icon, contentDescription = "Bookmark this item")
            }
        }
    }
}

@Composable
fun FavouriteIcon(modifier: Modifier = Modifier, icon: ImageVector, contentDescription: String? = "") {
    Icon(
        icon,
        contentDescription = contentDescription,
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    )
}

@Preview(showSystemUi = true)
@Composable
fun ClickableScreenPreview() {
    ModifiersExampleTheme {
        ClickableScreen()
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ClickableScreenPreviewDark() {
    ModifiersExampleTheme {
        ClickableScreen()
    }
}

object ClickableScreen {
    val padding = 16.dp
}
