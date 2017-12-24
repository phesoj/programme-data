import java.io.File

import org.apache.spark.sql.{DataFrame, SparkSession}

class ReportGenerator {
 lazy val spark = SparkSession
    .builder()
    .master("local")
    .appName("Spark SQL basic example")
    .getOrCreate()

  val programmeService = new ProgrammeService
  val urlReader = new UrlReader

  def retrieveJson = urlReader.urls.map(url => programmeService.fetch(url).asString.body)

  def tvShowsTable = {

    val programmesTable = spark.read.json(spark.sparkContext.parallelize(retrieveJson))

    val initialCols = programmesTable.select(
      "id",
      "url",
      "name",
      "type",
      "language",
      "status",
      "runtime",
      "premiered",
      "rating.average",
      "summary",
      "image.original")

    val networkNameCol = programmesTable.select("id", "network.name").withColumnRenamed("name", "network_name")
    val imageOriginalCol = programmesTable.select("id", "image.original").withColumnRenamed("original", "image_original")

    val renamedCols = initialCols
      .withColumnRenamed("average", "rating_average")
      .join(networkNameCol, "id")
      .join(imageOriginalCol, "id")

    renamedCols
  }

  def generateReport = {
    saveAsTsv(tvShowsTable, "output_tv_shows.tsv")
    spark.sparkContext.stop()
  }

  private def saveAsTsv(df: DataFrame, tsvPath: String): Unit = {
    val tmpDirectory = "tv_shows"
    df.coalesce(1)
      .write
      .format("com.databricks.spark.csv")
      .option("header", true)
      .option("delimiter", "\t")
      .save(tmpDirectory)

    val dir = new File(tmpDirectory)
    val tsvFile = dir.listFiles.filter(file => file.getName.endsWith(".csv"))
    tsvFile.head.renameTo(new File(tsvPath))
    dir.listFiles.foreach(file => file.delete)
    dir.delete
  }
}
