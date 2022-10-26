package com.simpletech.ewaysample.views.subviews

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simpletech.ewaysample.ui.theme.EwaySampleTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.InfoCircle

@Composable
fun InformCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(102.dp),
        color = Color.White.copy(alpha = 0.4f),
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .background(
                        color = Color(0xFF3183FF).copy(alpha = 0.16f),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp).fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        FontAwesomeIcons.Solid.InfoCircle, contentDescription = null,
                        tint = Color(0xFF3183FF), modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Ενημέρωση",
                        fontWeight = FontWeight(700),
                        fontSize = 12.sp,
                        color = Color(0xFF3183FF)
                    )
                }
            }
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight(400),
                            fontSize = 12.sp,
                            color = Color(0xFF8A8A8A)
                        )
                    ) {
                        append("Εμφανίζονται μόνο οι διελεύσεις σας για ημερομηνίες  μετά την τελευταία έκδοση")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight(700),
                            fontSize = 12.sp,
                            color = Color(0xFFF3830E)
                        )
                    ) {
                        append(" Μηνιαίου Λογαριασμού. -> ")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun InformCardPreview() {
    EwaySampleTheme {
        InformCard()
    }
}
