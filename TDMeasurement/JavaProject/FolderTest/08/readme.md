Library IS
==============
Create an information system for a public library. The system should keep track of book collections in library departments, library members as well as individual loaned items of every member. The system should be capable of providing information about all the members and books, what a member borrowed and when, who borrowed a certain book and what condition they returned the book in. Take into account that a person can borrow multiple books in a single loan.
Modules
-----
The project contains the following modules:
* <b>api</b> - contains service interfaces and data transfer objects.
* <b>backend</b> - contains entities with service and dao implementations.
* <b>web</b> - contains RESTful services and web implemetation.
* <b>rest</b> - contains the REST client

How to run the project
----
Make sure your database is running, you can set the login data in the file
```
libraryIS/backend/src/main/resources/META-INF/persistence.xml
```
Then you can build the project simply by executing these commands from the directory where you cloned the project
```
cd libraryIS
mvn install
```
To run just enter
```
mvn tomcat6:run
```
The webpages will be available on the url
```
http://localhost:8080/libraryIS
```

To test the REST API, you can use the client
```
cd rest
mvn exec:java
```
