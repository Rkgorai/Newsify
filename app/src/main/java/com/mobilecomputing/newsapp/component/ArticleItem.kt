package com.mobilecomputing.newsapp.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
//import com.google.accompanist.imageloading.rememberImagePainter
import com.mobilecomputing.newsapp.R
import com.mobilecomputing.newsapp.model.news.Article
import java.util.*;
import java.text.SimpleDateFormat
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip

@Composable
fun ArticleItem(article: Article) {

    Box() {
        Column(modifier = Modifier.padding(8.dp)) {
            // Add the image here
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                ,
                model = article.urlToImage ?: "",
                placeholder = painterResource(id = R.drawable.sudoimage),
                error = painterResource(id = R.drawable.sudoimage),
                contentDescription = "The delasign logo",
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.title ?: "", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))
//            Text(text = "Author: ${article.author}", fontSize = 14.sp)

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm a", Locale.getDefault())
            val date: Date? = inputFormat.parse(article.publishedAt)
            val formattedDate: String? = date?.let { outputFormat.format(it) }

            Text(text = "${formattedDate}", fontSize = 14.sp)
//            Text(text = "Source: ${article.source.name}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
            )
        }
    }
}
