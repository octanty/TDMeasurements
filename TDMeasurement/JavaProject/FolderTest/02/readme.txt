https://bitbucket.org/pa165/fleetmanagement/wiki/Installation

## Modules ##

The project contains the following modules:

* PA165-FleetManagement-api - contains service interfaces and data transfer objects.
* PA165-FleetManagement-impl - contains entities with service and dao implementations.
* PA165-FleetManagement-web - contains RESTful services and web implemetation.
* PA165-FleetManagement-client - contains the REST client

## How to run the project ##

Make sure your database is running, you can set the login data in the file
* PA165-FleetManagement/PA165-FleetManagement-impl/src/main/resources/META-INF/persistence.xml

Then you can build the project simply by executing these commands from the directory where you cloned the project
* cd PA165-FleetManagement
* mvn install

To run just enter:
* mvn tomcat6:run

The webpages will be available on the url
* http://localhost:8080/PA165-FleetManagement

To test the REST API, you can use the client:
* cd PA165-FleetManagement-rest
* mvn exec:java