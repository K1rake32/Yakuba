    plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.yakuba"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.yakuba"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    val fragment_version = "1.8.0"
    val nav_version = "2.7.7"

    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.github.terrakok:cicerone:7.1")
    implementation ("androidx.viewpager2:viewpager2:1.0.0-alpha01")
    implementation ("androidx.media3:media3-exoplayer:1.3.1")
    implementation ("androidx.media3:media3-ui:1.3.1")
    implementation ("androidx.media3:media3-common:1.3.1")
    implementation ("androidx.media:media:1.5.0")
    implementation ("com.google.android.material:material:1.6.0")
    implementation ("com.redmadrobot:input-mask-android:7.2.4")
    implementation ("me.relex:circleindicator:2.1.6")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.0")
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation ("io.github.chaosleung:pinview:1.4.4")

}