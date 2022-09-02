package stepper.module

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rahul.horizontal_stepper.AdapterClickListener
import stepper.module.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

class MainActivity : Activity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var data= ArrayList<String>()
        data.add("First")
        data.add("Second")
        data.add("Third")
        data.add("Four")

        var step=binding.step
        step.setdata(data,0,data.size)

        step.listner=object : AdapterClickListener{
            override fun onItemClick(view: View?, pos: Int, `object`: Any?) {
               Toast.makeText(this@MainActivity,`object`.toString(),Toast.LENGTH_SHORT).show()
            }

        }

        step.init()

        binding.Calender.setOnClickListener{
                startActivity(Intent(this,CalenderActivity::class.java))
        }
    }
}