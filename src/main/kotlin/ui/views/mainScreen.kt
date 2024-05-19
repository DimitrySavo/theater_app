package ui.views

import ViewModel.UIState
import ViewModel.ViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.CurrentUser
import ui.theme.*
import database.DatabaseService.db
import database.tables.*
import org.ktorm.dsl.*
import java.time.Instant

@Composable
fun mainScreen(viewModel: ViewModel) {
    var currentUIState = UIState.state.value

    Row(modifier = Modifier.fillMaxSize().background(white)) {
        Column(
            modifier = Modifier.fillMaxHeight().background(white).padding(16.dp).weight(1f),
        ) {
            sidebar()
        }

        Spacer(Modifier.fillMaxHeight().padding(start = 10.dp, end = 10.dp).width(1.dp).background(halfTransparent))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight().weight(2f).background(white).padding(16.dp)
        ) {
            when (currentUIState) {
                UIState.PROFILE -> {
                    if (CurrentUser.user.firstName == "") {
                        profileUnregister(viewModel)
                    } else {
                        ProfileRegistered(viewModel)
                    }
                }

                UIState.POSTER -> {
                    val perfomances: MutableList<PerfomanceOnPoster> = mutableListOf()

                    db.from(PosterTable).select().where { (PosterTable.perfomanceDateTime.greater(Instant.now())) }.forEach { row ->
                        perfomances.add(
                            PerfomanceOnPoster(
                                id = row[PosterTable.id] ?: 0,
                                perfomanceCode = row[PosterTable.perfomanceCode] ?: 0,
                                hallCode = row[PosterTable.hallCode] ?: 0,
                                perfomanceDateTime = row[PosterTable.perfomanceDateTime].toString(),
                                perfomanceInitialTicketCost = row[PosterTable.perfomanceInitialTicketCost]
                                    ?: 0.0
                            )
                        )
                    }

                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        perfomances.forEach { perfomance ->
                            PerfomanceOnPosterCard(
                                PerfomanceSupport.getPerfomance(perfomance.perfomanceCode),
                                PosterSupport.executeGetTotalFreeSeats(perfomance.id),
                                perfomance.perfomanceDateTime,
                                perfomance.perfomanceInitialTicketCost,
                                viewModel
                            )
                        }
                    }
                }

                UIState.REPERTOIRE -> {
                    PerfomanceSupport.getPerfomance(1)

                    val perfomances: MutableList<Perfomance> = mutableListOf()
                    db.from(database.tables.PerfomanceTable).select().forEach { row ->
                        perfomances.add(
                            Perfomance(
                                id = row[database.tables.PerfomanceTable.id] ?: 0,
                                genre = row[database.tables.PerfomanceTable.genre] ?: 0,
                                author = row[database.tables.PerfomanceTable.author] ?: 0,
                                workInBase = row[database.tables.PerfomanceTable.workInBase] ?: "",
                                perfomanceDuration = row[database.tables.PerfomanceTable.perfomanceDuration] ?: 0,
                                ageRestrictions = row[database.tables.PerfomanceTable.ageRestrictions] ?: "",
                                amountOfIntermissions = row[database.tables.PerfomanceTable.amountOfIntermissions] ?: 0,
                                perfomanceName = row[database.tables.PerfomanceTable.perfomanceName] ?: ""
                            )
                        )
                    }

                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        perfomances.forEach { perfomance ->
                            PerformanceCardTemplate(perfomance)
                        }
                    }
                }
            }
        }
    }
}
