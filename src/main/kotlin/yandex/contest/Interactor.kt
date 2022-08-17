package yandex.contest

fun main() {
    val resultTask = readLine()!!.toInt()
    val resultInteractor = readLine()!!.toInt()
    val resultChecker = readLine()!!.toInt()

    val result = when(resultInteractor) {
        0 -> if(resultTask == 0) resultChecker else 3
        1 -> resultChecker
        4 -> if(resultTask == 0) 4 else 3
        6 -> 0
        7 -> 1
        else -> resultInteractor
    }

    println(result)
}
