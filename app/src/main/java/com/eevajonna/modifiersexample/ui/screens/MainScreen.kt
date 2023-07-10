package com.eevajonna.modifiersexample.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eevajonna.modifiersexample.ui.theme.ModifiersExampleTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modifiers Example App") },
                navigationIcon = {
                    if (navBackStackEntry?.destination?.route != MainRoute.route) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, "Navigate up")
                        }
                    }
                },
                modifier = Modifier.padding(end = 8.dp),
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                NavHost(navController = navController, startDestination = MainRoute.route) {
                    composable(MainRoute.route) {
                        val links = listOf(ClickableRoute, SelectableRoute, ToggleableRoute, MagnifierRoute)
                        LinkList(
                            links = links,
                            navigate = { route ->
                                navController.navigate(route)
                            },
                        )
                    }
                    composable(ClickableRoute.route) {
                        ClickableScreen()
                    }
                    composable(SelectableRoute.route) {
                        SelectableScreen()
                    }
                    composable(MagnifierRoute.route) {
                        MagnifierScreen()
                    }
                    composable(ToggleableRoute.route) {
                        ToggleableScreen()
                    }
                }
            }
        },
    )
}

@Composable
fun LinkList(links: List<Route>, navigate: (String) -> Unit) {
    LazyColumn {
        items(links) {
            LinkRow(text = it.title, route = it.route, navigate = navigate)
            Divider()
        }
    }
}

@Composable
fun LinkRow(text: String, route: String, navigate: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                role = Role.Button,
                onClick = { navigate(route) },
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text, style = MaterialTheme.typography.labelLarge)
        Icon(Icons.Filled.ArrowRight, contentDescription = "")
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    ModifiersExampleTheme {
        MainScreen()
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun MainScreenPreviewDark() {
    ModifiersExampleTheme {
        MainScreen()
    }
}
