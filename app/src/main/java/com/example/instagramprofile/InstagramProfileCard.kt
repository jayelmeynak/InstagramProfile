package com.example.instagramprofile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun InstagramProfileCard(topPadding: PaddingValues, viewModel: MainViewModel) {
    val isFollowed = viewModel.isFollowing.observeAsState(initial = true)
    Card(
        modifier = Modifier.padding(top = topPadding.calculateTopPadding()),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.instagram_icon),
                    contentDescription = "instagram icon"
                )

                UserStatistics("Posts", "6,950")
                UserStatistics("Followers", "436M")
                UserStatistics("Following", "76")
            }
            Text(
                text = "Instagram",
                fontSize = 32.sp,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "#YoursToMake",
                fontSize = 14.sp,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "www.facebook.com/",
                fontSize = 14.sp,
                modifier = Modifier.padding(4.dp)
            )
            FollowButton(isFollowed = isFollowed.value) {
                viewModel.changeFollowingState()
            }
        }
    }

}


@Composable
private fun FollowButton(
    isFollowed: Boolean,
    clickListener: () -> Unit
) {
    Button(
        onClick = { clickListener() },
        modifier = Modifier.padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFollowed) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            } else MaterialTheme.colorScheme.primary
        )
    ) {
        val text = if (isFollowed) {
            "Unfollowed"
        } else "Followed"
        Text(
            text = text,
            fontSize = 20.sp
        )

    }
}

@Composable
private fun UserStatistics(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier.height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}