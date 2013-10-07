[![Build Status](https://travis-ci.org/mminella/jsr352-springbatch-and-you.png)](https://travis-ci.org/mminella/jsr352-springbatch-and-you)
### JSR-352, Spring Batch and You
This repository is the home of the code and presentation from the talk JSR-352, Spring Batch, and You by Michael Minella.

The source of the data for this example must be downloaded via BitTorent.  You can find more information about the input file here: [Star Wars Kid: The Data Dump](http://waxy.org/2008/05/star_wars_kid_the_data_dump/)

To run the samples, you'll need to install the GeoLite2 database for the geolocation to work.  You can download the database here: [GeoLite2 Country Database](http://dev.maxmind.com/geoip/geoip2/geolite2/).  The paths for the database are currently hardcoded in the GeocodingItemProcessor to /usr/local/share/GeoIP/GeoLite2-Country.mdb.  You can either drop the database there or change the path to meet your needs.

The other place paths are currently hard coded is in the ReportBatchlet.  The output of the job will be written to /tmp/jsr_temp/output/index.html.  If this doesn't work for you, feel free to change the paths as you need.  Wherever you generate the output file at, you'll need to copy the javascript libraries over before invoking the python command to serve the site.

#### To run the examples
By Default this will run using HSQLDB.  The configuration provided also offers MySql as well.

The Raw JSR version

* From the root of the project, execute `mvn clean install -P jsr` 
* From the target directory execute `java -jar jsr-talk-1.0.0.BUILD-SNAPSHOT.jar logAnalysis inputFile=/tmp/jsr_temp/swk_small.log stagingDirectory=/tmp/jsr_temp/out/`

Spring Version 

* From the root of the project, execute `mvn clean install -P jsr`
* For the SystemCommandTasklet, we need to be sure that the staging directory has been created (the JSR batchlet checks and creates it if it is not).
* From the target directory execute `java -jar jsr-talk-1.0.0.BUILD-SNAPSHOT.jar logAnalysisWithSpring inputFile=/tmp/jsr_temp/swk_small.log stagingDirectory=/tmp/jsr_temp/out/`

Both versions

* To view the report, you'll need to copy the js directory into the same directory your report is generated (in this case it's /tmp/jsr_temp/output).
* You can then use python to launch a simple server with the command: `python -m SimpleHTTPServer 8008`