package com.simpletech.ewaysample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.simpletech.ewaysample.ui.theme.EwaySampleTheme
import com.simpletech.ewaysample.viewmodels.MainViewModel
import com.simpletech.ewaysample.views.MainView

class MainActivity : ComponentActivity() {
    private val model by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.initData(this.applicationContext)
        setContent {
            EwaySampleTheme {
            MainView(model = model)
            }
        }
    }
}
