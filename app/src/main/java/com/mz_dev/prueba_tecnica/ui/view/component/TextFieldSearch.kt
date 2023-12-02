package com.mz_dev.prueba_tecnica.ui.view.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSearch(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier
) {
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        value = query,
        onValueChange = { onQueryChange(it) },
        placeholder = {
            Text("Search", color = Color.LightGray)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        modifier = modifier.fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .onGloballyPositioned {
                focusRequester.requestFocus()
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            textColor = MaterialTheme.colorScheme.onPrimary
        ),
        singleLine = true
    )
}