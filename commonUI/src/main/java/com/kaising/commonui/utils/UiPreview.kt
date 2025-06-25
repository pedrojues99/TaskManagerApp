package com.kaising.commonui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kaising.commonui.theme.TaskManagerAppTheme

@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Dark Theme")
@Composable
fun ThemePreview() {
    TaskManagerAppTheme {
        // Aquí puedes colocar un ejemplo de composable común
    }
}