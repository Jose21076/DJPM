package ipca.examples.dailynews.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ipca.examples.dailynews.R
import ipca.examples.dailynews.models.Article
import ipca.examples.dailynews.toYYYYMMDD
import ipca.examples.dailynews.ui.theme.DailyNewsTheme
import java.util.Date

@Composable
fun RowArticle(modifier: Modifier = Modifier, article: Article) {
    Row(modifier = modifier) {
        article.urlToImage?.let {
            AsyncImage(
                model = it,
                contentDescription = "image article",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(6.dp)
                    .border(width = 3.dp, color = Color.Black),
                contentScale = ContentScale.Crop
            )
        } ?: run {
            Image(
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(6.dp),
                painter = painterResource(id = R.mipmap.img_place_holder),
                contentDescription = "image article",
                contentScale = ContentScale.Crop,
            )
        }
        Column(modifier = Modifier.fillMaxWidth().background(color = Color.White).border(width = 3.dp, color = Color.Black).padding(5.dp)) {
            Text(text = article.title?: "",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black)
            Text(text = article.description?: "",
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black)
            Row {
                Text(text = article.publishedAt?.toYYYYMMDD()?:"",
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 10.dp)
                )
                Text(modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 10.dp),
                    text = article.author?: "",
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowArticlePreview() {
    DailyNewsTheme {
        RowArticle(article = Article(
            "id",
            "Title",
            "description",
            "url",
            "author",
            "urlToImage",
            "language",
            Date()
        ))
    }
}