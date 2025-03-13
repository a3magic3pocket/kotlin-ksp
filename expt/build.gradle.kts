plugins {
	val kotlinVersion = "2.1.10"
	val kspVersion = "2.1.10-1.0.31"

	kotlin("jvm") version kotlinVersion

	// spring, jpa
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion // JPA 플러그인 추가
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	application

	// ksp
	id("com.google.devtools.ksp") version kspVersion
}

group = "kotlink"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot 및 JPA 관련 라이브러리
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Jakarta Persistence API (JPA)
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

	// H2 Database
	runtimeOnly("com.h2database:h2")

	// Kotlin + JPA 호환성
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// KSP API를 사용하는 코드에서 사용할 의존성
	implementation(project(":ksp"))
	ksp(project(":ksp"))
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

application {
	mainClass.set("kotline.xpt.ExptApplication") // 실제 main 클래스 경로로 변경
}