object Dependency {
    object Kotlin {
        private const val GROUP = "org.jetbrains.kotlin"
        const val VERSION = "1.9.0"
        const val STDLIB = "${GROUP}:kotlin-stdlib:${VERSION}"
    }
    private object CommandAPI {
        private const val GROUP = "dev.jorel"
        private const val VERSION = "9.3.0"
        const val SHADE = "${GROUP}:commandapi-bukkit-shade:${VERSION}"
        const val KOTLIN = "${GROUP}:commandapi-bukkit-kotlin:${VERSION}"
    }
    val Repositories = arrayListOf(
        "https://repo.codemc.org/repository/maven-public"
    )
    val Libraries = arrayListOf(
        Kotlin.STDLIB,
        CommandAPI.SHADE,
        CommandAPI.KOTLIN
    )
    val PaperLibraries = arrayListOf(
        Kotlin.STDLIB,
        CommandAPI.SHADE
    )
}