`P`ost `A`pocalyptic 2165 - `S`ystems `U`tility for `R`ecording and `V`irtual `I`ndexing of `V`iolent `E`nemies

Code name: `PA165-SURVIVE`

![Image used for personal inspiration. Image remains property of 4A Games Metro: Last Light](http://www.doublejump.co/wp-content/uploads/2013/04/metrolastlight-header03-600x300.jpg)


Project Description, Diagrams and information view: 
https://github.com/Auronspan/Survive/wiki/home

Team members: 
Aubrey Oosthuizen,
Michal Vinkler,
Irina Serdyukova


## To run:

NOTE: Please ensure that all prerequisites from the TODO.md file has been met before attempting to run the application
<pre>
<code>
1. Open terminal/cmd
2. cd Survive\Survive-POM
3. mvn clean install
</code>
</pre>

#####To additionally run all project test

<pre>
<code>
4. mvn test
</code>
</pre>


####To run the web application on context /pa165 and the rest-server 

<pre>
<code>
1. cd Survive\Survive-POM\Surive-Web
2. mvn tomcat7:run 
</code>
</pre>


####To run the rest-client application


REST cmd client application explanation

NOTE: Please ensure that the rest-server is running through completing above steps

<pre>
<code>
1. Open terminal/cmd
2. Go to project root Survive/Survive-POM/Survive-REST-Client
3. mvn clean package
4. cd target
5. Run the cmd app rest-client-jar-with-dependencies.jar with the correct parameters, see the description below
</code>
</pre>   


You can also use maven exec plugin (from the Survive-REST-Client folder)
   
 <pre>
<code>
 mvn clean compile exec:java -Dexec.args="<command line args>"
</code>
</pre>

usage: [mode] -o [operation] [arguments]...
 -a                  Area Management
 -c <Weapon Class>   Weapon Class: RANGED, MELEE
 -d <Description>    Area description
 -g <range>          Weapon range in meters
 -h                  Print help
 -i <id>             Entity ID
 -m <caliber>        Weapon ammo caliber (mm)
 -n <name>           name of an entity of a choosen mode
 -o <operation>      operation, C, R, U, D, A
 -q <Area Terrain>   Terrain Type: OCEANIC, DESERT, SNOW, JUNGLE, SAVANNA, MOUNTAIN
 -r <rounds>         Number of rounds a weapon holds
 -t <Weapon Type>    Weapon Type: GUN, BALDE, BLUNT, EXPLOSIVE
 -w                  Weapon Management


First of all, you have to choose which [mode] of the application you are going to use. You can choose between the two:

<pre>
<code>
    -a 		area mode
    -w 		weapon mode
</code>
</pre>	
After choosing the mode, you have to specify which [operation] you want to perform on this type of entity. You can choose from 5 operations:

<pre>
<code>
	-o C  	Create an entity
	-o R  	Read an entity
	-o U  	Update an entity
	-o D  	Delete an entity
	-o A  	get All entities
</code>
</pre>	
Based on the chosen operation, you need to specify some mandatory arguments (see the list of mandatory arguments for each operation below):

<pre>
<code>
    -o C 
	-n <name> The name of the new entity
    -o R 
        -i <id> The ID of the entity to be read
    -o U 
        -i <id> The ID of the entity to be updated
    -o D
        -i <id> The ID of the entity to be deleted
    -o A 
		(no mandatory arguments)
</code>
</pre>		
You can also specify optional arguments if needed.

Examples of arguments that can be used when executing REST-Client app:
a weapon (-w): Create (-o C) a weapon of name Automatic shotgun (-n "Automatic shotgun"), weapon class Ranged (-c Ranged)
<pre>
<code>
-w -o C -n "Automatic shotgun" -c Ranged
</code>
</pre>
a weapon (-w): Read (-o R) a weapon of id 1 (-i 1)
<pre>
<code>
-w -o R -i 1
</code>
</pre>
a weapon (-w): Read (-o A) all weapons
<pre>
<code>
-w -o A
</code>
</pre>
a weapon (-w): Delete (-o D) a weapon of id 2 (-i 2) 
<pre>
<code>
-w -o D -i 2
</code>
</pre>
a weapon (-w): Update (-o U) the weapon of id 1 (-i 1) -  name (-n) "Big knife", type (-t) BLADE, description (-d) "New description"
<pre>
<code>
-w -o U -i 1 -n "Big knife" -t BLADE -c Melee -d "New description"
</code>
</pre>
an area (-a): Create (-o C) an area of name Brno (-n Brno),
<pre>
<code>
 -a -o C -n Brno
</code>
</pre>
an area (-a): Read (-o R) an area of id 1 (-i 1)
<pre>
<code>
-a -o R -i 1
</code>
</pre>
The resource base url is by default as http://localhost:8080/pa165/rest/.

The "GET" requests (-o R, -o A) have also its browser counterparts. You can try links as

<pre>
<code>
http://localhost:8080/pa165/rest/weapons/1 
http://localhost:8080/pa165/rest/weapons/all
http://localhost:8080/pa165/rest/areas/1 
http://localhost:8080/pa165/rest/areas/all	
</code>
</pre>
