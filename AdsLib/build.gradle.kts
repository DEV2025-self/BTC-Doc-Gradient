plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.doc.gradient.bt.server.uses.ai.ads"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")



    /*******************************
     ******* Admob Library********
     *******************************/
    implementation ("com.intuit.sdp:sdp-android:1.0.6")
    implementation ("com.google.ads.mediation:facebook:6.17.0.0")
    implementation ("com.google.android.ads:mediation-test-suite:3.0.0")
    implementation ("com.google.android.ads.consent:consent-library:1.0.8")
    implementation ("com.google.android.gms:play-services-ads:23.0.0")


    /******************************
     ******* Facebook ***********
     ******************************/
    implementation ("com.facebook.android:facebook-android-sdk:latest.release")
    implementation ("com.facebook.android:audience-network-sdk:6.19.0")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    /*****************************
     ****  YOYO animation ******
     *****************************/
    implementation ("com.daimajia.easing:library:2.4@aar")
    implementation ("com.daimajia.androidanimations:library:2.4@aar")


    /*****************************
     **** GifImageView ******
     *****************************/
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.25")


    /*****************************
     **** Glide For Image ******
     *****************************/
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
}