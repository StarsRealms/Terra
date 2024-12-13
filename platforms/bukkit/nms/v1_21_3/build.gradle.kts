apply(plugin = "io.papermc.paperweight.userdev")


repositories {
    mavenLocal()
    maven {
        name = "AliYun-Release"
        url = uri("https://packages.aliyun.com/maven/repository/2421751-release-ZmwRAc/")
        credentials {
            username = project.findProperty("aliyun.package.user") as String? ?: System.getenv("ALY_USER")
            password = project.findProperty("aliyun.package.password") as String? ?: System.getenv("ALY_PASSWORD")
        }
    }
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
    paperweightDevBundle("com.starsrealm.nylon", "1.21.4-R0.1-20241212.130150-1")
    implementation("xyz.jpenilla", "reflection-remapper", Versions.Bukkit.reflectionRemapper)
}