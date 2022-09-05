package stepper.module

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import stepper.module.databinding.ActivityCalenderBinding
import java.time.LocalDateTime
import com.rahul.calendardayweekview.AdapterClickListener
import java.util.*
import kotlin.collections.ArrayList

class CalenderActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalenderBinding
    var date:LocalDateTime= LocalDateTime.now()

    lateinit var viewmodel:EventViewmodel
    lateinit var event:EventModel
    lateinit var adapter: EventAdapter

    lateinit var resultlaucer:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCalenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel=ViewModelProvider(this)[EventViewmodel::class.java]

        var weekview=binding.weekview

        binding.dayview.setOnClickListener{
            startActivity(Intent(this,DayActivity::class.java))
        }


        resultlaucer = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
               event= result.data?.getSerializableExtra("Model") as EventModel
                viewmodel.addevent(event)
              //  adapter.addevent(viewmodel.eventList)
               //adapter.notifyDataSetChanged()
                setevent()
            }


        }

        setevent()

        weekview.listner= object : AdapterClickListener {
            override fun onItemClick(view: View?, pos: Int, `object`: Any?) {
                date= `object` as LocalDateTime
                Toast.makeText(this@CalenderActivity,date.toLocalDate().toString(),Toast.LENGTH_SHORT).show()
                adapter.addevent(viewmodel.datefilter(date.toLocalDate()))
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun newEventAction(view: View) {
        var intent=Intent(this,EventActivity::class.java)
        intent.putExtra("Date",date)
        resultlaucer.launch(intent)
        setevent()
    }

    fun setevent(){

        adapter = EventAdapter()
        binding.eventListView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true)
        adapter.addevent(viewmodel.datefilter(date.toLocalDate()))
        Log.d("@date",viewmodel.datefilter(date.toLocalDate()).toString())
        binding.eventListView.setAdapter(adapter)
        binding.eventListView.adapter!!.notifyDataSetChanged()

        Log.d("@event",viewmodel.eventList.size.toString())
    }

}