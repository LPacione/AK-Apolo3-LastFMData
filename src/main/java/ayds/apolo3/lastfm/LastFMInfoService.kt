package ayds.apolo3.lastfm

import retrofit2.http.Query

interface LastFMInfoService {
    fun getArtistInfo(@Query("artist") artist: String): ArtistArticle?
}