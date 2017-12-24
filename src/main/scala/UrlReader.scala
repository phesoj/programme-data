import scala.io.Source

class UrlReader {
  val urlFile = "src/main/resources/tv_show_urls.txt"
  val urls = Source.fromFile(urlFile).getLines().toSeq
}