package com.example.instagramprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.instagramprofile.ui.theme.InstagramProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            InstagramProfileTheme {
                MainContent(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(viewModel: MainViewModel){
    Scaffold(modifier = Modifier.fillMaxSize()) { topPadding ->
        val models = viewModel.models.observeAsState(listOf())
        LazyColumn {
            items(models.value, key = {it.id}) { model ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if (it == SwipeToDismissBoxValue.EndToStart) {
                            viewModel.delete(model)
                        }
                        return@rememberSwipeToDismissBoxState true
                    }
                )
                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {
                        SwipeBackground(topPadding)
                    }) {
                    InstagramProfileCard(
                        topPadding = topPadding,
                        model = model
                    ) {
                        viewModel.changeFollowingStatus(it)
                    }
                }
            }
        }
    }
}

@Composable
fun SwipeBackground(topPadding: PaddingValues){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = topPadding.calculateTopPadding(),
                horizontal = 16.dp
            )
            .background(color = Color.Red.copy(alpha = 0.5f)),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Delete item", fontSize = 24.sp,
            color = Color.White
        )
    }
}