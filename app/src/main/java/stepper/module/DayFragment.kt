package stepper.module

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendardayview.TextChangeListner
import stepper.module.databinding.FragmentDayBinding
import java.time.LocalDateTime


class DayFragment : Fragment() {

    lateinit var binding: FragmentDayBinding

    private val viewmodel: EventViewmodel by activityViewModels()
    lateinit var adapter: EventAdapter
    var date = LocalDateTime.now()
    lateinit var event: EventModel
    lateinit var resultlaucer: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDayBinding.inflate(layoutInflater, container, false)

        setevent()

        var day = binding.dayview

        day.listner = object : TextChangeListner {
            override fun onItem(`object`: Any?) {
                date = `object` as LocalDateTime
                setevent()
                Toast.makeText(context, date.toLocalDate().toString(), Toast.LENGTH_SHORT).show()
                adapter.addevent(viewmodel.datefilter(date.toLocalDate()))
                adapter.notifyDataSetChanged()
            }
        }

        binding.event.setOnClickListener {
            var intent = Intent(activity, EventActivity::class.java)
            intent.putExtra("Date", date)
            resultlaucer.launch(intent)
        }

        resultlaucer =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {

                    val data: Intent? = result.data
                    event = result.data?.getSerializableExtra("Model") as EventModel
                    viewmodel.addevent(event)
                    Log.d("@d", viewmodel.eventList.size.toString())
                    setevent()
                }
            }

        return binding.root
    }

    fun setevent() {

        adapter = EventAdapter()
        binding.dayevent.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.addevent(viewmodel.datefilter(date.toLocalDate()))
        // Log.d("@date",viewmodel.datefilter(date.toLocalDate()).toString())
        binding.dayevent.setAdapter(adapter)
        binding.dayevent.adapter!!.notifyDataSetChanged()

        //   Log.d("@event",viewmodel.eventList.size.toString())
    }
}