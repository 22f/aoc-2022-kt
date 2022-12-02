package jmh
import days.Day01
import days.Day01_max


import days.day_1_demo_1_input_file
import org.openjdk.jmh.annotations.*
import readInputAsString
import java.util.concurrent.TimeUnit


@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
open class Day1JmhTest {

    private lateinit var input : String

    @Setup
    fun setUp(){
        println("setUpCalled")
        input = readInputAsString(day_1_demo_1_input_file)
    }

    @Benchmark
    fun day1(){
        Day01(input)
    }

    @Benchmark
    fun day1WithMax(){
        Day01_max(input)
    }
}