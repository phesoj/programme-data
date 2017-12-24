import org.scalatest._

class ProgrammeServiceSpec extends FlatSpec with Matchers {

  "A ProgrammeService" should "fetch data from passed url" in {
    val programmeService = new ProgrammeService
    val hboQuery = "http://api.tvmaze.com/singlesearch/shows?q=hbo&embed=episodes"
    programmeService.fetch(hboQuery).asString.body should equal(TestJson.hboJson)
  }
}