package ui.views

import ViewModel.ViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import data.SeatsType
import data.SeatsTypeSupport
import data.TicketTypes
import data.TicketTypesSupport
import data.model.CurrentUser
import data.model.Ticket
import data.model.TicketSupport
import data.model.User
import database.tables.Perfomance
import ui.theme.black
import ui.theme.green
import ui.theme.halfTransparent
import ui.theme.velvetRed
import kotlin.random.Random

@Composable
fun BuyTicketDialog(initialCost: Double, onDismiss: () -> Unit, title: String, viewModel: ViewModel, perfomance: Perfomance) {
    var ticket by remember { mutableStateOf(Ticket(
        initialCost = initialCost,
        seatPlace = Random.nextInt(1, 100),
        seatType = SeatsType.REGULAR,
        ticket = data.TicketTypes.DEFAULT
    )) }


    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(vertical = 30.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                outlined_text_field(
                    placeholder = "Имя",
                    value = viewModel.userState.firstName,
                    onValueChange = { newValue -> viewModel.changeName(newValue) },
                    checker = User::checkNameSurname,
                    errorText = "Некорректное имя",
                    isReadOnly = CurrentUser.user.firstName != ""
                )

                outlined_text_field(
                    placeholder = "Фамилия",
                    value = viewModel.userState.lastName,
                    onValueChange = { newValue -> viewModel.changeSurname(newValue) },
                    checker = User::checkNameSurname,
                    errorText = "Некорректная фамилия",
                    isReadOnly = CurrentUser.user.lastName != ""
                )

                outlined_text_field(
                    placeholder = "Email",
                    value = viewModel.userState.email,
                    onValueChange = { newValue -> viewModel.changeEmail(newValue) },
                    checker = User::checkNotExistEmail,
                    errorText = "Пользователь с таким email уже существует",
                    isReadOnly = CurrentUser.user.email != ""
                )

                outlined_text_field(
                    placeholder = "Телефон",
                    value = viewModel.userState.mobilePhone,
                    onValueChange = { newValue -> viewModel.changePhone(newValue) },
                    checker = User::checkExistPhone,
                    errorText = "Некорректный телефон",
                    isReadOnly = CurrentUser.user.mobilePhone != ""
                )

                DropdownMenu_text_field(
                    elements = SeatsType.entries.map { it.typeName },
                    onValuesChanged = { newValue -> ticket = ticket.copy(seatType = SeatsTypeSupport.getSeatType(newValue)) },
                )

                DropdownMenu_text_field(
                    elements = TicketTypes.entries.map { it.typeName },
                    onValuesChanged = { newValue -> ticket = ticket.copy(ticket = TicketTypesSupport.getTicketType(newValue)) },
                )

                Text(
                    text = "Цена: ${ticket.calculatePrice(viewModel.userState.benefit)}",
                    style = TextStyle(fontSize = 20.sp, color = black),
                    textAlign = TextAlign.Center
                )

                color_selected_button(
                    text = "Купить",
                    color = green,
                    onClick = {
                        if (CurrentUser.user.email != "") {
                            TicketSupport.buyTicket(
                                ticket.seatType.id,
                                ticket.ticket.id,
                                CurrentUser.user.id,
                                perfomance.id,
                                ticket.seatPlace
                            )
                        } else {
                            if(User.addUser(viewModel.userState, true)) {
                                println("User added successfully")
                                TicketSupport.buyTicket(
                                    ticket.seatType.id,
                                    ticket.ticket.id,
                                    CurrentUser.user.id,
                                    perfomance.id,
                                    ticket.seatPlace
                                )
                            }
                        }
                        onDismiss()
                    },
                    currentIndex = 0,
                    icon = null
                )

            }
        }
    }
}