package com.rgk.qhatu.presentation

sealed class MainAction {
    data object SyncCategoies : MainAction()
    data object SyncBrands : MainAction()
    data object SyncUnitsMeasure : MainAction()
    data object Logout : MainAction()
}