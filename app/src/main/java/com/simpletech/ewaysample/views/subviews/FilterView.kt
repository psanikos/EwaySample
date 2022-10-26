package com.simpletech.ewaysample.views.subviews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simpletech.ewaysample.helpers.SortingCategories
import com.simpletech.ewaysample.ui.theme.black_100
import com.simpletech.ewaysample.ui.theme.orange_40
import com.simpletech.ewaysample.ui.theme.orange_80
import com.simpletech.ewaysample.ui.theme.orange_90
import com.simpletech.ewaysample.viewmodels.MainViewModel

@Composable
fun OrderCard(model: MainViewModel, onApplyFilter: () -> Unit) {

    var selectedCategory by remember {
        mutableStateOf(SortingCategories.RECENT)
    }
    val modelFilter = model.orderFilter.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        modelFilter.value?.let {
            selectedCategory = it
        }
    })

    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 12.dp, bottom = 36.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(86.dp)
                    .height(6.dp)
                    .background(
                        color = Color(0xFFCCCCCC),
                        shape = RoundedCornerShape(30.dp)
                    )
            )
        }
        Text(
            text = "Ταξινόμιση",
            fontWeight = FontWeight(700),
            fontSize = 32.sp,
            color = black_100
        )

        Column(
            modifier = Modifier.padding(top = 24.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            SortingCategories.values().forEach {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(13.dp),
                    color = Color(0xFFFFFBF8),
                    shadowElevation = 4.dp
                ) {
                    Row(
                        Modifier
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedCategory == it,
                            onClick = {
                                selectedCategory = it
                            },
                            colors = androidx.compose.material.RadioButtonDefaults.colors(
                                selectedColor = orange_90,
                                unselectedColor = orange_40,

                            ),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            it.value,
                            fontWeight = FontWeight(if (selectedCategory == it) 700 else 400),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
        OutlinedButton(
            onClick = {
                model.sortBy(selectedCategory)
                onApplyFilter()
            },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(107.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = orange_90,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 3.dp, orange_80)
        ) {
            Text(
                "Ok",
                fontWeight = FontWeight(700),
                fontSize = 16.sp
            )
        }
    }
}
