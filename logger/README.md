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



