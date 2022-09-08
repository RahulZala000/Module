package stepper.module

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rahul.monthview.AdapterClickListener
import stepper.module.databinding.FragmentDayBinding
import stepper.module.databinding.FragmentMonthBinding
import java.time.LocalDate
import java.time.LocalDateTime

class MonthFragment : Fragment() {

    lateinit var binding: FragmentMonthBinding
    private val viewmodel:EventViewmodel by activityViewModels()
    var date= LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentMonthBinding.inflate(layoutInflater, container, false)

        var month=binding.monthview

        month.listner=object : AdapterClickListener{
            override fun onItemClick(view: View?, pos: Int, `object`: Any?) {
                date=`object` as LocalDate

                if((viewmodel.datefilter(date)).size==0)
                    Toast.makeText(context,"Today No Event",Toast.LENGTH_SHORT).show()
                else
                    findNavController().navigate(MonthFragmentDirections.actionMonthFragmentToEventBottomSheetFragment(date))
            }

        }

        return binding.root
    }



}