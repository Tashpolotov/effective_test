package com.example.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core_domain.model.mainscreen.OfferResponse
import com.example.core_domain.model.search.TicketOfferResponse
import com.example.core_domain.usecase.MainUseCase
import com.example.core_utils.Resource
import com.example.core_utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: MainUseCase):BaseViewModel() {

    private val _search = MutableStateFlow<Resource<TicketOfferResponse>>(Resource.Empty())
    val search = _search.asStateFlow()


    fun loadSearch(){
        viewModelScope.launch {
            useCase.getSearch().collectData(_search)
        }
    }
}