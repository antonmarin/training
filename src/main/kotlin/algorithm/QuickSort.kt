package algorithm

/**
 * O(n log n)
 */
class QuickSort {
    /**
     * @param input Массив целых чисел который хотим сортировать
     * @param leftIndex Индекс первого сортируемого элемента массива
     * @param rightIndex Индекс последнего сортируемого элемента массива
     */
    fun quickSort(input: Array<Int>, leftIndex: Int = 0, rightIndex: Int = input.size - 1) {
        if (input.isEmpty())
            return //завершить выполнение если длина массива равна 0

        if (leftIndex >= rightIndex)
            return //завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        val middleIndex = leftIndex + (rightIndex - leftIndex) / 2
        val baseValue = input[middleIndex]

        // разделить на подмассивы, который больше и меньше опорного элемента
        var i = leftIndex
        var j = rightIndex
        while (i <= j) {
            while (input[i] < baseValue) {
                i++
            }

            while (input[j] > baseValue) {
                j--
            }

            if (i <= j) { //меняем местами
                input[i] = input[j].also { input[j] = input[i] }
                i++
                j--
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (leftIndex < j)
            quickSort(input, leftIndex, j)

        if (rightIndex > i)
            quickSort(input, i, rightIndex)
    }
}
