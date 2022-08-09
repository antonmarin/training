package contest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class RemoveDuplicatesTest {
    @ParameterizedTest
    @MethodSource("provideRemoveDuplicatesCases")
    fun `should `(input:Array<String>, expected: List<Int>){
        val removed = removeDuplicates(input.iterator())


        assertEquals(expected, removed)
    }

    companion object {
        @JvmStatic
        fun provideRemoveDuplicatesCases(): Stream<Arguments> = Stream.of(
            Arguments.of(arrayOf("5","2","4","8","8","8"), listOf(2,4,8)),
            Arguments.of(arrayOf("5","2","2","2","8","8"), listOf(2,8)),
            Arguments.of(arrayOf("0"), emptyList<Int>()),
            Arguments.of(arrayOf("1", Int.MIN_VALUE.toString()), listOf(Int.MIN_VALUE)),
        )
    }
}
