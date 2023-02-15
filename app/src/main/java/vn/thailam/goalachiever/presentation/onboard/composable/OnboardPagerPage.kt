package vn.thailam.goalachiever.presentation.onboard.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import vn.thailam.goalachiever.core.utils.SimpleCallback

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnboardPagerPage(
    title: String,
    onContinue: SimpleCallback? = null,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = title)
        AnimatedVisibility(
            visible = true, // TODO: animation not working yet
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd),
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp),
                onClick = {
                    onContinue?.invoke()
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Outlined.ArrowForward,
                    contentDescription = "Add button"
                )
            }
        }
    }
}
