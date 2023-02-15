package vn.thailam.goalachiever.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel
import vn.thailam.goalachiever.core.navigation.Route
import vn.thailam.goalachiever.data.goal.entity.Goal

@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeViewModel = koinViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        GoalList(vm.goals)
        CreateGoalFab(
            onClick = {
                navController.navigate(Route.GOAL_CREATE)
            }
        )
    }
}

@Composable
fun GoalList(goalsStateFlow: StateFlow<List<Goal>>) {
    val goals = goalsStateFlow.collectAsState().value

    if (goals.isNotEmpty()) {
        LazyColumn() {
            items(goals.size) { index ->
                Text(text = goals[index].name)
            }
        }
    }
}

@Composable
fun BoxScope.CreateGoalFab(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp),
        text = {
            Text(text = "Create Goal", color = Color.White)
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                contentDescription = "Create Goal FAB",
                tint = Color.White,
            )
        },
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
    )
}