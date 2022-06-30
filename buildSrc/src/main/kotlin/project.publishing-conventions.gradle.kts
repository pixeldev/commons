plugins {
    id("project.common-conventions")
    `maven-publish`
}

val snapshotRepository: String by project
val releaseRepository: String by project

publishing {
    repositories {
        maven {
            url = if (project.version.toString().endsWith("-SNAPSHOT")) {
                uri(snapshotRepository)
            } else {
                uri(releaseRepository)
            }

            credentials {
                val userKey = "COSMO_CI_USER"
                val pwdKey = "COSMO_CI_PASSWORD"
                val user = System.getenv(userKey)
                val pwd = System.getenv(pwdKey)
                println("$userKey: $user")
                println("$pwdKey: $pwd")
                username = project.properties[userKey] as String?
                    ?: user
                password = project.properties[pwdKey] as String?
                    ?: pwd
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            artifactId = "${rootProject.name}-${project.name}"
            from(components["java"])
        }
    }
}