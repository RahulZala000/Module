package stepper.module

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


data class EventModel(
    var name: String? = null,
    val date: LocalDate? = null,
    val time: LocalTime? = null
):Serializable
