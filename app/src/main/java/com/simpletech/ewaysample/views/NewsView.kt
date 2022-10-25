package com.simpletech.ewaysample.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simpletech.ewaysample.ui.theme.barColors
import com.simpletech.ewaysample.ui.theme.red_100
import com.simpletech.ewaysample.ui.theme.teal_100
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowDown

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsView(){
    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Νέα και προσφορές",
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
        backgroundColor = teal_100,
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize())
    }

}