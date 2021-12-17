package com.example.scheduleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.scheduleapp.databinding.FragmetFormForSignInAndOutOnClubBinding

class FormForSignInAndOutOnClubFragment : Fragment() {

    val viewModelFormForSignInAndOutOnClub:ViewModelFormForSignInAndOutOnClub by viewModels()

    var screenType:Int? = null

    private var _binding: FragmetFormForSignInAndOutOnClubBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmetFormForSignInAndOutOnClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        screenType = arguments?.getInt("arg")
        setupButton()
    }

    fun setupButton() {
        if (screenType==0) {
            binding.clubButton.text = "Записаться на кружок"
        } else {
            if(screenType==1) {
                binding.clubButton.text = "Отписаться от кружка"
            } else {
                binding.clubButton.text = "Произошла ошибка"
                binding.clubButton.isClickable = false
            }
        }
    }

    fun setupSpinner() {
        val spinner = binding.clubSpinner
        for (i in viewModelFormForSignInAndOutOnClub.clubs.indices) {
            viewModelFormForSignInAndOutOnClub.clubsString.add(viewModelFormForSignInAndOutOnClub.clubs[i].name.toString())
        }
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, viewModelFormForSignInAndOutOnClub.clubsString)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModelFormForSignInAndOutOnClub.selectedClub = viewModelFormForSignInAndOutOnClub.clubs[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Code to perform some action when nothing is selected
            }
        }
    }

}