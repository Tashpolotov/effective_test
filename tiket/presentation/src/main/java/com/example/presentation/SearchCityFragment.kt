package com.example.presentation

import android.app.DatePickerDialog
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core_domain.model.search.TicketOfferResponse
import com.example.core_utils.SharedPref
import com.example.core_utils.base.BaseFragment
import com.example.presentation.databinding.FragmentSearchCityBinding
import com.example.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class SearchCityFragment : BaseFragment(R.layout.fragment_search_city) {

    private val binding by viewBinding(FragmentSearchCityBinding::bind)
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var sharedPref: SharedPref

    override fun initialize() {
        viewModel.loadSearch()
        sharedPref = SharedPref(requireContext())
        binding.etWhere.text = sharedPref.selectedName.toString()
        if (sharedPref.selectedUserCity != null) {
            binding.etLocationCity.setText(sharedPref.selectedUserCity)
        }
        binding.btnGo.setOnClickListener {
            findNavController().navigate(R.id.allTicketFragment)
        }
        binding.imgSwap.setOnClickListener {
            val textFrom = binding.etWhere.text.toString()
            val textTo = binding.etLocationCity.text.toString()

            binding.etWhere.setText(textTo)
            binding.etLocationCity.setText(textFrom)
        }
        initTextView()
    }

    override fun initSubscribers() {
        viewModel.search.collectUIState(
            state = {
                binding.progressBar.visibility = View.VISIBLE
            },
            onSuccess = { response ->
                binding.progressBar.visibility = View.GONE
                binding.cardContainer.visibility = View.VISIBLE
                binding.scrollView.visibility = View.VISIBLE
                binding.cardView.visibility = View.VISIBLE
                binding.btnGo.visibility = View.VISIBLE
                response.let { populateViews(it) }
            }
        )
    }

    private fun populateViews(response: TicketOfferResponse) {
        val tickets = response.tickets_offers
        val timeRanges = tickets.get(0).time_range?.joinToString(", ")
        val timeRanges1 = tickets.get(1).time_range?.joinToString(", ")
        val timeRanges2 = tickets.get(2).time_range?.joinToString(", ")

        binding.apply {
            tvCategoryLine.text = tickets.getOrNull(0)?.title
            tvCategoryLineBlue.text = tickets.getOrNull(1)?.title
            tvCategoryLineWhite.text = tickets.getOrNull(2)?.title

            tvPriceTiket.text = tickets.getOrNull(0)?.price?.value.toString() + " \u20BD"
            tvPriceTiketBlue.text = tickets.getOrNull(1)?.price?.value.toString() + " \u20BD"
            tvPriceTiketWhite.text = tickets.getOrNull(2)?.price?.value.toString() + " \u20BD"

            tvTimeRed.text = timeRanges
            tvTimeBlue.text = timeRanges1
            tvTimeWhite.text = timeRanges2
        }
    }

    private fun initTextView(){
        val currentDate = SimpleDateFormat("d MMM E", Locale.getDefault()).format(Date())
        binding.tvDate.text = currentDate

        binding.linearContDate.setOnClickListener {
            openDatePicker(setDate = true)
        }

        binding.tvBack.setOnClickListener {
            openDatePicker(setDate = false)
        }

    }
    private fun openDatePicker(setDate: Boolean){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            if (setDate) {
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth)
                val formattedDate = SimpleDateFormat("d MMM, E", Locale.getDefault()).format(selectedDate.time)
                binding.tvDate.text = formattedDate
            }
        }, year, month, dayOfMonth)

        datePickerDialog.show()
    }
}