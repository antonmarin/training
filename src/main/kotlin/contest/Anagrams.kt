package contest

//import java.io.*
//val input = BufferedReader(InputStreamReader(System.`in`))
fun main() {
//    val stringA = input.readLine()!!
//    val stringB = input.readLine()!!
    val stringA = "somestring"
    val stringB = "stringsome"

    println(if(countAnagram(stringA) == countAnagram(stringB)) 1 else 0)
}

fun countAnagram(string: String): Map<Char, Int> {
    val result = HashMap<Char,Int>()
    string.forEach { result[it] = result[it]?.inc() ?: 0}

    return result
}
