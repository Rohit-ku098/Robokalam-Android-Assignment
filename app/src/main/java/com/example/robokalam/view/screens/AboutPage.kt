package com.example.robokalam.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.robokalam.R
import com.example.robokalam.view.components.ScreenSkeleton
import com.example.robokalam.view.components.TopBar

@Composable
fun AboutPage(
    onBack: () -> Unit
) {
    ScreenSkeleton(
        topAppBar = {
            TopBar(
                title = "About",
                navigation = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.robokalam),
                contentDescription = "RoboKalam",
                modifier = Modifier.padding(8.dp).size(100.dp),

            )

            Text(
                text = "Robokalam",
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = "ROBOKALAM TECHNOLOGIES (Literature of Robotics) is a 21st-century company that focuses on education solutions, covering robotics, IoT, AI, coding, and doing research & development in the latest technologies. We build products and also provide workshops, training, certifications, and DIY (do it yourself) kits to institutes all across the globe."
            )
        }
    }
}