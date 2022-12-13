package days

import readInputAsString
import java.util.*

fun main(){
    val inputString = readInputAsString("day5_1.test")


    val startPositionsInput = inputString.split("\n\n")[0]
    val reversedStart = startPositionsInput.split("\n").reversed().drop(1)

    val size = reversedStart.first().split(" ").size
    val positions: List<Stack<Char>> = List(size) { Stack() }
    reversedStart.forEach {
        for ((index, i) in (it.indices step 4).withIndex()) {
            val res = it[i+1]
            if (res != ' ') {
               positions[index].push(res)
            }
        }
    }

    val actions = inputString.split("\n\n")[1].split("\n")
    val regex = Regex("move (?<itemsCount>\\d+) from (?<source>\\d) to (?<target>\\d)")
    actions.filter { it.isNotBlank() }.forEach {
        val groups = regex.find(it)?.groups ?: error("cannot find groups in string [$it]")
        val itemsCount = groups["itemsCount"]!!.value.toInt()
        val sourceId = groups["source"]!!.value.toInt()-1
        val targetId = groups["target"]!!.value.toInt()-1
        //part1
        //repeat(itemsCount){
        //    val popped = positions[sourceId].pop()
        //    positions[targetId].push(popped)
        //}
        //part2
        val tempStack : Stack<Char> = Stack()
        repeat(itemsCount){
            tempStack.push(positions[sourceId].pop())
        }
        repeat(itemsCount) {
            positions[targetId].push(tempStack.pop())
        }


    }

    val res = positions.map { it.peek() }
    println(res.joinToString(separator = ""))

}