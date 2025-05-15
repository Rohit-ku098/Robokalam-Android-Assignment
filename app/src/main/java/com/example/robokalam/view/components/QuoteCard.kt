package com.example.robokalam.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.robokalam.model.Quote

@Composable
fun QuoteCard(
    quote: Quote
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
//        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = "Quote of the Day",
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        )
        Text(
            text = "\"${quote.q}\"",
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "- ${quote.a}",
            modifier = Modifier.padding(16.dp),
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic
        )
    }
}