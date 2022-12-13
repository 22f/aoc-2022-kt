package days

import readInput

fun main(){
    val inputLines = readInput("day4_1.test")
    val result = inputLines.count {
        val line = it.split(",")
        val range1 = line[0]
        val range2 = line[1]
        val startR1 = range1.split("-")[0].toInt()
        val startR2 = range2.split("-")[0].toInt()
        val endR1 = range1.split("-")[1].toInt()
        val endR2 = range2.split("-")[1].toInt()
        //part 1
        //(startR1 <= startR2 && endR1 >= endR2) || (startR1 >= startR2 && endR1 <= endR2)
        //part 2
        (endR1 in startR2..endR2 || endR2 in startR1..endR1)
    }
    println(result)
}