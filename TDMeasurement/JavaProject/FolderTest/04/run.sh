#/bin/sh
mvn clean install
cd mlib-ear
mvn embedded-glassfish:run
