plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
    id("kotlin-android")
}

android {
    namespace = "com.doc.gradient.bt.server.uses.ai"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.doc.gradient.bt.server.uses.ai"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    android {
        sourceSets {
            getByName("main") {
                res.srcDirs(
                    "src/main/res",
                    "src/main/res/layouts",
                    "src/main/res/layouts/Activity",
                    "src/main/res/layouts/Activity/layout",
                    "src/main/res/layouts/recycler_item",
                    "src/main/res/layouts/recycler_item/layout",
                    "src/main/res/layouts/Dialog",
                    "src/main/res/layouts/Dialog/layout"
                )
            }
        }
    }

    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            GradleVersion.version("3.10.2")// added plus after version
        }
    }

    kapt {
        javacOptions {
            option("-Xlint:deprecation")
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isPseudoLocalesEnabled = false
            isZipAlignEnabled = true
            isShrinkResources = true
            isMinifyEnabled = true
            signingConfig = signingConfigs.create("config") {
                storeFile = file("D:\\Android\\Project\\AppCred\\MineUSDT.jks")
                storePassword = "MineUSDT"
                keyAlias = "MineUSDT"
                keyPassword = "MineUSDT"
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isPseudoLocalesEnabled = false
            isZipAlignEnabled = true
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
        exclude("META-INF/DEPENDENCIES.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("mozilla/public-suffix-list.txt")
        exclude("META-INF/dependencies.txt")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/LICENSE")
        exclude("META-INF/license.txt")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/notice.txt")
    }
}

dependencies {
    implementation(project(":AdsLib"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.browser)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.animation.core.android)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /******************************************
     ******** Lottie Animation ****************
     * ****************************************/
    implementation(libs.lottie)

    /***********************************
     ******** Animation Library*********
     ***********************************/
    implementation(libs.konfetti)//celebration
    implementation("com.daimajia.easing:library:2.4@aar") /* YOYO animation*/
    implementation("com.daimajia.androidanimations:library:2.4@aar")
    implementation(libs.shimmer)

    /*****************************
     ******* In App update *******
     *****************************/
    implementation(libs.app.update)


    /************************************
     ******** country picker ************
     * ******************************* */
    implementation (libs.country.picker.android)


    /******************************
     **** Socket Added *******
     ***************************/
    implementation(libs.socket.io.client)

    /******************************
     **** Circle Image view *******
     ***************************/
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
    implementation(libs.circleimageview)


    /*************************************************
     ******** Billing (InAppPurchase) Library*********
     *************************************************/
    implementation(libs.billing)


    /***************************
     ******* Firebase  *******
     ***************************/
    implementation(libs.firebase.bom)
    implementation (libs.firebase.crashlytics)
    implementation (libs.firebase.database)
    implementation (libs.firebase.messaging)
    implementation (libs.firebase.analytics)
    implementation (libs.firebase.auth)
    implementation (libs.play.services.auth)


    /********************************** ******* Dagger And Hilt ******** **********************************/
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation("com.google.dagger:hilt-compiler:2.48.1") {
        exclude("group: 'META-INF', module: 'gradle/incremental.annotation.processors'")
    }
    /************************************ ******* Lifecycle Of MVVM ******** ************************************/
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.8.7")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.fragment:fragment-ktx:1.8.6")

    /*************************** ******* Retrofit ******** ***************************/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.facebook.stetho:stetho-okhttp3:1.5.1")

    /********************************* ****** Kotlin Coroutines ****** *********************************/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

}