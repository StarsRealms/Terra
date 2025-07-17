import io.papermc.paperweight.userdev.ReobfArtifactConfiguration

plugins {
    id("io.papermc.paperweight.userdev")
}

paperweight.reobfArtifactConfiguration = ReobfArtifactConfiguration.MOJANG_PRODUCTION

repositories {
    mavenLocal()
    maven {
        name = "AliYun-Snapshot"
        url = uri("https://packages.aliyun.com/maven/repository/2421751-snapshot-i7Aufp/")
        credentials {
            username = project.findProperty("aliyun.package.user") as String? ?: System.getenv("ALY_USER")
            password = project.findProperty("aliyun.package.password") as String? ?: System.getenv("ALY_PASSWORD")
        }
    }
}

dependencies {
    api(project(":platforms:bukkit:common"))
    paperweight.devBundle("com.starsrealm.nylon", "1.21.7-R0.1-20250705.122835-1")
    implementation("xyz.jpenilla", "reflection-remapper", Versions.Bukkit.reflectionRemapper)
}