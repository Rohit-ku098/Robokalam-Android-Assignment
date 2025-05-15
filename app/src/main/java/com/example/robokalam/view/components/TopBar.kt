package com.example.robokalam.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.robokalam.R
import com.example.robokalam.model.UserModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    action: @Composable RowScope.() -> Unit = {},
    navigation: @Composable () -> Unit = {}
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )

        },

        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        actions = action,
        navigationIcon = navigation
    )

}