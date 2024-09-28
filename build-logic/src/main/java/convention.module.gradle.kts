plugins {
    id("java")
    id("kotlin")
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.codec.common)

    testImplementation(libs.bundles.test)
    testImplementation(libs.bundles.test.cucumber)
}