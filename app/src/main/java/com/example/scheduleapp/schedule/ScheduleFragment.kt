package com.example.scheduleapp.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scheduleapp.CustomSharedPreferences
import com.example.scheduleapp.databinding.FragmentScheduleBinding
import com.example.scheduleapp.mainactivity.MainActivity

class ScheduleFragment: Fragment() {
    private lateinit var customSharedPreferences: CustomSharedPreferences
    private val viewModelSchedule: ViewModelSchedule by viewModels()
    var mainActivity: MainActivity?=null

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity
        customSharedPreferences = CustomSharedPreferences(context = requireContext())
        customSharedPreferences.getValueString("email")?.let {
            viewModelSchedule.settingDB(it)
        }
            Log.i(tag, "!observe")


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.scheduleRecyclerView.adapter = ScheduleRecyclerAdapter(viewModelSchedule.scheduleList as List<ScheduleItem>)
//        binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}