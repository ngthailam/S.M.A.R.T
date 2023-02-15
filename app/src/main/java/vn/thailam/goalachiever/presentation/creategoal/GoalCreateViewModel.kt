package vn.thailam.goalachiever.presentation.creategoal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.thailam.goalachiever.core.utils.DataLoadState
import vn.thailam.goalachiever.data.goal.IGoalRepository
import vn.thailam.goalachiever.data.goal.entity.Goal

class GoalCreateViewModel(
    private val goalRepository: IGoalRepository
) : ViewModel() {

    private val _createLoadState = MutableStateFlow<DataLoadState>(DataLoadState.Loading)
    val createLoadState: StateFlow<DataLoadState> = _createLoadState.asStateFlow()

    var goal: Goal = Goal(name = "")

    fun onNameChanged(text: String) {
        goal = goal.copy(name = text)
    }

    fun confirmCreate() = viewModelScope.launch {
        try {
            goalRepository.insert(goal)
            _createLoadState.value = DataLoadState.Success
        } catch (t: Throwable) {
            _createLoadState.value = DataLoadState.Error
        }
    }
}