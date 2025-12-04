
### СТРУКТУРА ПРОЕКТА

```asciidoc
m2-lecture-01/
├── settings.gradle.kts          # Настройки проекта
├── build.gradle.kts             # Конфигурация сборки
├── gradle.properties           # Свойства Gradle
├── gradlew, gradlew.bat        # Gradle Wrapper
│
├── src/
│   ├── main/kotlin/Main.kt     # Основной код
│   └── test/kotlin/ExampleTest.kt # Тесты
│
└── .gitignore
```

### Жизненный цикл конфигурации
1. Инициализация: Gradle читает settings.gradle.kts, определяет модули
2. Конфигурации: Выполняются build.gradle.kts (но задачи не запускаются)
3. Выполнение: Запускаются задачи в порядке зависимостей 

### КОМАНДЫ ДЛЯ ДЕМО
##### Из корня проекта (m2-lecture-01):

##### 1. Простые задачи
./gradlew hello  
./gradlew intro  
./gradlew projectInfo

##### 2. Запуск тестов
./gradlew test  

##### 3. Сборка проекта 
./gradlew build  

##### 4. Запуск приложения (*)
./gradlew run  

##### 5. Зависимости между задачами
./gradlew buildReport

# 6. Полный аудит
./gradlew projectAudit

##### Полезные команды
./gradlew tasks                  # Все доступные задачи  
./gradlew dependencies           # Зависимости проекта  
./gradlew properties             # Свойства Gradle  
