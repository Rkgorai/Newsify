package com.mobilecomputing.newsapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
//import com.google.accompanist.imageloading.rememberImagePainter
import com.mobilecomputing.newsapp.R
import com.mobilecomputing.newsapp.model.news.Article

@Composable
fun ArticleItem(article: Article) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title ?: "", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            // Add the image here

            AsyncImage(
                model = article.urlToImage ?: "",
                placeholder = painterResource(id = R.drawable.sudoimage),
                error = painterResource(id = R.drawable.sudoimage),
                contentDescription = "The delasign logo",
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Author: ${article.author}", fontSize = 14.sp)
            Text(text = "Published At: ${article.publishedAt}", fontSize = 14.sp)
            Text(text = "Source: ${article.source.name}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}