object SupportLibs {
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val CORE_KTX = "androidx.core:core-ktx:1.3.2"
    const val MATERIAL = "com.google.android.material:material:1.3.0"
    const val PREFERENCE_KTX = "androidx.preference:preference-ktx:1.1.1"
    const val WORK = "androidx.work:work-runtime-ktx:2.5.0"
    const val lifecycleVersion = "2.3.1"
    const val LIFECYCLE_COMMON =  "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
}

object Retrofit {
    const val retrofitVersion = "2.9.0"
    const val okhttpVersion = "4.9.0"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
    const val OKHTTP_URLCONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:$okhttpVersion"
}

object Utils {
    const val BLANKJ = "com.blankj:utilcodex:1.30.6"
    const val REQUEST = "com.github.murgupluoglu:request-android:5.0.0"
}

object Dagger {
    const val daggerVersion = "2.36"
    const val HILT = "com.google.dagger:hilt-android:$daggerVersion"
    const val KAPT_COMPILER = "com.google.dagger:hilt-android-compiler:$daggerVersion"
}

object Testing {
    const val JUNIT = "junit:junit:4.13.2"
}

object AndroidTesting {
    const val JUNIT = "androidx.test.ext:junit:1.1.2"
    const val TEST_RULES = "androidx.test:rules:1.3.0"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:3.3.0"
}
