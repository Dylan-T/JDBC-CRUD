# assignment1

## Task 7

### 7.a
To generate a JDepend report type "mvn JDepend:Generate" into the terminal. The report will be output into the Target folder.

### 7.b
In the JDepend report shows the package's dependencies and the packages that use it. In the report it says it is used by the cli package, however it does not depend on the cli. This is shows correct dependency for a UI and domain layer interaction.

### 7.c
The standalone cli  application .jar is generated every time the project is packaged, this is done by typing "mvn package". To run this application in the terminal type "java -jar jarname.jar " followed by the student id you wish to read.


## Task 8

My design is currently prone to memory leaks. This is due to the cache always holding a reference to previously used databse items. Because the cache has references to them the java garbage collection will not delete these objects. This means if the database is running for a long time the cache can become very large. To solve this I could use WeakReference type or a WeakMap. These types allow garbage collection to delete these objects if memory is required. The google Guava library also has good tools for databases and caching that could solve this vulnerability.
