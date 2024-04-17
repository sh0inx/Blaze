@file:Suppress("DSL_SCOPE_VIOLATION", "MISSING_DEPENDENCY_CLASS", "FUNCTION_CALL_EXPECTED", "PropertyName")

plugins {
	id("org.quiltmc.loom") version("1.+")
	id("com.github.johnrengelman.shadow") version("8.1.1")
	java
	`maven-publish`
}

base {
        archivesName = property("mod_id").toString()
}

repositories {
	maven {
		name = "oωo lib"
		url = uri("https://maven.wispforest.io/releases/")
	}
}

dependencies {
	minecraft("com.mojang:minecraft:" + property("minecraft_version"))
    mappings("org.quiltmc:quilt-mappings:" + property("quilt_mappings") + ":intermediary-v2")

	// Quilt API
	modImplementation("org.quiltmc:quilt-loader:" + property("quilt_loader"))
	modImplementation("org.quiltmc.quilted-fabric-api:quilted-fabric-api:" + property("qfapi_version"))

	// Other Environment Dependencies

	// oωo lib
	modImplementation("io.wispforest:owo-lib:" + property("owo_version"))

	// Java Libraries
	implementation("org.projectlombok:lombok:1.18.28")

	// Annotations
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	annotationProcessor("io.wispforest:owo-lib:" + property("owo_version"))

}

loom {

	decompilers {
		named("vineflower") {
			options.put("<option name>", "<value>")
		}
	}

    splitEnvironmentSourceSets()

    mods {
        // This should match your mod id.
        create("blaze") {
            // Tell Loom about each source set used by your mod here. This ensures that your mod's classes are properly transformed by Loader.
            sourceSet("main")
            sourceSet("test")
            sourceSet("client")
            // If you shade (directly include classes, not JiJ) a dependency into your mod, include it here using one of these methods:
            // dependency("com.example.shadowedmod:1.2.3")
            // configuration("exampleShadedConfigurationName")
        }
    }
}

// gradle.properties

val modId = property("mod_id")
val modDescription = property("mod_description")
val modAuthors = property("mod_authors")
val modPage = property("mod_page")
val githubUrl = property("mod_repo")
val issuesUrl = property("mod_issues")
val license = property("mod_license")

val qfapiVersion = property("qfapi_version")
val quiltLoaderVersion = property("quilt_loader")

val minecraftVersion = property("minecraft_version")
val minecraftVersionRange = property("minecraft_version_range")

java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        withSourcesJar()
        // withJavadocJar()
}

tasks {

	processResources {
		filteringCharset = "UTF-8"

		/**filesMatching(listOf("quilt.mod.json")) {
			expand(mapOf(

					group to group,
					description to description,
					"mod_id" to modId,
					version.toString() to version,
					"mod_description" to modDescription,
					"mod_authors" to modAuthors,
					"mod_page" to modPage,
					"mod_repo" to githubUrl,
					"mod_issues" to issuesUrl,
					"mod_license" to license,

					"qfapi_version" to qfapiVersion,
					"quilt_loader" to quiltLoaderVersion,

					"minecraft_version" to minecraftVersion,
					"minecraft_version_range" to minecraftVersionRange,
			))
		}*/
	}

	javadoc {
		options.encoding = "UTF-8"
	}

	wrapper {
		distributionType = Wrapper.DistributionType.BIN
	}

	jar {
        archiveClassifier.set("")
		from("LICENSE") {
			rename { "LICENSE_blaze" }
		}

		dependsOn("shadowJar")
		enabled = true
	}

	shadowJar {
		fun relocate(origin: String) =
				relocate(origin, "io.github.sh0inx.blaze.dependencies${origin.substring(origin.lastIndexOf('.'))}")

		archiveClassifier.set("")

		relocate("io.wispforest.owo-lib")

		minimize()
	}
}
