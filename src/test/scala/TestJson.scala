import scala.io.Source

object TestJson {
  val hboFile = "src/test/resources/hbo.json"
  val hboJson = Source.fromFile(hboFile).getLines().toSeq.head
}