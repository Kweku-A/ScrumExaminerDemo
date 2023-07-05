package com.kweku.armah.core.presentation.composables.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DualOptionDialog(
    header: String,
    dialogMessage: String = "",
    isDialogShown: (Boolean) -> Unit,
    negativeButtonText: String = "Cancel",
    onPositiveClick: () -> Unit = {},
    positiveButtonText: String = "Ok",
) {
    Dialog(onDismissRequest = { }, properties = DialogProperties()) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.elevatedCardColors(),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = header,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = dialogMessage,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                    ),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = { isDialogShown(false) }) {
                        Text(text = negativeButtonText)
                    }
                    TextButton(onClick = onPositiveClick) {
                        Text(text = positiveButtonText)
                    }
                }
            }
        }
    }
}
