package yandex.contest

fun main() {
    val jewels = readLine()!!
    val stones = readLine()!!

    val result = stones.count {it in jewels}
    println(result)
}
