// Специфические плагины
// источники плагинов:
// - Gradle Plugin Portal -  основные плагины
// - Maven Central - Java/Kotlin
// - Google Maven - Android-плагины
// - Кастомные репозитории - внутри компании

// Типы идентификаторов плагинов:
// - короткие (core plugins): java, application, kotlin("jvm")
// - полные (community plugins): io.qameta.allure, com.github...
// - локальные: id("my-plugin")
plugins {
    kotlin("jvm") version "1.9.0"   // Kotlin плагин
    application                     // Для запуска приложения
    // jacoco                       // Плагин для покрытия кода
    // id("org.jetbrains.dokka") version "..." // Документация
    // id("ogr.jetbrains.kotlin.multiplatform") version "..." // Для KMP
}

// Подключение плагинов (вариант 2, (устаревший)
// buildscript {
//    repositories {
//        gradlePluginPortal()
//    }
//    dependencies {
//        classpath("com.example:custom-plugin:1.0")
//    }
// }
// apply(plugin = "com.example.custom-plugin")

group = "ru.otus"
version = "1.0-SNAPSHOT"

// Области видимости репозиториев
// Только для загрузки плагинов
// pluginManagement {
//    repositories {
//        gradlePluginPortal()
//        mavenCentral()
//    }
// }

// Для всех проектов
// allprojects {
//    repositories {
//        mavenCentral()
//    }
// }

// Для конкретного проекта
repositories {
    mavenCentral() // Репозиторий зависимостей
}

// Для KMP - здесь будут настройки под конкретные платформы (JVM, Android, JS)

// Зависимости на Groovy: (типизация динамическая, проверяем во время выполнения)
// dependencies {
//    implementation 'org.springframework:spring-core:5.3.0'
//    testImplementation 'junit:junit:4.13'
// }

// Зависимости на KTS: (типизация статическая, проверяется при компиляции)
dependencies {
    implementation(kotlin("stdlib")) // Стандартная библиотека Kotlin
    testImplementation(kotlin("test")) // Для тестирования - всё необходимое в одной зависимости
}

// Секция для рантайма
// runtimeOnly("...")

// Обработка аннотаций
// kapt / ksp ("...")

// Инструментальные тесты Android
// androidTestImplementation

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

    useJUnitPlatform() // Используем JUnit 5 (рекомендованной), useTestNG(), useJUnit() - версия 4

    // Логирование
    testLogging {
        events("passed", "failed", "skipped")
    }

    // Слушатели
    // addTestListener(CustomTestListener())

    // Системные свойства
    // systemProperties["user.timezone"] = "UTC"

    // Фильтрации
    // include("**/*Test.class")
    // exclude("**/*IntegrationTest.class")

    // Производительность
    // maxParallelForks = Runtime.getRuntime().availableProcessors()
    // failFast = false

}

// 4. Конфигурация для запуска приложения
application {
    mainClass.set("MainKt") // Класс с функцией main()
}

// 5. Настройка версии Java
kotlin {
    jvmToolchain(17) // Используем Java 17
}

// 6. Показывает информацию о проекте
tasks.register("projectInfo") {
    group = "help"
    description = "Показывает информацию о проекте"

    doFirst {
        println("=== Информация о проекте ===")
    }

    doLast {
        println("Имя: $rootProject.name")
        println("Версия: $version")
        println("Каталог сборки: ${layout.buildDirectory.get()}")
    }
}

// В build.gradle.kts
tasks.register("prepare") {
    doLast {
        println("📦 Подготовка окружения...")
    }
}

tasks.register("compileKotlinCustom") {
    dependsOn("prepare") // Зависит от prepare
    doLast {
        println("🔧 Компиляция Kotlin...")
    }
}

tasks.register("runTests") {
    dependsOn("compileKotlinCustom") // Зависит от компиляции
    mustRunAfter("compileKotlinCustom") // И строго после
    doLast {
        println("🧪 Запуск тестов...")
    }
}

tasks.register("buildReport") {
    dependsOn("runTests") // Зависит от тестов
    shouldRunAfter("runTests") // Желательно после
    doLast {
        println("📊 Генерация отчёта...")
    }
}

// Новая задача, использующая все основные объекты
tasks.register("projectAudit") {
    group = "help"
    description = "Полный аудит проекта"

    doLast {
        println("=".repeat(50))
        println("🔍 АУДИТ ПРОЕКТА: ${project.name}")
        println("=".repeat(50))

        // Проект
        println("\n📁 ПРОЕКТ:")
        println("  Имя: ${project.name}")
        println("  Путь: ${project.path}")
        println("  Версия: ${project.version}")
        println("  Каталог: ${project.projectDir}")

        // Плагины
        println("\n🔌 ПЛАГИНЫ (${project.plugins.size}):")
        project.plugins.take(5).forEach {
            println("  • ${it.javaClass.simpleName}")
        }

        // Задачи
        println("\n✅ ЗАДАЧИ (${tasks.size}):")
        tasks.filter { it.group == "help" }.forEach {
            println("  • ${it.name}: ${it.description ?: "без описания"}")
        }

        // Зависимости
        println("\n📦 ЗАВИСИМОСТИ:")
        listOf("implementation", "testImplementation").forEach { configName ->
            val deps = configurations.findByName(configName)?.dependencies?.size ?: 0
            println("  • $configName: $deps зависимостей")
        }

        // Gradle
        println("\n⚙️ GRADLE:")
        println("  Версия: ${gradle.gradleVersion}")
        println("  Kotlin DSL: ${project.buildFile.name.endsWith(".kts")}")

        println("\n" + "=".repeat(50))
    }
}