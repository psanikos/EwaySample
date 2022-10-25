package com.simpletech.ewaysample.views

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.simpletech.ewaysample.services.Navigation
import com.simpletech.ewaysample.ui.theme.backColor
import com.simpletech.ewaysample.ui.theme.barColors
import com.simpletech.ewaysample.viewmodels.MainViewModel
import com.simpletech.ewaysample.views.subviews.FilterCard
import com.simpletech.ewaysample.views.subviews.OrderCard
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowDown
import compose.icons.fontawesomeicons.solid.CartArrowDown
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainView(model: MainViewModel) {
    val bottomBarTargets = listOf<Navigation>(
        Navigation.HomeScreen,
        Navigation.NewsScreen,
        Navigation.EWayScreen,
        Navigation.CommunicationScreen,
        Navigation.ProfileScreen
    )
    val controller = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val sheetState = ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var filterCategory by remember {
        mutableStateOf(FilterCategory.ORDER)
    }

    val currentTitle by remember(currentDestination) {
        derivedStateOf {
            bottomBarTargets.find { it.route == currentDestination?.route }?.name
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()){
           when(filterCategory){
               FilterCategory.ORDER -> OrderCard()
               FilterCategory.FILTER -> FilterCard()
           }
        }
    },
    sheetState = sheetState,
        sheetShape = RoundedCornerShape(13.dp),
        sheetBackgroundColor = Color(0xFFFFFBF8)
    ) {
        Scaffold(
            bottomBar = {
                    BottomAppBar(
                        containerColor = Color.Transparent,
                        modifier = Modifier
                            .height(88.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = barColors
                                ), shape = RoundedCornerShape(
                                    topStart = 20.dp, topEnd = 20.dp
                                )
                            )

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            bottomBarTargets.forEach { screen ->

                                val isSelected = currentDestination?.route == screen.route
                                val alpha = animateFloatAsState(
                                    targetValue = if (isSelected) 1f else 0f,
                                    animationSpec = tween(
                                        durationMillis = 700,
                                        easing = FastOutSlowInEasing
                                    )
                                )

                                IconButton(
                                    onClick = {
                                        controller.navigate(screen.route) {
                                            popUpTo(controller.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    modifier = Modifier.width(70.dp)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .width(48.dp)
                                                .height(46.4.dp)
                                                .background(
                                                    Brush.verticalGradient(
                                                        colors = listOf(
                                                            Color(0xFFFF8718).copy(
                                                                alpha = alpha.value
                                                            ), Color.Transparent
                                                        )
                                                    ),
                                                    shape = RoundedCornerShape(4.4.dp)
                                                ), contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                screen.icon, contentDescription = null,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                        Text(
                                            stringResource(screen.name),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 8.sp,
                                                color = Color(0xFF4A4A4A)
                                            ),
                                            modifier = Modifier.height(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
            },
            backgroundColor = backColor,
        ) { innerPadding ->
            NavHost(
                controller, startDestination = Navigation.HomeScreen.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Navigation.DetailsScreen.route) { DetailsView(model,controller) }
                composable(Navigation.EWayScreen.route) {
                    EWayView(controller, model = model) {
                        filterCategory = it
                    scope.launch {
                        sheetState.animateTo(ModalBottomSheetValue.Expanded)
                    }
                    }
                }
                composable(Navigation.CommunicationScreen.route) { CommunicationView() }
                composable(Navigation.ProfileScreen.route) { ProfileView() }
                composable(Navigation.NewsScreen.route) { NewsView() }
                composable(Navigation.HomeScreen.route) { HomeView() }
            }
        }
    }
}
