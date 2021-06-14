package ayds.apolo3.lastfm.info

import ayds.apolo.songinfo.moredetails.model.entities.ArtistArticle
import ayds.apolo3.lastfm.LastFMInfoService
import retrofit2.Response

internal class LastFmInfoServiceImpl(
    private val lastFMInfoAPI: LastFMInfoAPI,
    private val lastFMToInfoResolver: LastFMToInfoResolver
) : LastFMInfoService {

    override fun getCardInfo(artist: String): ArtistArticle? {
        val callResponse = getResponseFromService(artist)
        return lastFMToInfoResolver.getInfoFromExternalData(callResponse.body())
    }

    private fun getResponseFromService(artistName: String): Response<String> =
        lastFMInfoAPI.getArtistInfo(artistName).execute()
}