package com.example.scheduleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scheduleapp.databinding.FragmentScheduleBinding

class ScheduleFragment: Fragment() {
    val viewModelSchedule:ViewModelSchedule by viewModels()

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelSchedule.setScheduleList()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scheduleRecyclerView.adapter = ScheduleRecyclerAdapter(viewModelSchedule.scheduleList as List<ScheduleItem>)
        binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


}