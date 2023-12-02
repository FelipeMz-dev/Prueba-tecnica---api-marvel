package com.mz_dev.prueba_tecnica.ui.view.component.topappbar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TopNavigationBar(
    sections: List<String>,
    sectionSelected: String,
    onClickItem: (String) -> Unit) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary,
        elevation = 0.dp
    ) {
        BottomNavigationItem(
            selected = sectionSelected == sections[0],
            onClick = { onClickItem(sections[0]) },
            icon = { },
            label = {
                Text(
                    sections[0],
                    color = if (sectionSelected == sections[0]) {
                        MaterialTheme.colorScheme.tertiary
                    } else MaterialTheme.colorScheme.onPrimary
                )
            }
        )
        BottomNavigationItem(
            selected = sectionSelected == sections[1],
            onClick = { onClickItem(sections[1]) },
            icon = { },
            label = {
                Text(
                    sections[1],
                    color = if (sectionSelected == sections[1]) {
                        MaterialTheme.colorScheme.tertiary
                    } else MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}