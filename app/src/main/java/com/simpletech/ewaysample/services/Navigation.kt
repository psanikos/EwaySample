package com.simpletech.ewaysample.services

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorComposable
import com.simpletech.ewaysample.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.DotCircle
import compose.icons.fontawesomeicons.solid.EllipsisH
import compose.icons.fontawesomeicons.solid.GripHorizontal
import compose.icons.fontawesomeicons.solid.Headphones
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.Newspaper
import compose.icons.fontawesomeicons.solid.Road

sealed class Navigation(val route:String,@StringRes val name:Int,val icon:ImageVector){
    object DetailsScreen:Navigation(route = "details",R.string.detailsSc,FontAwesomeIcons.Solid.GripHorizontal)
    object EWayScreen:Navigation(route = "eway",R.string.eway,FontAwesomeIcons.Solid.Road)
    object HomeScreen:Navigation(route = "home",R.string.homeSc,FontAwesomeIcons.Solid.Home)
    object NewsScreen:Navigation(route = "news",R.string.newsSc,FontAwesomeIcons.Solid.Newspaper)
    object ProfileScreen:Navigation(route = "profile",R.string.profileSc,FontAwesomeIcons.Solid.EllipsisH)
    object CommunicationScreen:Navigation(route = "communication",R.string.communicationSc,FontAwesomeIcons.Solid.Headphones)
}