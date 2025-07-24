package com.rgk.qhatu.feature.setting.presentation.sync.deprecated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.payment.domain.usecase.GetClientPaymentStatsUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncClientPaymentUseCase
import com.rgk.qhatu.utils.TimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SyncClientPaymentViewModel(
    private val getClientPaymentStatsUseCase: GetClientPaymentStatsUseCase,
    private val syncClientPaymentUseCase: SyncClientPaymentUseCase
) : ViewModel() {

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    init {
        observeStats()
    }

    private fun observeStats() {
        viewModelScope.launch(Dispatchers.IO) {
            @Suppress("SOME_SONAR_RULE")
            val result = getClientPaymentStatsUseCase()
                when (result) {
                    is SyncResult.Error -> {
                        _syncState.value = SyncState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<SyncStats> -> {
                        _syncState.value = SyncState.Success(
                            count = result.data.count,
                            lastUpdated = TimeUtils.formatTimestampToDate(result.data.lastUpdated)
                        )
                    }
                }

        }
    }

    fun sync() {
        viewModelScope.launch(Dispatchers.IO) {
            _syncState.value = SyncState.Loading
            @Suppress("SOME_SONAR_RULE")
            val result = syncClientPaymentUseCase(SyncOperation.RemoteToLocal())
            when (result) {
                is SyncResult.Error -> {
                    _syncState.value = SyncState.Error(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    _syncState.value = SyncState.Idle
                    observeStats()
                }
            }
        }
    }
}
