package com.simpletech.ewaysample.views

import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simpletech.ewaysample.helpers.roundOffDecimal
import com.simpletech.ewaysample.ui.theme.backColor
import com.simpletech.ewaysample.ui.theme.barColors
import com.simpletech.ewaysample.ui.theme.black_100
import com.simpletech.ewaysample.ui.theme.grey_100
import com.simpletech.ewaysample.ui.theme.grey_80
import com.simpletech.ewaysample.viewmodels.MainViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowDown
import compose.icons.fontawesomeicons.solid.ChevronLeft

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(model: MainViewModel, controller: NavController) {

    val transaction by model.currentTransaction.observeAsState(null)

    val data = remember {
        derivedStateOf {
            mutableStateOf(
                mapOf(
                    "Ημέρα/Ώρα" to (transaction?.transactionDate ?: "").replace("T", " - "),
                    "Πομποδέκτης" to (transaction?.transponderID ?: ""),
                    "Αυτοκινητόδρομος" to (transaction?.concession ?: ""),
                    "Σταθμός Διοδίων" to (transaction?.tollStation ?: ""),
                    "Λωρίδα" to (transaction?.lane ?: ""),
                    "Κατηγορία" to (transaction?.tollCategory ?: ""),
                    "Τιμή (Προ έκπτωσης)" to (transaction?.amountWithoutDiscount ?: 0.0).roundOffDecimal().toString() + "€",
                    "Έκπτωση" to (transaction?.discount ?: 0.0).toDouble().roundOffDecimal().toString() + "€",
                    "Τιμή (Με έκπτωση)" to (transaction?.amountAfterDiscount ?: 0.0).roundOffDecimal().toString() + "€",
                    "ΦΠΑ" to (transaction?.taxAmount ?: 0.0).roundOffDecimal().toString() + "€",
                    "Σύνολο" to (transaction?.totalAmount ?: 0.0).toString() + "€"
                )
            )
        }
    }

    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        Text(
                            "Λεπτομέριες διέλευσης",
                            fontSize = 17.5.sp,
                            fontWeight = FontWeight(700)
                        )
                        Text(
                            (transaction?.transactionDate ?: "").replace("T", " - "),
                            fontSize = 13.sp,
                            fontWeight = FontWeight(400),
                            color = grey_100
                        )
                    }
                },

                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            FontAwesomeIcons.Solid.ArrowDown, contentDescription = "",
                            modifier = Modifier.size(17.5.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        controller.popBackStack()
                    }) {
                        Icon(
                            FontAwesomeIcons.Solid.ChevronLeft,
                            contentDescription = "",
                            modifier = Modifier.size(18.dp),
                            tint = black_100
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
        backgroundColor = backColor
    ) { innerPadding ->
        when (transaction) {
            null -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(30.dp))
            }

            else ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .graphicsLayer {
                            shadowElevation = 8f
                            shape = RoundedCornerShape(13.dp)
                        }
                        .background(Color.White, shape = RoundedCornerShape(13.dp))
                        .verticalScroll(state = ScrollState(0)),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    data.value.value.toList().forEachIndexed { index, pair ->
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .height(56.dp)
                                .fillMaxWidth()
                                .background(
                                    color = if (index % 2 == 0) Color.Transparent else grey_80
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                pair.first,
                                fontWeight = FontWeight(700),
                                fontSize = 12.sp,
                                color = grey_100
                            )
                            Text(
                                pair.second,
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                color = black_100
                            )
                        }
                    }
                }
        }
    }
}
