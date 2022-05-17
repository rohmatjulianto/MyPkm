package com.joule.mypoke.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.joule.mypoke.R
import com.joule.mypoke.commons.BaseFragment
import com.joule.mypoke.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home, { FragmentHomeBinding.bind(it)}) {


    val viewModel : HomeViewModel by inject()

    override fun init() {
        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner){
            textView.text = it
        }

        textView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToDetailActivity("bulbasaur")
            findNavController().navigate(action)
        }
    }
}