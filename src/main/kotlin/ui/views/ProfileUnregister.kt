package ui.views

import ViewModel.ViewModel
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.*
import javax.swing.text.View


@Composable
@Preview()
fun profileUnregister(viewModel: ViewModel) {
    var isSignIn by remember { mutableStateOf(false) }

    if(isSignIn){
        SignInDialog(viewModel)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            Text(
                text = "Вы еще не авторизованны. Войдите или зарегистрируйтесь, чтобы получать скидки.",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = halfTransparent,
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 20.dp),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                link_button(
                    onClick = {
                        isSignIn = true
                    },
                    text = "Войти",
                    modifier = Modifier.padding(end = 50.dp, top = 20.dp)
                )

                link_button(
                    onClick = { println("register") },
                    text = "Зарегистрироваться",
                    modifier = Modifier.padding(start = 50.dp, top = 20.dp)
                )
            }
        }

    }
}