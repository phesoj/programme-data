import org.scalatest._

class UrlReaderSpec extends FlatSpec with Matchers {

  val tvShowUrls = Seq(
    "http://api.tvmaze.com/singlesearch/shows?q=hbo&embed=episodes",
    "http://api.tvmaze.com/singlesearch/shows?q=dexter&embed=episodes",
    "http://api.tvmaze.com/singlesearch/shows?q=shameless&embed=episodes",
    "http://api.tvmaze.com/singlesearch/shows?q=mr-robot&embed=episodes",
    "http://api.tvmaze.com/singlesearch/shows?q=prison-break&embed=episodes")

  "A URL Reader" should "read input from a textFile " in {
    val urlReader = new UrlReader
    urlReader.urls should equal (tvShowUrls)
  }
}


