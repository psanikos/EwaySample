package com.simpletech.ewaysample.views

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
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
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.simpletech.ewaysample.services.Navigation
import com.simpletech.ewaysample.ui.theme.EwaySampleTheme
import com.simpletech.ewaysample.ui.theme.backColor
import com.simpletech.ewaysample.ui.theme.barColors
import com.simpletech.ewaysample.viewmodels.MainViewModel
import com.simpletech.ewaysample.views.subviews.InformCard
import com.simpletech.ewaysample.views.subviews.SortingCategories
import com.simpletech.ewaysample.views.subviews.TollInfoCard
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowDown
import compose.icons.fontawesomeicons.solid.Filter
import compose.icons.fontawesomeicons.solid.SortDown
import compose.icons.fontawesomeicons.solid.SortNumericDown
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class FilterCategory{
    ORDER,FILTER
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun EWayView(controller: NavController,model:MainViewModel,onShowFilter:(FilterCategory)->Unit){

    val transactions by model.filteredData.observeAsState(listOf())
    val sortingFilter by model.orderFilter.observeAsState(SortingCategories.RECENT)

    Scaffold(
            topBar = {

                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Διελεύσεις",
                            fontSize = 17.5.sp,
                            fontWeight = FontWeight(700)
                        )
                    },

                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                FontAwesomeIcons.Solid.ArrowDown, contentDescription = "",
                                modifier = Modifier.size(17.5.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .graphicsLayer {
                            this.shadowElevation = 4f
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)

                        }
                        .background(
                            brush = Brush.verticalGradient(
                                colors = barColors
                            ),
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                )

            },
            backgroundColor = backColor,
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 14.dp)
                    .fillMaxSize()
                    .verticalScroll(
                        state = ScrollState(0)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                InformCard()


                Row(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            onShowFilter(FilterCategory.FILTER)

                        },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.background(
                            Color.White,
                            shape = RoundedCornerShape(61.dp)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                FontAwesomeIcons.Solid.Filter, contentDescription = "",
                                modifier = Modifier.size(20.dp),
                                tint = Color(0xFFFF8400)
                            )
                            Text(
                                "Φίλτρα",
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp, color = Color(0xFFFF8501)

                            )
                        }
                    }
                    TextButton(
                        onClick = {
                            onShowFilter(FilterCategory.ORDER)

                        },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.background(
                            Color.White,
                            shape = RoundedCornerShape(61.dp)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                sortingFilter.value,
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                color = Color(0xFFFF8501)
                            )
                            Icon(
                                FontAwesomeIcons.Solid.SortNumericDown, contentDescription = "",
                                modifier = Modifier.size(20.dp),
                                tint = Color(0xFFFF8400)
                            )
                        }
                    }
                }
              when(transactions.size){
                  0-> Box(modifier = Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center){
                      Text(
                          "Δεν βρέθηκαν αποτελέσματα",
                          fontWeight = FontWeight(400),
                          fontSize = 14.sp, color = Color.Black

                      )
                  }
                  else->   transactions.forEach {
                      Box(modifier = Modifier
                          .wrapContentSize()
                          .clickable {
                              model.selectTransaction(it.id, it.transactionDate)
                              controller.navigate(Navigation.DetailsScreen.route)
                          }){
                          TollInfoCard(transaction = it)
                      }
                  }
              }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(107.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFF3830E)
                    ),
                    border = BorderStroke(3.dp, Color(0xFFFFB86D)),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(
                        "Load more",
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                        color = Color(0xFFF3830E),
                        modifier = Modifier.padding(horizontal = 40.dp, vertical = 15.dp)
                    )

                }
            }
        }


}

@Preview
@Composable
fun EWayPreview(){
    EwaySampleTheme {
        EWayView(controller = rememberNavController(), model = viewModel()){

        }
    }

}