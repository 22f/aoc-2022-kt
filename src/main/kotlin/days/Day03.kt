package days

import readInput

fun main() {
    //a - 97, z - 122, A - 65, Z - 90
    val input = readInput("day3_1.prod")
    val result1 = input.map { line ->
        val l1 = line.substring(0, line.length / 2)
        val l2 = line.substring(line.length / 2, line.length)
        val result = l1.first { l2.contains(it) }
        result.toPriority()
    }.sum()
    println(result1)

    var r2 = 0
    for (i in input.indices step 3) {
        val first = input[i]
        val second = input[i + 1]
        val third = input[i + 2]
        val common = first.first { second.contains(it) && third.contains(it) }
        r2 += common.toPriority()
    }

    println(r2)


}

fun Char.toPriority(): Int {
    return if (isUpperCase()) {
        code - 38
    } else code - 96
}