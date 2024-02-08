plugins {
    java
    jacoco
    id("org.unbroken-dome.test-sets") version "4.1.0"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
}

group = "hu.geralt.gradproject"
version = "1.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    val lombokVersion = "1.18.30"
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    implementation("org.projectlombok:lombok:$lombokVersion")
    implementation("org.postgresql:postgresql:42.3.8")
    implementation("org.flywaydb:flyway-core:9.3.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.2")
    implementation("org.freemarker:freemarker:2.3.31")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.apache.logging.log4j:log4j-api:2.22.0")
    implementation ("org.apache.logging.log4j:log4j-core:2.22.0")
    testImplementation ("org.mockito:mockito-core:4.0.0")
}

testSets.create("integrationTest")

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    withType(Test::class.java) {
        useJUnitPlatform()
    }
    test {
        useJUnitPlatform()
    }
    val integrationTest = getByName<Test>("integrationTest") {
        maxHeapSize = "1g"
        shouldRunAfter(test)
    }
    check {
        dependsOn(integrationTest)
    }
    jacocoTestReport {
        dependsOn("check")
        reports.xml.required.set(true)
    }

}
