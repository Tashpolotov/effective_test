package com.example.presentation.viewmodel

import android.text.InputFilter
import androidx.lifecycle.viewModelScope
import com.example.core_domain.model.mainscreen.OfferResponse
import com.example.core_domain.usecase.MainUseCase
import com.example.core_utils.Resource
import com.example.core_utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase:MainUseCase):BaseViewModel(){

    private val _mainScreen = MutableStateFlow<Resource<OfferResponse>>(Resource.Empty())
    val mainScreen = _mainScreen.asStateFlow()

    private val _cyrillicFilter = MutableStateFlow<InputFilter?>(null)
    val cyrillicFilter = _cyrillicFilter.asStateFlow()

    init {
        _cyrillicFilter.value = InputFilter { source, _, _, _, _, _ ->
            val regex = Regex("[а-яА-ЯёЁ\\s]+")
            if (source.toString().matches(regex)) {
                null
            } else {
                ""
            }
        }
    }

    fun updateCyrillicFilter(filter: InputFilter?) {
        _cyrillicFilter.value = filter
    }

    fun loadMainScreen(){
        viewModelScope.launch {
            useCase.getMainScreen().collectData(_mainScreen)
        }
    }
}