package stepper.module

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import stepper.module.databinding.ActivityEventBinding
import java.time.LocalDateTime
import java.time.LocalTime

class EventActivity : AppCompatActivity() {

    lateinit var binding: ActivityEventBinding
    var date = LocalDateTime.now()
    lateinit var viewmodel: EventViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[EventViewmodel::class.java]

        if (intent != null)
            date = intent.getSerializableExtra("Date") as? LocalDateTime



        binding.eventDateTV.text = date.toLocalDate().toString()

        binding.eventTimeTV.text = date.toLocalTime().toString()


    }

    fun saveEventAction(view: View) {

        val newEvent = EventModel(binding.eventNameET.text.toString(), date.toLocalDate(), LocalTime.now())

        var newintent = Intent(this, CalenderActivity::class.java)
        newintent.putExtra("Model", newEvent)
        setResult(Activity.RESULT_OK,newintent)

        finish()
    }
}