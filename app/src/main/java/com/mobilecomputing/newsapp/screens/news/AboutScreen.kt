package com.mobilecomputing.newsapp.screens.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mobilecomputing.newsapp.R

@Composable
fun AboutScreen() {
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(40.dp, 100.dp)
        ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = rememberAsyncImagePainter(model = R.drawable.newicon),
                contentDescription = "Your image description",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape))

               Spacer(modifier = Modifier.padding(20.dp))
            Text(text = "You can find this whole source code on",
                fontSize = 13.sp)
            Text(text = "github.com/Rkgorai/NewsApp",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,

                )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(text = "Made by")
            Text(text = "MUKUL and RAHUL",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                )


        }

    }
}