package days

import readInputAsString

fun main() {
    val inputString = readInputAsString("day6_1.prod")
    var result = 0
    //for (i in inputString.indices -4) {
    //    if (inputString.substring(i, i + 4).toSet().size == 4)  {
    //        result = i + 4
    //        break
    //    }
    //}

    for (i in inputString.indices - 14) {
        if (inputString.substring(i, i + 14).toSet().size == 14)  {
            result = i + 14
            break
        }
    }
    println(result)
}