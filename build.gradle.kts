plugins {
    kotlin("jvm") version "1.7.22"
    id("me.champeau.jmh") version "0.6.8"

}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src/main")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}
