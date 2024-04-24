package com.example.mviprojectex1.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mviprojectex1.MainApplication
import com.example.mviprojectex1.data.repo.QuoteRepo
import com.example.mviprojectex1.model.MainAppUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val quoteRepo: QuoteRepo) : ViewModel() {

    private val _mutableStateFlow: MutableStateFlow<MainAppUIState> =
        MutableStateFlow(MainAppUIState.Nothing)
    private val _mutableState: MutableState<MainAppUIState> = mutableStateOf(MainAppUIState.Nothing)
    var mutableStateFlow = _mutableStateFlow

    init {
        getQuoteListData()
    }

    public fun getQuoteListData() {
        mutableStateFlow.value = MainAppUIState.Loading
        viewModelScope.launch {
            quoteRepo.getQuoteList("1")
                .catch { mutableStateFlow.value = MainAppUIState.Error }
                .collect {
                    mutableStateFlow.value = MainAppUIState.Success(it)
                }

        }
    }
}