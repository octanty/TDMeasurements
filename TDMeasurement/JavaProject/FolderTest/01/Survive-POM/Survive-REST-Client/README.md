REST cmd client application explanation


NOTE: Please ensure that all prerequisites from the TODO.md and README.md file has been met before attempting to run the application

1. Open terminal/cmd 

2. Go to project root Survive/Survive-POM/Survive-REST-Client

3. mvn clean package

4. cd target  

5. Run the cmd app rest-client-jar-with-dependencies.jar with the correct parameters, see the description below
   
   You can also use maven exec plugin (from the Survive-REST-Client folder)
   
   mvn clean compile exec:java -Dexec.args="<command line args>"


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

    -a 		area mode
    -w 		weapon mode
	
After choosing the mode, you have to specify which [operation] you want to perform on this type of entity. You can choose from 5 operations:

    -o C  	Create an entity
    -o R  	Read an entity
	-o U  	Update an entity
	-o D  	Delete an entity
	-o A  	get All entities
	
Based on the chosen operation, you need to specify some mandatory arguments (see the list of mandatory arguments for each operation below):

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
		
You can also specify optional arguments if needed.

Examples:
a weapon (-w): Create (-o C) a weapon of name Automatic shotgun (-n "Automatic shotgun"), weapon class Ranged (-c Ranged)
$ java -jar rest-client-jar-with-dependencies.jar -w -o C -n "Automatic shotgun" -c Ranged

a weapon (-w): Read (-o R) a weapon of id 1 (-i 1)
$ java -jar rest-client-jar-with-dependencies.jar -w -o R -i 1

a weapon (-w): Read (-o A) all weapons
$ java -jar rest-client-jar-with-dependencies.jar -w -o A

a weapon (-w): Delete (-o D) a weapon of id 2 (-i 2) 
$ java -jar rest-client-jar-with-dependencies.jar -w -o D -i 2

a weapon (-w): Update (-o U) the weapon of id 1 (-i 1) -  name (-n) "Big knife", type (-t) BLADE, description (-d) "New description"
-w -o U -i 1 -n "Big knife" -t BLADE -c Melee -d "New description"

an area (-a): Create (-o C) an area of name Brno (-n Brno),
$ java -jar rest-client-jar-with-dependencies.jar -a -o C -n Brno

an area (-a): Read (-o R) an area of id 1 (-i 1)
$ java -jar rest-client-jar-with-dependencies.jar -a -o R -i 1

The resource base url is by default as http://localhost:8080/pa165/rest/.

The "GET" requests (-o R, -o A) have also its browser counterparts. You can try links as

http://localhost:8080/pa165/rest/weapons/1 
http://localhost:8080/pa165/rest/weapons/all
http://localhost:8080/pa165/rest/areas/1 
http://localhost:8080/pa165/rest/areas/all	