package days

import readInputAsString

val root = Dir("/")

fun main() {
    val inputS = readInputAsString("day7_1.prod")
    var currentDir = root
    val context = Context(currentDir)

    inputS.split("$")
        .drop(1)
        .forEach { cmdAndOutput ->
            println("process $cmdAndOutput")
            val split = cmdAndOutput.split("\n")
            val cmd = split[0].trim()
            println("processing command [$cmd]")
            when {
                cmd.startsWith("cd") -> {
                    context.cd(cmd.substringAfter("cd "))
                }

                cmd == "ls" -> {
                    val output = split.drop(1)
                    println("processing ls: [$cmd], output: $output")
                    output
                        .filterNot { it.isBlank() }
                        .forEach {
                            if (it.startsWith("dir")) {
                                context.dir.dirs.add(
                                    Dir(
                                        name = it.substringAfter("dir "),
                                        parent = context.dir
                                    )
                                )
                            } else {
                                context.dir.files.add(File.parse(it))
                            }
                        }
                }
            }

        }

    println(root)
    val allDirs = getDirs(root)

    println(allDirs.map { it.name })

    val result1 = allDirs
        .filter { it.size <= 100000 }
        .sumOf { it.size }

    println(result1)

    val total = 70000000
    val reqFree = 30000000
    val nowFree = total - root.size

    println("now free :$nowFree")

    val toDelete = reqFree - nowFree
    println("need to delete :$toDelete")

    val resultToDelete = allDirs.filter { it.size> toDelete}
        .minBy { it.size }
    println(resultToDelete.size)


}


fun getDirs(parent: Dir): MutableList<Dir> {
    val child = mutableListOf<Dir>()
    parent.dirs.forEach {
        child.add(it)
        child.addAll(getDirs(it))
    }
    return child
}

data class File(val name: String, val size: Long) {
    companion object {
        fun parse(input: String): File {
            val ar = input.split(" ")
            if (ar.size < 2) error("unexpected file input :$input")
            return File(name = ar[1], size = ar[0].toLong())
        }
    }
}

data class Dir(val name: String, val parent: Dir? = null) {
    val files = mutableListOf<File>()
    val dirs = mutableListOf<Dir>()

    val size: Long
        get() = files.sumOf { it.size } + dirs.sumOf { it.size }
}


data class Context(var dir: Dir) {
    var path: String = ""

    fun cd(input: String) {
        when (input) {
            "/" -> goToRoot()
            ".." -> up()
            else -> down(input)
        }
    }

    private fun up() {
        path = path.substringBeforeLast("/")
        dir = dir.parent ?: error("up error, context: [$this]")
    }

    private fun goToRoot() {
        path = "/"
        dir = root
    }

    private fun down(name: String) {
        path = "$path/$name"
        dir = dir.dirs.firstOrNull { it.name == name } ?: error("down error $name, context: [$this]")
    }
}