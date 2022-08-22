package structure

class ForwardListItem(val value: String, var next: ForwardListItem? = null) {
    /**
     * Получить склеенные значения всего списка
     */
    fun printAllNext(): String {
        val sb = StringBuilder().append(value)
        if (next != null) {
            sb.append(next!!.printAllNext())
        }

        return sb.toString()
    }
}
