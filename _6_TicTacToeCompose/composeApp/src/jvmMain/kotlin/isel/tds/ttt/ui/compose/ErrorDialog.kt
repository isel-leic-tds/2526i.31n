package isel.tds.ttt.ui.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun ErrorDialog(errorMessage: String, hideDialog: () -> Unit) = BaseInfoDialog(
    title = "Error",
    closeAction = hideDialog
) {
    Text(errorMessage, style = MaterialTheme.typography.h6)
}
