import scalaj.http._

class ProgrammeService {
  def fetch(url: String) = Http(url)
}