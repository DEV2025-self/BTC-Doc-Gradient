buildscript {
    dependencies {
        classpath ("com.google.gms:google-services:4.4.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.0")
        classpath ("com.android.tools.build:gradle:8.3.2")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:3.0.3")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.52")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
}