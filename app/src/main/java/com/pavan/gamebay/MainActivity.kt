package com.pavan.gamebay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.GameBayTheme
import com.pavan.gamebay.feature.gameschedule.presentation.screens.GameScheduleScreen
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.toolbar.AppToolBarUI
import com.pavan.rapidqa.store.RapidQADataStore
import com.pavan.rapidqa.tracer.RapidQATraceRecord
import com.pavan.rapidqa.ui.RapidQATracerActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: RapidQADataStore<Long, RapidQATraceRecord>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameBayTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppToolBarUI()
                    },
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            onClick = {
                                this.startActivity(
                                    RapidQATracerActivity.getInstance(
                                        context = this@MainActivity,
                                        dataStore = dataStore
                                    )
                                )
                            }
                        ) {
                            Text(
                                text = "Network Inspector",
                                style = MaterialTheme.typography.bodySmall,
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Icon(
                                contentDescription = "Access Network Inspector",
                                painter = painterResource(R.drawable.baseline_settings_ethernet_24)
                            )
                        }
                    }
                ) { innerPadding ->
                    GameScheduleScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

