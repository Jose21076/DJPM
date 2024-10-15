package com.example.dailynews.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.dailynews.R
import com.example.dailynews.models.Article
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.Date

@Composable
fun HomeView( modifier: Modifier = Modifier) {


    val viewModel = HomeViewModel()
    var articles by viewModel.articles

    LazyColumn(modifier = modifier) {
        itemsIndexed(
            items = articles,
        ){  index, article ->
            //RowArticle(article = article)
            Column {
                Row(modifier = Modifier

                ) {
                    article.urlToImage?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = "image article",
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                        )

                    }?:run{
                        Image(
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp),
                            painter =  painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "image article" )
                    }
                    Column (            modifier = Modifier
                        .weight(2f)){
                        Text(text = article.title!!)
                        Text(text = article.description!!)
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DailyNewsTheme {
        HomeView()
    }
}