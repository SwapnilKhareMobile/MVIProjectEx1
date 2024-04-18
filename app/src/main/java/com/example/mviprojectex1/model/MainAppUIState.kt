package com.example.mviprojectex1.model

sealed class MainAppUIState {
    data object Nothing:MainAppUIState()
    data object Loading:MainAppUIState()
    data object Error:MainAppUIState()
    class Success(val list: List<Result>?) : MainAppUIState()
}