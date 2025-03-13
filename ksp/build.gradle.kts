plugins {
	val kotlinVersion = "2.1.10"
	val kspVersion = "2.1.10-1.0.31"

	kotlin("jvm") version "1.9.25"
	kotlin("kapt") version kotlinVersion
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
	val kspVersion = "2.1.10-1.0.31"

	// KSP API를 사용하는 코드에서 사용할 의존성
	implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")  // 테스트를 위한 의존성
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
	testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.0") // Kotlin Test 의존성
	testImplementation("io.mockk:mockk:1.12.0")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
