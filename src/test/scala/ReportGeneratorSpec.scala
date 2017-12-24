import java.io.File

import org.scalatest._

import scala.collection.immutable.Stream.Empty
import scala.io.Source
class ReportGeneratorSpec extends FlatSpec with Matchers {

  val reportGenerator = new ReportGenerator

  "A Report Generator" should "retrieve 5 json blobs" in {
    reportGenerator.retrieveJson.size should equal(5)
    reportGenerator.retrieveJson.head should equal(TestJson.hboJson)
  }

  "A Report Generator" should "select the appropriate fields" in {
    reportGenerator.tvShowsTable.select("image_original") shouldNot be (Empty)
    reportGenerator.tvShowsTable.select("network_name") shouldNot be (Empty)
    reportGenerator.tvShowsTable.select("rating_average") shouldNot be (Empty)
  }

  "A Report Generator" should "Generate a report" in {
    reportGenerator.generateReport

    val exampleTsvPath = "src/test/resources/output_tv_shows.tsv"
    val exampleTsvFile = Source.fromFile(exampleTsvPath)

    val dir = new File("./")
    val tsvFile = dir.listFiles.filter(file => file.getName.endsWith(".tsv"))

    tsvFile.head.getName should be ("output_tv_shows.tsv")
    val generatedFile = Source.fromFile(tsvFile.head.getAbsolutePath)

    generatedFile.toSeq should equal (exampleTsvFile.toSeq)
  }
}


