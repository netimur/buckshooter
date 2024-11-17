package com.netimur.buckshooter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.netimur.buckshooter.ui.gamesetting.GameSettingScreen
import com.netimur.buckshooter.ui.gamesetting.GameSettingViewModel
import com.netimur.buckshooter.ui.routes.GameSetting
import com.netimur.buckshooter.ui.theme.BuckshooterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            BuckshooterTheme {
                val viewModel: GameSettingViewModel by viewModels()
                NavHost(navController = navController, startDestination = GameSetting) {
                    composable<GameSetting> {
                        GameSettingScreen(startGame = {}, viewModel = viewModel)
                    }
                }
            }
        }
    }
}