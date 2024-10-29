package ipca.examples.dailynews.models

import ipca.examples.dailynews.parseDate
import org.json.JSONObject
import java.util.Date

class Article (var id: String? = null,
               var title: String? = null,
               var description: String? = null ,
               var url: String? = null,
               var author: String? = null,
               var urlToImage: String? = null,
               var language: String? = null,
               var publishedAt: Date? = null) {

    companion object {
        fun fromJson(json: JSONObject): Article {
            return Article(
                id = json.getString("id"),
                title = json.getString("title"),
                description = json.getString("description").removePrefix("<p>").removeSuffix("<p>"),
                url = json.getString("url"),
                author = json.getString("author"),
                urlToImage = json.getString("image"),
                language = json.getString("language"),
                publishedAt = json.getString("published").parseDate()
            )
        }
    }

    fun descriptionformat(article : Article) : Article{

        return article
    }
}

