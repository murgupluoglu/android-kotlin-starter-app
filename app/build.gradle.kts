plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.murgupluoglu.kotlinmvvm")
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    base {
        archivesBaseName = "starter-app"
    }
    signingConfigs {
        create("release") {
            storeFile(project.rootProject.file("store_files/keystore.jks"))
            storePassword("123456")
            keyAlias("kotlin-starter")
            keyPassword("123456")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            versionNameSuffix = "-test"
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    testImplementation(Testing.JUNIT)
    androidTestImplementation(AndroidTesting.JUNIT)
    androidTestImplementation(AndroidTesting.ESPRESSO)

    implementation(SupportLibs.APPCOMPAT)
    implementation(SupportLibs.CONSTRAINT_LAYOUT)
    implementation(SupportLibs.CORE_KTX)
    implementation(SupportLibs.MATERIAL)
    implementation(SupportLibs.PREFERENCE_KTX)
    implementation(SupportLibs.WORK)
    implementation(SupportLibs.LIFECYCLE_COMMON)

    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.CONVERTER_GSON)
    implementation(Retrofit.OKHTTP)
    implementation(Retrofit.OKHTTP_INTERCEPTOR)
    implementation(Retrofit.OKHTTP_URLCONNECTION)

    implementation(Dagger.HILT)
    kapt(Dagger.KAPT_COMPILER)

    implementation(Utils.BLANKJ)
    implementation(Utils.REQUEST)
}