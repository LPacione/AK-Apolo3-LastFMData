package ayds.apolo3.lastfm

import ayds.apolo.songinfo.moredetails.model.entities.ArtistArticle
import retrofit2.http.Query

interface LastFMInfoService {
    fun getCardInfo(@Query("artist") artist: String): ArtistArticle?
}