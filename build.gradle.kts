	plugins {
	id("org.quiltmc.loom") version("1.+")
	//id("net.neoforged.gradle.userdev") version("7.0.105")
	id("com.github.johnrengelman.shadow") version("8.1.1")
	java
	`maven-publish`
}

base.archivesName.set(property("mod_id").toString())

repositories {
	maven {
		name = "oωo lib"
		url = uri("https://maven.wispforest.io/releases/")
	}
}

dependencies {
	minecraft("com.mojang:minecraft:" + property("minecraft_version")!!)
	//mappings("net.fabricmc:yarn:" + property("yarn_mappings")!!)
	mappings("org.quiltmc:quilt-mappings:1.20.4+build.3:intermediary-v2")

	// Quilt API
	modImplementation("org.quiltmc:quilt-loader:0.24.0")
	modImplementation("org.quiltmc.quilted-fabric-api:quilted-fabric-api:7.5.0+0.91.0-1.20.1")

	// Fabric API
	//modImplementation("net.fabricmc:fabric-loader:" + property("fabric_loader_version"))
	//modImplementation("net.fabricmc.fabric-api:fabric-api:" + property("fabric_api_version"))

	// Other Environment Dependencies

	// oωo lib
	modImplementation("io.wispforest:owo-lib:" + property("owo_version"))
	implementation("io.wispforest:owo-lib:" + property("owo_version"))

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
}

tasks {

	processResources {
		filteringCharset = "UTF-8"

		// gradle.properties
		val modId: String by extra
		val modDescription: String by extra
		val modAuthors: String by extra
		val modPage: String by extra
		val githubUrl: String by extra
		val issuesUrl: String by extra
		val license: String by extra

		val fabricApiVersion: String by extra
		val fabricLoaderVersion: String by extra

		//val neoforgeVersionRange: String by extra
		//val neoforgeLoaderVersionRange: String by extra

		val minecraftVersion: String by extra
		val minecraftVersionRange: String by extra

		filesMatching(listOf("META-INF/mods.toml", "fabric.mod.json", "quilt.mod.json")) {
			expand(mapOf(

					"maven_group" to group,

					"mod_name" to description,
					"mod_id" to modId,
					"mod_version" to version,
					"mod_description" to modDescription,
					"mod_authors" to modAuthors,
					"mod_page" to modPage,
					"mod_repo" to githubUrl,
					"mod_issues" to issuesUrl,
					"mod_license" to license,

					"fabric_api_version" to fabricApiVersion,
					"fabric_loader_version" to fabricLoaderVersion,

					//"neo_version_range" to neoforgeVersionRange,
					//"neo_loader_version_range" to neoforgeLoaderVersionRange,

					"minecraft_version" to minecraftVersion,
					"minecraft_version_range" to minecraftVersionRange,
			))
		}
	}

	compileJava {
		sourceCompatibility = JavaVersion.VERSION_17.toString()
		targetCompatibility = JavaVersion.VERSION_17.toString()
	}

	javadoc {
		options.encoding = "UTF-8"
	}

	wrapper {
		distributionType = Wrapper.DistributionType.BIN
	}

	jar {
		from("LICENSE") {
			rename { "LICENSE_${base.archivesName}" }
		}

		dependsOn("shadowJar")
		enabled = false
	}

	shadowJar {
		fun relocate(origin: String) =
				relocate(origin, "io.github.sh0inx.blaze.dependencies${origin.substring(origin.lastIndexOf('.'))}")

		archiveClassifier.set("")

		relocate("io.wispforest.owo-lib")

		minimize()
	}
}

java {
	withSourcesJar()
	// withJavadocJar()
}