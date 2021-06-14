package ayds.apolo3.lastfm

interface Article {
    val description: String
    val infoURL: String
    var sourceLogoURL: String
    var isLocallyStoraged: Boolean
}

data class ArtistArticle(
    override var description: String,
    override val infoURL: String,
    override var sourceLogoURL: String,
    override var isLocallyStoraged: Boolean = false
) : Article

object EmptyArticle : Article {
    override val description: String = ""
    override val infoURL: String = ""
    override var sourceLogoURL: String = ""
    override var isLocallyStoraged: Boolean = false
}

