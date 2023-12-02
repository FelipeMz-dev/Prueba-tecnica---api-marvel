package com.mz_dev.prueba_tecnica.ui.view.component.topappbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
    icon: ImageVector,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = "action app bar",
        tint = color,
        modifier = Modifier
            .clickable(onClick = onClick)
            .size(28.dp)
    )
}