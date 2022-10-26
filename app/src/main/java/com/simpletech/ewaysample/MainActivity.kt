package com.simpletech.ewaysample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
