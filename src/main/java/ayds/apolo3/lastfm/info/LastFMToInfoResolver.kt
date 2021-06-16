package ayds.apolo3.lastfm.info

import ayds.apolo3.lastfm.Article
import ayds.apolo3.lastfm.ArtistArticle
import ayds.apolo3.lastfm.EmptyArticle
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.Exception

interface LastFMToInfoResolver {
    fun getInfoFromExternalData(serviceData: String?): Article?
}

private const val DATA_ARTIST = "artist"
private const val DATA_CONTENT = "content"
private const val DATA_BIO = "bio"
private const val DATA_URL = "url"


internal class JsonToInfoResolver : LastFMToInfoResolver {

    override fun getInfoFromExternalData(serviceData: String?): Article? =
        try {
            serviceData?.getJson()?.getArtistInfo()?.let { item ->
                ArtistArticle(
                    item.getArticleDescription().asString.replace("\\n", "\n"),
                    item.getArticleUrl(), sourceLogoURL()
                )
            }
        } catch (e: Exception) {
            EmptyArticle
        }

    private fun String?.getJson(): JsonObject {
        val gson = Gson()
        return gson.fromJson(this, JsonObject::class.java)
    }

    private fun JsonObject.getArtistInfo(): JsonObject {
        return this[DATA_ARTIST].asJsonObject
    }

    private fun JsonObject.getArticleDescription(): JsonElement {
        val articleDescription = this[DATA_BIO].asJsonObject
        return articleDescription[DATA_CONTENT]
    }

    private fun JsonObject.getArticleUrl(): String {
        return this[DATA_URL].asString
    }

    private fun sourceLogoURL():String {
        return IMAGE_URL
    }

    companion object {
        const val IMAGE_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Lastfm_logo.svg/320px-Lastfm_logo.svg.png"
    }
}