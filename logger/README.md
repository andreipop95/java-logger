# Logger

### How to run locally
1. `./gradlew clean build`
2. Running the demo `./gradlew bootRun`

### Package the library
1. Update the library version in `build.gradle.kts`
2. Publish to maven local `./gradlew publishToMavenLocal`

### Usage in other projects
Add library to dependencies
````
dependencies {
	implementation("com.basic:logger:0.0.2-SNAPSHOT")
}
````

### Features to be implemented in next releases
1. **Pattern** - add configurable logging patterns
2. **Configuration** file - the logger should also be configurable via config files - not only at run time
   This could include options such as log format, log levels, log destination (console, file, email, serverAPI), log rotation, log retention policies.
3. **Multiple writers of the same type** - in the current implementation, there's only one LogWriter of a type. The user should have the possibility of adding and configuring multiple writers of a given type (ex: FILE, EMAIL, SERVER API)
4. **Logging metadata** - add logging metadata: class name, method name, thread name, process id
5. **Performance** - user parameterized log messages, message buffering and batching in order to minimise I/O operations
6. **Log filtering** - log filtering mechanism that allows the user to include / exclude logs based on a certain criteria
7. **Log formatting** - log messages can be written in various formats like JSON or XML

### Improvements to simplify Big O notation of every method
1. **String manipulation** - use StringBuilder instead of concatenating Strings 
2. **Output buffers** - use print buffers when adding "write to file" functionality

### Management in a multi-threading environment
1. **Synchronisation** - use synchronized blocks or methods - prevent race conditions 
2. **Use atomic operations** - atomic operations and atomic variables
3. **Concurrent data structures** - currently using HashMap in the BasicLogger class - use ConcurrentHashMap
