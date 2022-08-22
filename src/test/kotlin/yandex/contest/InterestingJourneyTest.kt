package yandex.contest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.BufferedReader
import java.io.StringReader
import java.util.stream.Stream

internal class InterestingJourneyTest {
    @Test
    fun `should count abs of coordinates diff`() {
        val cityA = City(1, 3, -8)
        val cityB = City(2, -3, 2)

        assertEquals(16, countDistanceBetween(cityA, cityB))
    }

    @Test
    fun `should parse input`() {
        val input = BufferedReader(StringReader(INPUT))

        val result = parseInput(input)

        assertEquals(
            listOf(
                City(1, 0, 0),
                City(2, 0, 2),
                City(3, 2, 2),
                City(4, 0, -2),
                City(5, 2, -2),
                City(6, 2, -1),
                City(7, 2, 1),
            ),
            result.cities,
        )
        assertEquals(2, result.maxDistance)
        assertEquals(1, result.fromCityId)
        assertEquals(3, result.toCityId)
    }

    @Test
    fun `should build graph without edges longer then max`() {
        val cities = listOf(
            City(1, 0, 0),
            City(2, 0, 2),
            City(3, 2, 0),
            City(4, 0, 3),
        )

        val graph = Graph(cities, 2)

        assertEquals(
            mapOf(
                1 to listOf(2, 3),
                2 to listOf(1, 4),
                3 to listOf(1),
                4 to listOf(2),
            ),
            graph.edges
        )
    }

    @Test
    fun `should count edges`() {
        val cities = listOf(
            City(1, 0, 0),
            City(2, 0, 2),
            City(3, 2, 0),
            City(4, 0, 3),
        )

        val graph = Graph(cities, 2)

        assertEquals(2, graph.bfs(1, 4))
    }

    @ParameterizedTest
    @MethodSource("provideBfsExpectations")
    fun `should count edges by input`(expected: Int, input: String) {
        val inputData = parseInput(BufferedReader(StringReader(input)))

        val graph = Graph(inputData.cities, inputData.maxDistance)

        assertEquals(expected, graph.bfs(inputData.fromCityId, inputData.toCityId))
    }

    companion object {
        @JvmStatic
        fun provideBfsExpectations(): Stream<Arguments> = Stream.of(
            Arguments.of(
                2, """
                7
                0 0
                0 2
                2 2
                0 -2
                2 -2
                2 -1
                2 1
                2
                1 3
            """.trimIndent()
            ),
            Arguments.of(
                1, """
                4
                0 0
                1 0
                0 1
                1 1
                2
                1 4
            """.trimIndent()
            ),
            Arguments.of(
                -1, """
                4
                0 0
                2 0
                0 2
                2 2
                1
                1 4
            """.trimIndent()
            ),
        )

        private val INPUT = """
            7
            0 0
            0 2
            2 2
            0 -2
            2 -2
            2 -1
            2 1
            2
            1 3
        """.trimIndent()
    }
}
