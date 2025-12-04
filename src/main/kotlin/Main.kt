package ru.otus.m2

fun main() {
    println("=== Модуль m2-lecture-01 ===")
    println("Демонстрация работы с Gradle KTS")
    println("Сумма чисел 10 и 20: ${calculate(10, 20)}")
    println("Площадь круга радиусом 5: ${circleArea(5.0)}")
}

fun calculate(a: Int, b: Int): Int = a + b

fun circleArea(radius: Double): Double = Math.PI * radius * radius