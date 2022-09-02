package stepper.module

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import java.time.LocalDate

class EventViewmodel(app: Application) : AndroidViewModel(app) {

    var eventList: ArrayList<EventModel> = ArrayList()

    fun addevent(event: EventModel) {
        eventList.add(event)
    }

    fun datefilter(date: LocalDate): ArrayList<EventModel> {

        var list: ArrayList<EventModel> = ArrayList()
        for (data in eventList) {
            if (data.date == date)
                list.add(data)
        }
        return list
    }
}
