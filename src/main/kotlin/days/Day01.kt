package days

import readInputAsString

const val day_1_demo_1_input_file = "Day1_demo.txt"
const val day_1_demo_1_result = 24000
const val day_1_demo_2_result = 45000


val Day01: (input: String) -> List<Int> = {
    it.split("\n\n")
        .map { it.split("\n").sumOf { it.toInt() } }
        .sortedDescending()
}

val Day01_max: (input: String) -> Int = {
    it.split("\n\n").maxOf { it.split("\n").sumOf { it.toInt() } }
}

val Day1Part1: (String) -> Int = { Day01(it).first() }
val Day1Part2: (String) -> Int = { Day01(it).take(3).sum() }

fun main() {
    val input = readInputAsString(day_1_demo_1_input_file)

    val part1result = Day1Part1(input)
    check(part1result == day_1_demo_1_result)

    val part2result = Day1Part2(input)
    check(part2result == day_1_demo_2_result)
}