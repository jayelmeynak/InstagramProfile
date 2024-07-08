package com.example.instagramprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.instagramprofile.ui.theme.InstagramProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            InstagramProfileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { topPadding ->
                    InstagramProfileCard(topPadding, viewModel)
                }
            }
        }
    }
}

