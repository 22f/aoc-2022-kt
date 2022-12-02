package days

import days.Shape.Paper.getByResult
import readInputAsString

fun main() {
    val input = readInputAsString("day2_1.prod")

    val map = mapOf(
        "A" to Shape.Rock, "X" to Shape.Rock,
        "B" to Shape.Paper, "Y" to Shape.Paper,
        "C" to Shape.Scissors, "Z" to Shape.Scissors,
    )

    val result = input.split("\n").sumOf {
        val g = it.split(" ")
        val elf = map[g[0]]!!
        val player = map[g[1]]!!
        player.test(elf)
    }


    val map2 = mapOf(
        "X" to 0,
        "Y" to 3,
        "Z" to 6,
    )

    val result2 = input.split("\n").sumOf {
        val g = it.split(" ")
        val elf = map[g[0]]!!
        val secondColumnWithResult =  map2[g[1]]!!
        val player = elf.getByResult(secondColumnWithResult)
        secondColumnWithResult+player.selfPoint
    }

    println(result)
    println(result2)


}

sealed interface Shape {
    val selfPoint: Int
    val beat: Shape
    val lose: Shape


    object Rock : Shape {
        override val selfPoint: Int = 1
        override val beat: Shape = Scissors
        override val lose: Shape = Paper
    }

    object Paper : Shape {
        override val selfPoint: Int = 2
        override val beat: Shape = Rock
        override val lose: Shape = Scissors
    }

    object Scissors : Shape {
        override val selfPoint: Int = 3
        override val beat: Shape = Paper
        override val lose: Shape = Rock
    }


    fun test(other: Shape): Int {
        val bonus = when {
            this == other -> 3
            this.beat == other -> 6
            this.lose == other -> 0
            else -> error("impossible!")
        }
        return this.selfPoint + bonus
    }

    fun getByResult(bonusPoint: Int): Shape {
        return when (bonusPoint) {
            0 -> this.beat
            3 -> this
            6 -> this.lose
            else -> error("wtf")
        }
    }
}