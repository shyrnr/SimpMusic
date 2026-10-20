package com.maxrave.simpmusic.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maxrave.simpmusic.expect.ui.PlatformBackdrop
import com.maxrave.simpmusic.viewModel.SharedViewModel
import org.jetbrains.compose.resources.stringResource
import kotlin.reflect.KClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun LiquidGlassAppBottomNavigationBar(
    startDestination: Any,
    navController: NavController,
    backdrop: PlatformBackdrop,
    viewModel: SharedViewModel,
    isScrolledToTop: Boolean,
    showAnalyticsTab: Boolean,
    showMixForYouTab: Boolean,
    onOpenNowPlaying: () -> Unit,
    liquidGlass: Boolean,
    reloadDestinationIfNeeded: (KClass<*>) -> Unit,
) {
    val entry by navController.currentBackStackEntryAsState()
    val screens = listOfNotNull(
        BottomNavScreen.Home,
        BottomNavScreen.MixForYou.takeIf { showMixForYouTab },
        BottomNavScreen.Analytics.takeIf { showAnalyticsTab },
        BottomNavScreen.Library,
        BottomNavScreen.Search,
    )

    NavigationBar {
        screens.forEach { screen ->
            val selected = entry?.destination?.hierarchy?.any {
                it.hasRoute(screen.destination::class)
            } ?: (startDestination::class == screen.destination::class)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (selected) {
                        reloadDestinationIfNeeded(screen.destination::class)
                    } else {
                        navController.navigate(screen.destination) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { screen.icon() },
                label = { Text(stringResource(screen.title)) },
            )
        }
    }
}
