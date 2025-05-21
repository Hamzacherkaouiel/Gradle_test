plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	// Plugin SonarQube
	id("org.sonarqube") version "4.4.1.3373"
	// Plugin JaCoCo pour la couverture de code
	id("jacoco")
}

group = "com"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
	// Activer JaCoCo pour mesurer la couverture de tests
	finalizedBy(tasks.jacocoTestReport)
}

// Configurer la génération du rapport JaCoCo
tasks.jacocoTestReport {
	reports {
		xml.required.set(true) // Nécessaire pour SonarQube
		csv.required.set(false)
		html.required.set(true)
	}
	dependsOn(tasks.test)
}

// Configuration spécifique pour SonarQube
sonarqube {
	properties {
		property("sonar.sourceEncoding", "UTF-8")
		property("sonar.java.source", "21")
		// Pour les rapports JaCoCo
		property("sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/reports/jacoco/test/jacocoTestReport.xml")
		// Pour les rapports JUnit
		property("sonar.junit.reportPaths", "${buildDir}/test-results/test")
	}
}

// Votre configuration existante pour désactiver les tests si nécessaire
// Remarque: Ceci désactivera les tests et donc JaCoCo ne produira pas de rapport
// Ce qui affectera la couverture de code dans SonarQube
