package com.example.instagramprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
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
                    val models = viewModel.models.observeAsState(listOf())
                    LazyColumn {
                        items(models.value) {
                            InstagramProfileCard(
                                topPadding = topPadding,
                                model = it) {
                                viewModel.changeFollowingStatus(it)
                            }
                        }
                    }
                }
            }
        }
    }
}


