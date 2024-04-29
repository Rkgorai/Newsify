package com.mobilecomputing.newsapp.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.mobilecomputing.newsapp.R
import com.mobilecomputing.newsapp.WebViewActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ArticleItemPage(
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
    val activity = context as? Activity
    val scrollState = rememberScrollState(0)

    BackHandler {
        activity?.finish()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = urlToImage ?: "",
                placeholder = painterResource(id = R.drawable.sudoimage),
                error = painterResource(id = R.drawable.sudoimage),
                contentDescription = "The delasign logo",
                contentScale = ContentScale.FillBounds
            )

            IconButton(
                onClick = { activity?.finish() },
                modifier = Modifier
                    .padding(7.dp)
                    .align(Alignment.TopStart)
                    .background(Color.White.copy(alpha = 0.6f), shape = CircleShape)
                    .zIndex(1f)
                    .absoluteOffset(y = 0.dp)

            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
        }



        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = title ?: "", style = MaterialTheme.typography.headlineLarge)
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(text = "Author: $author", fontSize = 16.sp)
//
//        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
//        val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm a", Locale.getDefault())
//        val date: Date? = publishedAt?.let { inputFormat.parse(it) }
//        val formattedDate: String? = date?.let { outputFormat.format(it) }
//
//        Text(text = "Published At: $formattedDate", fontSize = 16.sp)
//        Text(text = "Source: $source", fontSize = 16.sp)
      //  Spacer(modifier = Modifier.height(8.dp))
      Column(modifier = Modifier.padding(16.dp)) {

          Box(
              modifier = Modifier
                  .border(5.dp, Color.Black)
                  .padding(16.dp)
          ) {
              Column(
                  modifier = Modifier.align(Alignment.Center)
              ) {
                  Text(text = title ?: "", style = MaterialTheme.typography.headlineMedium)

                  Spacer(modifier = Modifier.height(16.dp))
                  Row(
                      modifier = Modifier.fillMaxWidth()
                  ) {
                      if (author != null) {
                          Text(text = "Author: $author", fontSize = 16.sp, modifier = Modifier.weight(1f))
                      }

                      val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                      val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm a", Locale.getDefault())
                      val date: Date? = publishedAt?.let { inputFormat.parse(it) }
                      val formattedDate: String? = date?.let { outputFormat.format(it) }

                      Text(text = "$formattedDate", fontSize = 14.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
                  }
//                Text(text = "Source: $source", fontSize = 14.sp)
                  //Spacer(modifier = Modifier.height(8.dp))
              }
          }
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
                          val intent = Intent(context, WebViewActivity::class.java).apply {
                              putExtra("url", annotation.item)
                          }
                          ContextCompat.startActivity(context, intent, null)
                      }
              },
              modifier = Modifier.padding(top = 16.dp)
          )
      }
    }
}