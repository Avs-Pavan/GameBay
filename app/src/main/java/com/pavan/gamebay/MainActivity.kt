package com.pavan.gamebay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.GameBayTheme
import com.pavan.gamebay.feature.gameschedule.presentation.screens.GameScheduleScreen
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.toolbar.AppToolBarUI
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameBayTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppToolBarUI()
                    }
                ) { innerPadding ->
                    GameScheduleScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

