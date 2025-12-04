import ru.otus.m2.calculate
import ru.otus.m2.circleArea
import kotlin.test.Test
import kotlin.test.assertEquals

class MathTests {
    @Test
    fun testSum() {
        assertEquals(5, calculate(2, 3))
    }

    @Test
    fun testCircleArea() {
        assertEquals(Math.PI * 25.0, circleArea(5.0))
    }
}

class GradleTests {
    @Test
    fun demoTest() {
        println("Этот тест демонстрирует работу тестов в Gradle")
        assertEquals(1, 1)
    }
}