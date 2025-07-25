dependencies {
    shaded(project(":platforms:bukkit:common"))
    shaded(project(":platforms:bukkit:nms:v1_21_8"))
    shaded("xyz.jpenilla", "reflection-remapper", Versions.Bukkit.reflectionRemapper)
}

tasks {
    shadowJar {
        relocate("io.papermc.lib", "com.dfsek.terra.lib.paperlib")
        relocate("com.google.common", "com.dfsek.terra.lib.google.common")
        relocate("org.apache.logging.slf4j", "com.dfsek.terra.lib.slf4j-over-log4j")
        exclude("org/slf4j/**")
        exclude("org/checkerframework/**")
        exclude("org/jetbrains/annotations/**")
        exclude("org/intellij/**")
        exclude("com/google/errorprone/**")
        exclude("com/google/j2objc/**")
        exclude("javax/**")

        manifest {
            attributes(
                "paperweight-mappings-namespace" to "mojang"
            )
        }
    }
}