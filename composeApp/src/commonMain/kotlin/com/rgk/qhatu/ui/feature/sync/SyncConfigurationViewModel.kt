package com.rgk.qhatu.ui.feature.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.domain.common.SyncOperation
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.common.SyncStats
import com.rgk.qhatu.domain.usecase.configuration.GetConfigurationStatsUseCase
import com.rgk.qhatu.domain.usecase.configuration.SyncConfigurationUseCase
import com.rgk.qhatu.domain.util.TimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SyncConfigurationViewModel(
    private val getConfigurationStatsUseCase: GetConfigurationStatsUseCase,
    private val syncConfigurationUseCase: SyncConfigurationUseCase
) : ViewModel() {

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    init {
        observeStats()
    }

    private fun observeStats() {
        viewModelScope.launch(Dispatchers.IO) {
            @Suppress("SOME_SONAR_RULE")
            val result = getConfigurationStatsUseCase()
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
            val result = syncConfigurationUseCase(SyncOperation.Download())
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
