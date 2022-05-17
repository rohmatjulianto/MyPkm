package com.joule.mypoke.commons

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding>(@LayoutRes private val layoutId: Int, private val bind: (View) -> B, ) : Fragment(layoutId) {
    private var _binding: B? = null
    val binding get() = _binding as B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = bind(view)

        onSetTitleToolbar((requireActivity() as AppCompatActivity?)?.supportActionBar)
        init()

        // Adding an option to handle the back press in fragment
        with(requireActivity()) {
            if (supportFragmentManager.backStackEntryCount > 0) {
                onBackPressedDispatcher.addCallback(
                    viewLifecycleOwner,
                    object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            onBackPressed()
                        }
                    })
            }
        }
    }

    open fun onBackPressed() {
        findNavController().popBackStack()
    }

    open fun onSetTitleToolbar(supportActionBar: ActionBar?) {}
    abstract fun init()

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}