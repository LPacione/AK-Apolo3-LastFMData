package ayds.apolo3.lastfm

interface Article {
    val description: String
    val infoURL: String
    var sourceLogoURL: String
}

data class ArtistArticle(
    override var description: String,
    override val infoURL: String,
    override var sourceLogoURL: String
) : Article

object EmptyArticle:Article{
    override var description = ""
    override val infoURL = ""
    override var sourceLogoURL = ""
}

