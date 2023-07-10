package com.eevajonna.modifiersexample.ui.screens

interface Route {
    val route: String
    val title: String
}

object MainRoute : Route {
    override val route = "main"
    override val title = ""
}

object ClickableRoute : Route {
    override val route = "clickable"
    override val title = "Clickable"
}

object SelectableRoute : Route {
    override val route = "selectable"
    override val title = "Selectable"
}

object MagnifierRoute : Route {
    override val route = "magnifier"
    override val title = "Magnifier"
}

object ToggleableRoute : Route {
    override val route = "toggleable"
    override val title = "Toggleable"
}
