package vn.thailam.goalachiever.presentation.onboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import vn.thailam.goalachiever.core.navigation.Route
import vn.thailam.goalachiever.presentation.onboard.composable.OnboardPagerPage

@Composable
fun OnboardScreen(
    navController: NavController,
    vm: OnboardViewModel = koinViewModel(),
) {
    val uiState by vm.uiState.collectAsState()
    when (uiState) {
        OnboardState.Loading -> LoadingState()
        OnboardState.NotOnboarded -> OnboardPager(
            onFinish = { navController.navigate(Route.HOME) }
        )
        OnboardState.Onboarded -> LaunchedEffect(key1 = Unit) {
            navController.navigate(Route.HOME)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardPager(onFinish: () -> Unit) {
    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    val animateToPage = { page: Int ->
        scope.launch {
            state.animateScrollToPage(page)
        }
    }

    HorizontalPager(count = 3, state = state) { page ->
        when (page) {
            0 -> {
                OnboardPagerPage(
                    title = "Page 1",
                    onContinue = {
                        animateToPage(1)
                    }
                )
            }
            1 -> {
                OnboardPagerPage(
                    title = "Page 2",
                    onContinue = {
                        animateToPage(2)
                    }
                )
            }
            2 -> {
                OnboardPagerPage(
                    title = "Page 3",
                    onContinue = onFinish
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(4.dp))
    DotsIndicator()
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun DotsIndicator() {
    LazyRow(modifier = Modifier.wrapContentSize()) {
        items(3) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
            )
        }
    }
}