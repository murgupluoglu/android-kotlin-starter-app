plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
    jcenter()
}

kotlinDslPluginOptions.experimentalWarning.set(false)

dependencies {
    implementation("com.android.tools.build:gradle:4.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.36")
}