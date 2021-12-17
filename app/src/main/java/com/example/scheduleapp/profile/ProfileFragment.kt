package com.example.scheduleapp.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.example.scheduleapp.*
import com.example.scheduleapp.databinding.FragmentProfileBinding
import com.example.scheduleapp.mainactivity.MainActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment: Fragment() {
    private val viewModelProfile: ViewModelProfile by viewModels()
    private lateinit var customSharedPreferences: CustomSharedPreferences

    val bundle = Bundle()

    var mainActivity: MainActivity?=null

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity
        customSharedPreferences = CustomSharedPreferences(context = requireContext())

        viewModelProfile.liveDataStudent.observe(viewLifecycleOwner) {
            Log.i(tag, "!observeProfile")
            binding.allNameText.text = "${it.firstName} ${it.secondName} ${it.lastName}"
        }

        mainActivity?.viewModelMainActivity?.liveDataStudent?.observe(viewLifecycleOwner) {
            Log.i(tag, "!observeProfileMain")
            viewModelProfile.setLiveDataStudent(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.clubInformation.setOnClickListener {
            val intent = Intent(activity, GuestActivity::class.java)
            startActivity(intent)
        }

        binding.signUpForAClub.setOnClickListener {
            bundle.putInt("arg", 0) // 0 - значение для записаться на кружок
            view.findNavController().navigate(R.id.action_profileFragment_to_formForSignInAndOutOnClubFragment, bundle)
        }

        binding.signOutFromAClub.setOnClickListener {
            bundle.putInt("arg", 1) // 1 - значение для отписаться от кружка
            view.findNavController().navigate(R.id.action_profileFragment_to_formForSignInAndOutOnClubFragment, bundle)
        }

        binding.signOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            customSharedPreferences.removeValue("email")
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}