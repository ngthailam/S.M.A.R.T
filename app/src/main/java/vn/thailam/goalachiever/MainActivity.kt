package vn.thailam.goalachiever

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import vn.thailam.goalachiever.core.navigation.Route
import vn.thailam.goalachiever.data.core.AppRoomDatabase
import vn.thailam.goalachiever.data.goal.GoalRepository
import vn.thailam.goalachiever.data.goal.IGoalRepository
import vn.thailam.goalachiever.data.settings.ISettingsRepo
import vn.thailam.goalachiever.data.settings.SettingsRepo
import vn.thailam.goalachiever.presentation.creategoal.GoalCreateScreen
import vn.thailam.goalachiever.presentation.creategoal.GoalCreateViewModel
import vn.thailam.goalachiever.presentation.home.HomeScreen
import vn.thailam.goalachiever.presentation.home.HomeViewModel
import vn.thailam.goalachiever.presentation.onboard.OnboardScreen
import vn.thailam.goalachiever.presentation.onboard.OnboardViewModel
import vn.thailam.goalachiever.ui.theme.GoalAchieverTheme

val dataModules = module {
    single<AppRoomDatabase> { AppRoomDatabase.build(androidContext()) }

    factory { get<AppRoomDatabase>().goalDao() }

    factory<ISettingsRepo> { SettingsRepo() }
    factory<IGoalRepository> { GoalRepository(get()) }
}

val viewModelModules = module {
    viewModel { OnboardViewModel(get()) }
    viewModel { GoalCreateViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(dataModules + viewModelModules)
        }

        setContent {
            GoalAchieverTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Route.ONBOARD) {
                    composable(Route.HOME) { HomeScreen(navController) }
                    composable(Route.ONBOARD) { OnboardScreen(navController) }
                    composable(Route.GOAL_CREATE) { GoalCreateScreen(navController) }
                }
            }
        }
    }
}
