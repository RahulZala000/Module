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
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.calendardayweekview.AdapterClickListener
import stepper.module.databinding.FragmentWeekBinding
import java.time.LocalDateTime

class WeekFragment : Fragment() {

    lateinit var binding:FragmentWeekBinding
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
        binding=FragmentWeekBinding.inflate(layoutInflater, container, false)

        setevent()

        var week=binding.week

        week.listner=object :AdapterClickListener{
            override fun onItemClick(view: View?, pos: Int, `object`: Any?) {
                date= `object` as LocalDateTime
                Toast.makeText(context,date.toLocalDate().toString(),Toast.LENGTH_SHORT).show()
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
        binding.weekevent.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.addevent(viewmodel.datefilter(date.toLocalDate()))
        binding.weekevent.setAdapter(adapter)
        binding.weekevent.adapter!!.notifyDataSetChanged()
    }
}