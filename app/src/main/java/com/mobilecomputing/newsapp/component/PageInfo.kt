package com.mobilecomputing.newsapp.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
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
    source: String?,
    context: Context
) {
    val scrollState = rememberScrollState(0)

    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Add the image here
//        AsyncImage(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp),
//            model = urlToImage ?: "",
//            placeholder = painterResource(id = R.drawable.sudoimage),
//            error = painterResource(id = R.drawable.sudoimage),
//            contentDescription = "The delasign logo",
//        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = title ?: "", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Author: $author", fontSize = 16.sp)

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm a", Locale.getDefault())
        val date: Date? = publishedAt?.let { inputFormat.parse(it) }
        val formattedDate: String? = date?.let { outputFormat.format(it) }

        Text(text = "Published At: $formattedDate", fontSize = 16.sp)
        Text(text = "Source: $source", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Description:", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        Text(text ="$description", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Content:", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        Text(text = "$content", fontSize = 18.sp)

        // Add the link here
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Read from Source")
                addStringAnnotation(
                    tag = "URL",
                    annotation = url ?: "",
                    start = 0,
                    end = "Read from Source".length
                )
            }
        }

        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                        ContextCompat.startActivity(context, intent, null)
                    }
            },
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}