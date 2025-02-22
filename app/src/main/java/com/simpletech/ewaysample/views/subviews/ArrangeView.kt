package com.simpletech.ewaysample.views.subviews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simpletech.ewaysample.viewmodels.MainViewModel

@Composable
fun FilterCard(model: MainViewModel, onApplyFilter: () -> Unit) {

    val data by model.data.observeAsState()

    val radioButtons by remember {
        derivedStateOf {
            mutableStateOf(
                data?.map {

                    it.concession
                }?.distinct()
            )
        }
    }
    val checkBoxes by remember {
        derivedStateOf {
            mutableStateOf(
                data?.map {
                    it.tollStation
                }?.distinct()
            )
        }
    }
    val selectedCheckBoxes by model.tollStations.observeAsState(listOf())

    val selectedRadio by model.concession.observeAsState()

    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 12.dp, bottom = 36.dp, end = 16.dp)
            .fillMaxWidth()
            .height(704.dp)
            .verticalScroll(state = ScrollState(0))
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = "Φίλτρα",
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
                color = Color(0xFF4A4A4A)
            )

            TextButton(onClick = {
                model.clearFilters()
            }) {
                Text(
                    "Καθαρισμός ->",
                    fontWeight = FontWeight(700),
                    fontSize = 14.sp,
                    color = Color(
                        0xFFF3830E
                    )
                )
            }
        }
        Text(
            "Αυτοκινητόδρομος",
            fontWeight = FontWeight(700),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Column(
            modifier = Modifier.padding(top = 20.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            radioButtons.value?.forEach {

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
                            selected = selectedRadio == it,
                            onClick = {
                                model.selectConcession(it)
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFFF3830E),
                                unselectedColor = Color(0xFFFDECDD),

                            ),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            it,
                            fontWeight = FontWeight(if (selectedRadio == it) 700 else 400),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
        Text(
            "Σταθμός διοδίων",
            fontWeight = FontWeight(700),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Column(
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            checkBoxes.value?.forEach {
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
                        Checkbox(
                            checked = selectedCheckBoxes.contains(it),
                            onCheckedChange = { add ->
                                model.changeTollStations(it, !add)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFFF3830E),
                                uncheckedColor = Color(0xFFFFE0BD)
                            )
                        )
                        Text(
                            it,
                            fontWeight = FontWeight(if (selectedCheckBoxes.contains(it)) 700 else 400),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
        OutlinedButton(
            onClick = {
                model.filter()
                onApplyFilter()
            },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(107.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFFF3830E),
                contentColor = Color.White
            ),
            border = BorderStroke(width = 3.dp, Color(0xFFFFB86D))
        ) {
            Text(
                "Εμφάνιση αποτελεσμάτων",
                fontWeight = FontWeight(700),
                fontSize = 16.sp
            )
        }
    }
}
