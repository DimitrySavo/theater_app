package ui.views

import ViewModel.ViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Constants
import database.tables.GenreSupport
import database.tables.Perfomance
import ui.theme.halfTransparent
import ui.theme.velvetRed
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PerfomanceOnPosterCard(
    perfomance: Perfomance,
    freeSeats: Int,
    dateTime: String,
    perfomanceInitialTicketCost: Double,
    viewModel: ViewModel
) {
    val dateTimeRightFormat = ""
    val dateTimePerfomance = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME)
    var isBuyTicketDialogShow by remember { mutableStateOf(false) }

    if (isBuyTicketDialogShow) {
        BuyTicketDialog(perfomanceInitialTicketCost, { isBuyTicketDialogShow = false}, "Покупка билета", viewModel, perfomance)
    }


    Card(
        modifier = Modifier.padding(horizontal = 40.dp).fillMaxWidth().requiredHeight(120.dp),
        elevation = 8.dp,
        border = BorderStroke(2.dp, velvetRed),
        onClick = {
            isBuyTicketDialogShow = true
        }
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Text(
                perfomance.perfomanceName, modifier = Modifier.align(Alignment.TopStart),
                style = TextStyle(fontSize = 24.sp, color = halfTransparent)
            )
            Text(
                perfomance.ageRestrictions, modifier = Modifier.align(Alignment.BottomStart),
                style = TextStyle(fontSize = 16.sp, color = halfTransparent)
            )
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column {
                    Text(
                        text = "Свободных мест: $freeSeats",
                        style = TextStyle(fontSize = 16.sp, color = halfTransparent)
                    )

                    Text(
                        text = "Дата и время: ${dateTimePerfomance.format(Constants.DATE_FORMAT)}",
                        style = TextStyle(fontSize = 16.sp, color = halfTransparent)
                    )

                    Text(
                        text = "Цена билета: $perfomanceInitialTicketCost",
                        style = TextStyle(fontSize = 16.sp, color = halfTransparent)
                    )
                }
                Column{
                    Text(
                        text = "Кол-во антрактов: ${perfomance.amountOfIntermissions}",
                        style = TextStyle(fontSize = 16.sp, color = halfTransparent)
                    )

                    Text(
                        text = "Продолжительность: ${perfomance.perfomanceDuration} минут",
                        style = TextStyle(fontSize = 16.sp, color = halfTransparent)
                    )

                    Text(
                        text = "Жанр: ${GenreSupport.getGenreName(perfomance.genre)}",
                        style = TextStyle(fontSize = 16.sp, color = halfTransparent)
                    )
                }

            }
        }
    }
}