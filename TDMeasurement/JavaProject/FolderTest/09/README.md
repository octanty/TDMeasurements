#Bottler Liquor registry

This project
* bottler-api
* bottler-business
* bottler-cmd
* bottler-web

## Project specification 
*(copied from is.muni.cz)*

Everybody knows why prohibition went into effect in Czech Republic some time ago. In order to prevent this in the future there is a need for liquor bottles registry. Such a registry would keep track of all the liquor bottles that are, or will be, on the market. For each bottle there is a need to keep record of its producer. 			
			
All the bottles 	currently on the market must be gradually analyzed and for each bottle it must be determined if the bottle is toxic or not. In order to return the bottle to the market, it cannot be toxic and 	it must re-stamp with new kind of tax stamp. Newly-manufactured bottles carry the new stamps automatically and thus can be introduced to the market. 			
			
The customers are interested in how many bottles of which liquor type are currently in particular stores. Police, on the other hand, is interested in the statistics of the toxic bottles for each store and producer.



##System requirements
 
Your system must meet following requirements to run bottler

 * Java 1.7
 * Maven 3
 * running Derby database listening on http://localhost:1527
 * free port localhost:8080 (otherwise embedded tomcat cant bind to that port)
 
 

##bottler deployment

If you don't want to execute tests during maven operations, include `-p noTest` attribute. For example mvn install without tests: `mvn -p noTest`

###installation

```
#!bash
git clone https://bitbucket.org/ludvicekj/bottler.git
cd bottler

mvn install

```

All following run commands expect succesfull `mvn install` in bottler root.

###run web application

```
#!bash
#expecting you are in bottler root folder
cd bottler-web
mvn tomcat7:run
```

###run command line client

```
#!bash
#expecting you are in bottler root folder
cd bottler-cmd
java -jar bottler-cmd-jar-with-dependencies.jar #add -h param to print help
```

To complete all project requirements, maven exec plugin must be used.
If you wish to run cmd client using maven, just use this command:

```
#!bash

mvn exec:java \
	-Dexec.mainClass="cz.muni.fi.pa165.bottler.client.cmd.Main" \
	-Dexec.args=" -h"
	# -h or any other arguments you need
```
 
 
 

