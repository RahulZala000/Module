package stepper.module

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import stepper.module.databinding.FragmentEventBottomSheetBinding

class EventBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentEventBottomSheetBinding
    private val viewmodel: EventViewmodel by activityViewModels()
    lateinit var adapter: EventAdapter
    val args:EventBottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentEventBottomSheetBinding.inflate(layoutInflater, container, false)

        setevent()

        /*var sheet:BottomSheetDialog=object : BottomSheetDialog(context!!)
        sheet.setContentView(R.layout.fragment_event_bottom_sheet)
        sheet.show()*/

        return binding.root
    }

    fun setevent() {
        adapter = EventAdapter()
        binding.eventslist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.addevent(viewmodel.datefilter(args.date))
        binding.eventslist.setAdapter(adapter)
        binding.eventslist.adapter!!.notifyDataSetChanged()
    }
}