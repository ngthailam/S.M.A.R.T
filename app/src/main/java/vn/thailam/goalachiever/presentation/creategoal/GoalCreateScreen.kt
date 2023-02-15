package vn.thailam.goalachiever.presentation.creategoal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import vn.thailam.goalachiever.core.utils.DataLoadState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalCreateScreen(
    navController: NavController,
    vm: GoalCreateViewModel = koinViewModel()
) {
    Column {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        val createLoadState = vm.createLoadState.collectAsState().value

        if (createLoadState == DataLoadState.Success) {
            LaunchedEffect(key1 = Unit) {
                navController.popBackStack()
            }
        }

        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                vm.onNameChanged(newText.text)
            },
            label = { Text(text = "Your Label") },
            placeholder = { Text(text = "Your Placeholder/Hint") },
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Button(onClick = {
            vm.confirmCreate()
        }) {
            Text(text = "Confirm")
        }
    }
}