plugins {
	java
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.netrunner"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_19

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.json:json:20230227")
	// https://mvnrepository.com/artifact/org.springframework/spring-jdbc
	implementation("org.springframework:spring-jdbc:6.0.6")
	// https://mvnrepository.com/artifact/mysql/mysql-connector-java
	implementation("mysql:mysql-connector-java:8.0.31")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools
	implementation("org.springframework.boot:spring-boot-devtools:3.0.1")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")
	// https://mvnrepository.com/artifact/org.projectlombok/lombok
	compileOnly("org.projectlombok:lombok:1.18.24")
	annotationProcessor("org.projectlombok:lombok:1.18.24")
	// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
	testImplementation("ch.qos.logback:logback-classic:1.4.6")
	// https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
	implementation("net.logstash.logback:logstash-logback-encoder:7.2")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
	implementation("org.springframework.boot:spring-boot-starter-validation:3.0.4")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")
	// https://mvnrepository.com/artifact/com.google.code.gson/gson
	implementation("com.google.code.gson:gson:2.10.1")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
