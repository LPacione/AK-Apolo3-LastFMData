package ayds.apolo3.lastfm.info

import ayds.apolo3.lastfm.FullCard
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.Exception

interface LastFMToInfoResolver {
    fun getInfoFromExternalData(serviceData: String?): FullCard?
}

private const val DATA_ARTIST = "artist"
private const val DATA_CONTENT = "content"
private const val DATA_BIO = "bio"
private const val DATA_URL = "url"
private const val SOURCE = 1


internal class JsonToInfoResolver : LastFMToInfoResolver {

    override fun getInfoFromExternalData(serviceData: String?): FullCard? =
        try {
            serviceData?.getJson()?.getArtistInfo()?.let { item ->
                FullCard(
                    item.getArticleDescription().asString.replace("\\n", "\n"),
                    item.getArticleUrl(), SOURCE
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
        return this[DATA_ARTIST].asJsonObject
    }

    private fun JsonObject.getArticleDescription(): JsonElement {
        val articleDescription = this[DATA_BIO].asJsonObject
        return articleDescription[DATA_CONTENT]
    }

    private fun JsonObject.getArticleUrl(): String {
        return this[DATA_URL].asString
    }
}