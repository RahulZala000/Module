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
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import stepper.module.databinding.ActivityCalenderBinding
import java.time.LocalDateTime
import com.rahul.calendardayweekview.AdapterClickListener
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class CalenderActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalenderBinding
    lateinit var navcontroller:NavController

    lateinit var viewmodel:EventViewmodel
    lateinit var event:EventModel
    lateinit var adapter: EventAdapter

    lateinit var resultlaucer:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCalenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel=ViewModelProvider(this)[EventViewmodel::class.java]
        navcontroller=(supportFragmentManager.findFragmentById(R.id.navcontroller) as NavHostFragment).navController

        binding.day.setOnClickListener {
           navcontroller.popBackStack()
            navcontroller.navigate(R.id.dayFragment)
        }

        binding.week.setOnClickListener {
            navcontroller.popBackStack()
            navcontroller.navigate(R.id.weekFragment)
        }

        binding.month.setOnClickListener {
            navcontroller.popBackStack()
            navcontroller.navigate(R.id.monthFragment)
        }

     /*   binding.event.setOnClickListener {
            var intent=Intent(this,EventActivity::class.java)
            intent.putExtra("Date",LocalDateTime.now())
            resultlaucer.launch(intent)
        }*/

        resultlaucer = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
               event= result.data?.getSerializableExtra("Model") as EventModel
                viewmodel.addevent(event)
                Log.d("@d",viewmodel.eventList.size.toString())
              //  adapter.addevent(viewmodel.eventList)
               //adapter.notifyDataSetChanged()
              //  setevent()
            }

        }
    }

    override fun navigateUpTo(upIntent: Intent?): Boolean {
        return navcontroller.navigateUp()
    }



   /* fun setevent(){

        adapter = EventAdapter()
        binding.eventListView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true)
        adapter.addevent(viewmodel.datefilter(date.toLocalDate()))
        Log.d("@date",viewmodel.datefilter(date.toLocalDate()).toString())
        binding.eventListView.setAdapter(adapter)
        binding.eventListView.adapter!!.notifyDataSetChanged()

        Log.d("@event",viewmodel.eventList.size.toString())
    }*/

}