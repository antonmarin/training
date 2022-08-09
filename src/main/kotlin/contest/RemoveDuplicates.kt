package contest

/**
 * by some reason replacing mutable list with println() breaks this code
 */
fun main(){
//    val sequence = generateSequence(::readLine)
    val sequence = sequenceOf("5","2","4","8","8","8")
    val result = removeDuplicates(sequence.iterator())
    result.forEach {println(it)}
}

fun removeDuplicates(sequence: Iterator<String>): List<Int> {
    val linesCnt = sequence.next().toInt()
    val result = mutableListOf<Int>()
    var min: Int? = null
    repeat(linesCnt) {
        val next = sequence.next().toInt()
        if (min == null || next > min!!) {
            result.add(next)
            min = next
        }
    }
    return result
}
