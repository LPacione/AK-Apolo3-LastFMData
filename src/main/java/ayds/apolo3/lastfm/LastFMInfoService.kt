package ayds.apolo3.lastfm

interface LastFMInfoService {
    fun getCardInfo(artist: String): Article?
}