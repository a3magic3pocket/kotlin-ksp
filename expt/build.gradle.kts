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
