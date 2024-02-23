plugins {
    id("java")
}

group = "hu.geralt"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

val developmentOnly: Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

dependencies {
    val springBootVersion = "3.2.3"
    val junitBomVersion = "5.9.1"
    val lombokVersion = "1.18.30"
    val postgresqlVersion = "42.7.2"
    val thymeleafVersion = "3.1.2.RELEASE"
    val springBootTestcontainersVersion = "1.19.6"

    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.flywaydb:flyway-core")

    implementation("org.springframework.boot:spring-boot-starter-data-couchbase")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web-services")

    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.thymeleaf:thymeleaf:$thymeleafVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers:$springBootTestcontainersVersion")
    testImplementation("org.springframework.security:spring-security-test")

    testImplementation("org.testcontainers:couchbase")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:$springBootTestcontainersVersion")

    testImplementation(platform("org.junit:junit-bom:$junitBomVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    //implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks {
    test {
        useJUnitPlatform()
    }
    withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }
}