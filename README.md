[![Build Status](https://travis-ci.org/mminella/jsr352-springbatch-and-you.png)](https://travis-ci.org/mminella/jsr352-springbatch-and-you)
### JSR-352, Spring Batch and You
This repository is the home of the code and presentation from the talk JSR-352, Spring Batch, and You by Michael Minella.

#### To run the examples
The Raw JSR version

* From the root of the project, execute `mvn clean install -P jsr` 
* From the target directory execute `java -jar jsr-talk-1.0.0.BUILD-SNAPSHOT.jar logAnalysis inputFile=/tmp/jsr_temp/swk_small.log stagingDirectory=/tmp/jsr_temp/out/`

Spring Version 

* From the root of the project, execute `mvn clean install -P jsr`
* From the target directory execute `java -jar jsr-talk-1.0.0.BUILD-SNAPSHOT.jar logAnalysisWithSpring inputFile=/tmp/jsr_temp/swk_small.log stagingDirectory=/tmp/jsr_temp/out/`

