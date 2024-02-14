import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private val paperVersion = project.properties["paperVersion"].toString()
plugins {
    kotlin("jvm") version Dependency.Kotlin.VERSION
    id("io.papermc.paperweight.userdev") version "1.5.11"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.2.2"
}
repositories {
    mavenCentral()
    Dependency.Repositories.forEach { maven(it) }
}
dependencies {
    paperweight.paperDevBundle("${paperVersion}-R0.1-SNAPSHOT")
    Dependency.apply {
        Libraries.forEach { implementation(it) }
        PaperLibraries.forEach { paperLibrary(it) }
    }
}
paper {
    name = rootProject.name
    version = project.version.toString()
    paperVersion.apply { apiVersion = split(".${split(".")[2]}")[0] }
    generateLibrariesJson = true
    val codeName = name!!.replace("-", "")
    "${group}.${codeName.lowercase()}.plugin.".apply {
        loader = "${this}loader.${codeName}PluginLoader"
        main = "${this}${codeName}Plugin"
    }
}
tasks {
    withType<KotlinCompile> { kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString() }
    processResources { filteringCharset = Charsets.UTF_8.toString() }
    runServer {
        jvmArgs = arrayListOf("-Dcom.mojang.eula.agree=true")
        minecraftVersion(paperVersion)
    }
}