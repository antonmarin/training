package algorithm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QuickSortTest {
    private val alg = QuickSort()

    @Test
    fun `should sort full input`() {
        val input = arrayOf(3, 7, 1, 8, 14, 23, 4, 2, 8, 2)
        alg.quickSort(input)

        assertArrayEquals(arrayOf(1, 2, 2, 3, 4, 7, 8, 8, 14, 23), input)
    }

    @Test
    fun `should sort selected part of input`() {
        val input = arrayOf(3, 7, 1, 8, 14, 23, 4, 2, 8, 2)
        alg.quickSort(input, 3,8)

        assertArrayEquals(arrayOf(3,7,1,2,4,8,8,14,23,2), input)
    }
}
