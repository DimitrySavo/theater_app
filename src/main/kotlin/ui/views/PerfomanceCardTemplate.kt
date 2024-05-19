package ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import database.DatabaseService.db
import database.tables.GenreSupport
import database.tables.Perfomance
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import ui.theme.halfTransparent
import ui.theme.velvetRed

@Composable
fun PerformanceCardTemplate(perfomance: Perfomance) {


    Card(
        modifier = Modifier.padding(horizontal = 40.dp).fillMaxWidth().requiredHeight(120.dp),
        elevation = 8.dp,
        border = BorderStroke(2.dp, velvetRed)
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
            Column(
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
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