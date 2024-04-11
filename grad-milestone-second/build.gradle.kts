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
    val springBootVersion = "3.2.4"
    val junitBomVersion = "5.10.2"
    val lombokVersion = "1.18.32"
    val postgresqlVersion = "42.7.3"
    val thymeleafVersion = "3.1.2.RELEASE"
    val springBootTestcontainersVersion = "1.19.7"
    val mapStructVersion = "1.5.5.Final"
    val openCsv = "5.9"
    val restAssured = "5.4.0"

    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.thymeleaf:thymeleaf:$thymeleafVersion")
    implementation("org.flywaydb:flyway-core")
    implementation("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    implementation("com.opencsv:opencsv:$openCsv")
    implementation("org.mapstruct:mapstruct:$mapStructVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers:$springBootTestcontainersVersion")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:$springBootTestcontainersVersion")

    testImplementation(platform("org.junit:junit-bom:$junitBomVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.rest-assured:rest-assured:$restAssured")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    //developmentOnly("org.springframework.boot:spring-boot-docker-compose")
}

tasks {
    test {
        useJUnitPlatform()
    }
    withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }
}