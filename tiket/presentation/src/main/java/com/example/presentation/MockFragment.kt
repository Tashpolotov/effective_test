package com.example.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_utils.base.BaseFragment
import com.example.presentation.databinding.FragmentMockBinding


class MockFragment : BaseFragment(R.layout.fragment_mock) {

    private val binding by viewBinding(FragmentMockBinding::bind)

    override fun initialize() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}