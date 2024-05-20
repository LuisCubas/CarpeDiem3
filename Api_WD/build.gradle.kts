plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("io.rest-assured:json-schema-validator:5.4.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite")

    //implementation ("org.seleniumhq.selenium:selenium-java:4.12.0")
    //implementation ("net.masterthought:cucumber-reporting:5.7.7")

    testImplementation ("io.cucumber:cucumber-java:7.14.0")
    testImplementation ("io.cucumber:cucumber-junit:7.14.0")
    testImplementation ("io.cucumber:cucumber-junit-platform-engine:7.14.0")


}

tasks.test {
    useJUnitPlatform()
}

