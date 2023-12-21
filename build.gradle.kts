// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
//        classpath("com.google.dagger:hilt-android-gradle-plugin:${DaggerHilt.version}")
        classpath("com.android.tools.build:gradle:8.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.kotlinVersion}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {


    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("com.google.dagger.hilt.android") version DaggerHilt.version apply false

}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


/*
buildscript {
    ext {
        compose_version = '1.2.0'
    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:7.0.4"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0"
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.42'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}*/
