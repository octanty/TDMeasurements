![logo](https://raw.github.com/ibek/music-library/master/mlib-web/src/main/webapp/resources/logo.png)
=============

#Instructions

    1. mvn clean install
    2. cd mlib-ear
    3. start Derby DB at jdbc:derby://localhost:1527/pa165 with following creadentials: 
          username: pa165
          password: pa165
    4. mvn embedded-glassfish:run
    5. open http://localhost:8080/pa165

#REST client
Can be found in github repository [music-library-client](https://github.com/tomparys/music-library-client).

# Problems with Java versions and other
1. In case of an error while trying to connect to the database, try [this](http://stackoverflow.com/questions/21154400/unable-to-start-derby-database-from-netbeans-7-4), or specifically adding this to the java.policy file

grant {
        	permission java.net.SocketPermission "localhost:1527", "listen,resolve";
	};

2. In case of an error *java.net.BindException: Cannot assign requested address*, try adjusting your /etc/hosts file so that it contains localhost defined as 127.0.0.1 and not other.
