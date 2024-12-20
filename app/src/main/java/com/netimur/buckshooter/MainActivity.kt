package com.netimur.buckshooter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.netimur.buckshooter.ui.gameprocess.GameProcessScreen
import com.netimur.buckshooter.ui.gamesetting.GameSettingScreen
import com.netimur.buckshooter.ui.routes.GameProcess
import com.netimur.buckshooter.ui.routes.GameSetting
import com.netimur.buckshooter.ui.theme.BuckshooterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            BuckshooterTheme {
                NavHost(navController = navController, startDestination = GameSetting) {
                    composable<GameSetting> {
                        GameSettingScreen(
                            startGame = {
                                navController.navigate(GameProcess)
                            },
                            viewModel = hiltViewModel()
                        )
                    }

                    composable<GameProcess> {
                        GameProcessScreen(viewModel = hiltViewModel())
                    }
                }
            }
        }
    }
}