const val day_1_demo_1 = "Day1_demo.txt"

fun main() {
    val valuesf: (fileName: String) -> List<Int> = { fileName ->
        readInputAsString(fileName)
            .split("\n\n")
            .map { it.split("\n").sumOf { it.toInt() } }
            .sortedDescending()
    }

    val values = valuesf(day_1_demo_1)

    val part1result = values[0]
    check(part1result == 24000)

    val part2result = values.take(3).sum()
    check(part2result == 45000)
}
