package ayds.apolo3.lastfm.info

import ayds.apolo.songinfo.moredetails.model.entities.ArtistArticle
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.Exception

interface LastFMToInfoResolver {
    fun getInfoFromExternalData(serviceData: String?): ArtistArticle?
}

private const val DATA_ARTIST = "artist"
private const val DATA_CONTENT = "content"
private const val ARTIST_NAME = "name"
private const val DATA_BIO = "bio"
private const val DATA_URL = "url"

internal class JsonToInfoResolver : LastFMToInfoResolver {

    override fun getInfoFromExternalData(serviceData: String?): ArtistArticle? =
        try {
            serviceData?.getJson()?.getArtistInfo()?.let { item ->
                ArtistArticle(
                    item.getArticleDescription().asString.replace("\\n", "\n"),
                    item.getArticleUrl()
                )
            }
        } catch (e: Exception) {
            null
        }

    private fun String?.getJson(): JsonObject {
        val gson = Gson()
        return gson.fromJson(this, JsonObject::class.java)
    }

    private fun JsonObject.getArtistInfo(): JsonObject {
        return this[ayds.apolo3.lastfm.info.DATA_ARTIST].asJsonObject
    }

    private fun JsonObject.getArticleDescription(): JsonElement {
        val articleDescription = this[ayds.apolo3.lastfm.info.DATA_BIO].asJsonObject
        return articleDescription[ayds.apolo3.lastfm.info.DATA_CONTENT]
    }

    private fun JsonObject.getArticleUrl(): String {
        return this[ayds.apolo3.lastfm.info.DATA_URL].asString
    }
}