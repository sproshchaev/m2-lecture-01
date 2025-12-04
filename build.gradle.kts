plugins {
    kotlin("jvm") version "1.9.0"   // Kotlin плагин
    application                     // Для запуска приложения
    // jacoco                       // Плагин для покрытия кода
}

group = "ru.otus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral() // Репозиторий зависимостей
}

dependencies {
    implementation(kotlin("stdlib")) // Стандартная библиотека Kotlin

    testImplementation(kotlin("test")) // Для тестирования - всё необходимое в одной зависимости
}

// === ДЕМО-ЗАДАЧИ ДЛЯ ВЕБИНАРА ===

// 1. Простая задача
tasks.register("hello") {
    doLast {
        println("👋 Привет от Gradle KTS!")
    }
}

// 2. Задача с зависимостью
tasks.register("intro") {
    dependsOn("hello") // Зависит от задачи hello
    doLast {
        println("🎯 Это демонстрация зависимостей между задачами")
    }
}

// 3. Кастомная конфигурация тестов
tasks.test {
    useJUnitPlatform() // Используем JUnit 5
    testLogging {
        events("passed", "failed", "skipped")
    }
}

// 4. Конфигурация для запуска приложения
application {
    mainClass.set("MainKt") // Класс с функцией main()
}

// 5. Настройка версии Java
kotlin {
    jvmToolchain(17) // Используем Java 17
}