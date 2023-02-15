package vn.thailam.goalachiever.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.thailam.goalachiever.core.utils.DataState
import vn.thailam.goalachiever.data.goal.IGoalRepository
import vn.thailam.goalachiever.data.goal.entity.Goal

class HomeViewModel(
    private val goalRepository: IGoalRepository
): ViewModel() {
    init {
        observeAllGoalFlow()
    }

    private val _goalsDataState = MutableStateFlow<DataState<Any>>(DataState.Loading)
    val goalsDataState: StateFlow<DataState<Any>> = _goalsDataState.asStateFlow()

    private val _goals = MutableStateFlow<List<Goal>>(listOf())
    val goals: StateFlow<List<Goal>> = _goals.asStateFlow()

    private fun observeAllGoalFlow() = viewModelScope.launch {
        goalRepository.getAllFlow().collect { goals ->
            _goals.value = goals
        }
    }
}