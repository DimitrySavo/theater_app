package ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.*

@Composable
fun profileUnregister(onClick:()->Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Вы еще не авторизаванны. Выберите способ авторизации.",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = halfTransparent,
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 20.dp),
            textAlign = TextAlign.Center
        )

    }
}