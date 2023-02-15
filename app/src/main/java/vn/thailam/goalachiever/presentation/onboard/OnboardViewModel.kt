package vn.thailam.goalachiever.presentation.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.thailam.goalachiever.data.settings.ISettingsRepo

class OnboardViewModel(
    private val settingsRepo: ISettingsRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnboardState>(OnboardState.Loading)
    val uiState: StateFlow<OnboardState> = _uiState.asStateFlow()

    init {
        checkHasShownOnboard()
    }

    private fun checkHasShownOnboard() = viewModelScope.launch {
        val isFirstTimeUser = settingsRepo.shouldShowOnboard()
        _uiState.value = if (isFirstTimeUser) {
            OnboardState.NotOnboarded
        } else {
            OnboardState.Onboarded
        }
    }
}

sealed class OnboardState {
    object Loading : OnboardState()
    object Onboarded : OnboardState()
    object NotOnboarded : OnboardState()
}