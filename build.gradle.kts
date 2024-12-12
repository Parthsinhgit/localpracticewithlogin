
plugins {
    // Google's KSP plugin (applied later in subprojects)
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false

    // Aliased plugins from the libs catalog (applied later in subprojects)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

