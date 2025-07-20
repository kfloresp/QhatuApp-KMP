package com.rgk.qhatu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val _action = MutableSharedFlow<MainAction>()
    val action = _action.asSharedFlow()

    fun dispatch(action: MainAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }
}
