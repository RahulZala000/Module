package stepper.module

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendardayview.TextChangeListner
import stepper.module.databinding.ActivityDayBinding
import java.time.LocalDateTime


class DayActivity : AppCompatActivity() {

    lateinit var resultlaucer: ActivityResultLauncher<Intent>
    lateinit var binding: ActivityDayBinding

    lateinit var viewmodel: EventViewmodel
    lateinit var event: EventModel
    lateinit var adapter: EventAdapter
    var date = LocalDateTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[EventViewmodel::class.java]



        var dayview = binding.dayview
        setevent()
        resultlaucer = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {

                    val data: Intent? = result.data
                    event = result.data?.getSerializableExtra("Model") as EventModel
                    viewmodel.addevent(event)
                    adapter.addevent(viewmodel.eventList)
                    adapter.notifyDataSetChanged()
                    setevent()
                }

                dayview.listner = object : TextChangeListner {
                    override fun onItem(`object`: Any?) {
                        date = `object` as LocalDateTime
                        Toast.makeText(this@DayActivity,date.toLocalDate().toString(),Toast.LENGTH_SHORT).show()
                        adapter.addevent(viewmodel.datefilter(date.toLocalDate()))
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    fun newEventAction(view: View) {
        var intent = Intent(this, MonthActivity::class.java)
        intent.putExtra("Date", date)
        startActivity(intent)
      //  resultlaucer.launch(intent)

    }

    fun setevent() {

        adapter = EventAdapter()
        binding.event.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        adapter.addevent(viewmodel.datefilter(date.toLocalDate()))
        // Log.d("@date",viewmodel.datefilter(date.toLocalDate()).toString())
        binding.event.setAdapter(adapter)
        binding.event.adapter!!.notifyDataSetChanged()

        //   Log.d("@event",viewmodel.eventList.size.toString())
    }
}