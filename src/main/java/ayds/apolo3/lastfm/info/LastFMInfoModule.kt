package ayds.apolo3.lastfm.info

import ayds.apolo3.lastfm.LastFMInfoService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

internal object LastFMInfoModule {

    private const val LASTFM_URL = "https://ws.audioscrobbler.com/2.0/"
    private val lastFMAPIRetrofit = Retrofit.Builder()
        .baseUrl(LASTFM_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val lastFMInfoAPI = lastFMAPIRetrofit.create(LastFMInfoAPI::class.java)
    private val lastFMToInfoResolver: LastFMToInfoResolver = JsonToInfoResolver()

    val lastFMInfoService: LastFMInfoService = LastFmInfoServiceImpl(
        lastFMInfoAPI,
        lastFMToInfoResolver
    )
}