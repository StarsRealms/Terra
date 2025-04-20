plugins {
    application
}

val javaMainClass = "com.dfsek.terra.minestom.TerraMinestomExample"

dependencies {
    shadedApi(project(":platforms:minestom"))

    implementation("net.minestom", "minestom-snapshots", Versions.Minestom.minestom)
    implementation("org.slf4j", "slf4j-simple", Versions.Libraries.slf4j)
}

tasks.withType<Jar> {
    entryCompression = ZipEntryCompression.STORED
    manifest {
        attributes(
            "Main-Class" to javaMainClass,
        )
    }
}

application {
    mainClass.set(javaMainClass)
}

tasks.getByName("run").setProperty("workingDir", file("./run"))
addonDir(project.file("./run/terra/addons"), tasks.named("run").get())
