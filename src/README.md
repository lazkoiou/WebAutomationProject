MAVEN SUITE TEST

To execute 'mvn clean test' on a suite we need to write:

````
mvn clean test -DsuiteFile=sampleSuite.xml
````

ALLURE REPORT

To see the allure results we need to execute:

````
allure serve target/surefire-reports
````