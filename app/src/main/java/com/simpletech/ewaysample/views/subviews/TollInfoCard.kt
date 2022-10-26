package com.simpletech.ewaysample.views.subviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simpletech.ewaysample.models.TransactionElement
import com.simpletech.ewaysample.ui.theme.EwaySampleTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ChevronRight

@Composable
fun TollInfoCard(transaction:TransactionElement){
 Surface(
     modifier = Modifier
         .fillMaxWidth()
         .height(61.dp)
         .background(Color.White, shape = RoundedCornerShape(13.dp)),
        elevation = 12.dp,
 shape = RoundedCornerShape(13.dp)) {
Row(modifier = Modifier
    .padding(start = 24.dp,end = 24.dp, top = 14.dp,bottom = 12.dp)
    .fillMaxSize(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically) {
    Column(horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(2.dp)) {
    Text(transaction.concession,
    fontSize = 14.sp,
    fontWeight = FontWeight(400),
        color = Color(0xFF4A4A4A)
    )
        Row(
            horizontalArrangement = Arrangement.spacedBy(9.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                transaction.transactionDate.replace("T"," - "),
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFAEAEAE)
            )
            Divider(
                modifier = Modifier.height(16.dp).width(1.dp),
                color = Color(0xFFAEAEAE)
            )
            Text(
                transaction.tollStation,
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFAEAEAE)
            )
        }
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(transaction.totalAmount.toString() + "€",
            fontSize = 18.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF4A4A4A)
        )

          Icon(FontAwesomeIcons.Solid.ChevronRight,
          contentDescription = null,
          tint = Color(0xFFF3830E),
              modifier = Modifier.size(16.dp)
          )

    }
}
 }
}


//@Preview
//@Composable
//fun TollInfoCardPreview(){
//EwaySampleTheme {
//    TollInfoCard()
//}
//}