plugins {
	`java-library`
	`maven-publish`
	java
	id("org.springframework.boot") version "3.1.1-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.basic"
version = "0.0.2-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			versionMapping {
				usage("java-api") {
					fromResolutionOf("runtimeClasspath")
				}
				usage("java-runtime") {
					fromResolutionResult()
				}
			}

			from(components["java"])

			groupId = project.group.toString()
			artifactId = project.name
			version = project.version.toString()

			// (Optional) Customize the publication settings as needed
			pom {
				// Add any additional metadata or configurations for the published artifact
			}
		}
	}

	repositories {
		mavenLocal()
	}

//	afterEvaluate {
//		publications.withType<MavenPublication> {
//			if (version == "unspecified") {
//				versionMapping.allVariants { variant ->
//					val resolvedVersion = variant.attributes.getAttribute(Usage.USAGE_ATTRIBUTE)?.get("version")?.toString()
//						?: error("Unable to determine version for variant $variant")
//					version = resolvedVersion
//				}
//			}
//		}
//	}
}
