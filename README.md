# assignment1

## Task 7

### 7.a
To generate a JDepend report type "mvn jdepend:generate" into the terminal. The report will be output into the Target folder.

### 7.b
In the JDepend report shows the package's dependencies and the packages that use it. In the report it says the cli package depends on the main package but not the other way. This is good because it shows there are no circular dependencies meaning the program has a good layer architecture.

### 7.c
The standalone cli  application .jar is generated every time the project is packaged, this is done by typing "mvn clean package". To run this application in the terminal type "java -jar studentfinder.jar " followed by the student id you wish to read. For example "java -jar studentfiner id42" will print the details of the student with an id of "id42".


## Task 8
My design is currently prone to memory leaks. This is due to the cache always holding a reference to previously used database items. Because the cache has references to the student instances the java garbage collection will not delete these objects. This means if the database is running for a long time the cache can become very large and hog system memory. To solve this I could use WeakReference type or a WeakMap. These types allow garbage collection to delete these objects if memory is they are determined as "weakly reachable". The google Guava library also has good tools for databases and caching that could be used to solve this vulnerability.
