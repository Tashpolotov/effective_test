package com.example.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_utils.SharedPref
import com.example.core_utils.base.BaseFragment
import com.example.presentation.adapter.MainScreenAdapter
import com.example.presentation.databinding.FragmentTiketBinding
import com.example.presentation.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiketFragment : BaseFragment(R.layout.fragment_tiket) {

    private val binding by viewBinding(FragmentTiketBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    private val adapter = MainScreenAdapter()
    private lateinit var sharedPref: SharedPref

    override fun initialize() {
        binding.rv.adapter = adapter
        viewModel.loadMainScreen()
        sharedPref =  SharedPref(requireContext())
        if (sharedPref.selectedUserCity != null) {
            binding.etLocationCity.setText(sharedPref.selectedUserCity)
        }
        sharedPref.selectedName?.let { selectedCity ->
            binding.tvWhere.setText(selectedCity)
        }
        binding.tvWhere.setOnClickListener {
            showBottomSheet()
        }

        binding.etLocationCity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                sharedPref.selectedUserCity = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        viewModel.cyrillicFilter.value?.let { filter ->
            binding.etLocationCity.filters = arrayOf(filter)

        }


    }

    override fun initSubscribers() {
        viewModel.mainScreen.collectUIState (
            state = {
                binding.progressBar.visibility = View.VISIBLE
            },
            onSuccess = {
                binding.progressBar.visibility = View.GONE
                binding.linearContainer.visibility = View.VISIBLE
                binding.tvMusic.visibility = View.VISIBLE
                binding.btn.visibility = View.VISIBLE
                adapter.submitList(it.offers)
            }
        )
    }
    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.modal_window, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(bottomSheetView)
        val imgViewStrong = bottomSheetView.findViewById<ImageView>(R.id.img_strong)
        val imgViewHot = bottomSheetView.findViewById<ImageView>(R.id.img_hot)
        val imgViewWeekend = bottomSheetView.findViewById<ImageView>(R.id.img_weekend)
        val imgViewWhere = bottomSheetView.findViewById<ImageView>(R.id.img_where)
        val tvStambul = bottomSheetView.findViewById<TextView>(R.id.tv_stambul)
        val tvSochi = bottomSheetView.findViewById<TextView>(R.id.tv_sochi)
        val tvPhucket = bottomSheetView.findViewById<TextView>(R.id.tv_phucket)
        val etWhere = bottomSheetView.findViewById<TextInputEditText>(R.id.et_where)
        val etLocationCity = bottomSheetView.findViewById<TextInputEditText>(R.id.et_location_city)
        val const1 = bottomSheetView.findViewById<ConstraintLayout>(R.id.const1)
        val const2 = bottomSheetView.findViewById<ConstraintLayout>(R.id.const2)
        val const3 = bottomSheetView.findViewById<ConstraintLayout>(R.id.const3)
        dialog.show()

        imgViewStrong.setOnClickListener {
            findNavController().navigate(R.id.mockFragment)
            dialog.dismiss()
        }


        imgViewHot.setOnClickListener {
            findNavController().navigate(R.id.mockFragment)
            dialog.dismiss()
        }

        imgViewWeekend.setOnClickListener {
            findNavController().navigate(R.id.mockFragment)
            dialog.dismiss()
        }

        imgViewWhere.setOnClickListener {
            val textToSave = "Куда угодно"
            etWhere.setText(textToSave)
            sharedPref.selectedName = textToSave
            findNavController().navigate(R.id.searchCityFragment)
            dialog.dismiss()
        }

        const1.setOnClickListener {
            etWhere.setText(tvStambul.text)
            findNavController().navigate(R.id.searchCityFragment)
            sharedPref.selectedName = tvStambul.text.toString()
            dialog.dismiss()
        }

        const2.setOnClickListener {
            etWhere.setText(tvSochi.text)
            findNavController().navigate(R.id.searchCityFragment)
            sharedPref.selectedName = tvSochi.text.toString()
            dialog.dismiss()
        }

        const3.setOnClickListener {
            etWhere.setText(tvPhucket.text)
            findNavController().navigate(R.id.searchCityFragment)
            sharedPref.selectedName = tvPhucket.text.toString()
            dialog.dismiss()
        }
        etLocationCity.setText(sharedPref.selectedUserCity)

    }

}