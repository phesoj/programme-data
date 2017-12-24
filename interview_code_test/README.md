# Realitymine Coding Test

## Introduction 

The goal of the exercise is to download some JSON data relating to TV shows from the URLs provided within tv_show_urls.txt and then parse the JSON for all of the tv shows into one tab seperated file called output_tv_shows.tsv.
This should be written using Scala or Scala using Spark with a test driven development approach. 

* Note: You may use third party libraries in order to complete the task. 

## Task 

1) Read in the file tv_show_urls.txt and iterate the URLs within it, downloading the JSON from each of the URLs

2) Parse each TV shows JSON and extract the following JSON properties:

id
url
name
type
language
status
runtime
premiered
rating_average
summary
network_name
image_original

3) Using the data extracted in the previous step generate a tab seperated text file. You have been provided with an example of the output in the file output_tv_shows.tsv.template (note this is for different TV shows that your task). 
	
The TSV should contain the following columns:  

id, url, name, type, language, status, runtime, premiered, rating_average, summary, network_name, image_original 

* Note ensure your output file is called output_tv_shows.tsv there should be one row for each TV show

4) Provide test cases for your work using scala-test or similar

## Code Submission Requirements

* Scala source code
* Scala test code
* Build that builds, runs tests and runs the application
* Final TSV output file after your have ran your build script
