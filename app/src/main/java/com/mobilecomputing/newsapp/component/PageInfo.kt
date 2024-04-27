package com.mobilecomputing.newsapp.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.mobilecomputing.newsapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun PageArticleItem(
    title: String?,
    content: String?,
    description: String?,
    author: String?,
    url: String?,
    urlToImage: String?,
    publishedAt: String?,
    source: String?
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Add the image here
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = urlToImage ?: "",
                placeholder = painterResource(id = R.drawable.sudoimage),
                error = painterResource(id = R.drawable.sudoimage),
                contentDescription = "The delasign logo",
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title ?: "", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Author: $author", fontSize = 14.sp)

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm a", Locale.getDefault())
            val date: Date? = publishedAt?.let { inputFormat.parse(it) }
            val formattedDate: String? = date?.let { outputFormat.format(it) }

            Text(text = "Published At: $formattedDate", fontSize = 14.sp)
            Text(text = "Source: $source", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}