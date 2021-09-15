package org.example

import java.io.OutputStream
import java.io.PrintStream
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun example5() {
    var counter = 0
    log("run $`threads-num` threads with task 'increment counter by $`increments-num`'")
    val millis = measureTimeMillis {
        (1..`threads-num`).map {
            thread {
                if (log) log("start with counter=$counter")
                repeat(`increments-num`) { counter++ }
                if (log) log("finish with counter=$counter")
            }
        }.forEach { thread -> thread.join() }
    }
    log("$`threads-num` threads finished with counter=$counter and time=$millis ms")
}

@Volatile
var `volatile-counter` = 0
fun example6() {
    `volatile-counter` = 0
    log("run $`threads-num` threads with task 'increment volatile-counter by $`increments-num`'")
    val millis = measureTimeMillis {
        (1..`threads-num`).map {
            thread {
                if (log) log("start with volatile-counter=$`volatile-counter`")
                repeat(`increments-num`) { `volatile-counter`++ }
                if (log) log("finish with volatile-counter=$`volatile-counter`")
            }
        }.forEach { thread -> thread.join() }
    }
    log("$`threads-num` threads finished with counter=$`volatile-counter` and time=$millis ms")
}

fun example7() {
    var counter = 0
    @Synchronized
    fun increment() { counter++ }
    log("run $`threads-num` threads with task 'increment counter by $`increments-num` in synchronized func per increment'")
    val millis = measureTimeMillis {
        (1..`threads-num`).map {
            thread {
                if (log) log("start with counter=$counter")
                repeat(`increments-num`) { increment() }
                if (log) log("finish with counter=$counter")
            }
        }.forEach { thread -> thread.join() }
    }
    log("$`threads-num` threads finished with counter=$counter and time=$millis ms")
}

fun example8() {
    var counter = 0
    @Synchronized
    fun increment() {
        repeat(`increments-num`) { counter++ }
    }
    log("run $`threads-num` threads with task 'increment counter by $`increments-num` in synchronized func per thread'")
    val millis = measureTimeMillis {
        (1..`threads-num`).map {
            thread {
                if (log) log("start with counter=$counter")
                increment()
                if (log) log("finish with counter=$counter")
            }
        }.forEach { thread -> thread.join() }
    }
    log("$`threads-num` threads finished with counter=$counter and time=$millis ms")
}

fun example9() {
    var counter = 0
    val lock = Any()
    log("run $`threads-num` threads with task 'increment counter by $`increments-num` and sync on each increment'")
    val millis = measureTimeMillis {
        (1..`threads-num`).map {
            thread {
                if (log) log("start with counter=$counter")
                repeat(`increments-num`) { synchronized(lock) { counter++ } }
                if (log) log("finish with counter=$counter")
            }
        }.forEach { thread -> thread.join() }
    }
    log("$`threads-num` threads finished with counter=$counter and time=$millis ms")
}

fun example10() {
    var counter = 0
    val lock = Any()
    log("run $`threads-num` threads with task 'increment counter by $`increments-num` and sync once for all increments'")
    val millis = measureTimeMillis {
        (1..`threads-num`).map {
            thread {
                if (log) log("start with counter=$counter")
                synchronized(lock) { repeat(`increments-num`) { counter++ } }
                if (log) log("finish with counter=$counter")
            }
        }.forEach { thread -> thread.join() }
    }
    log("$`threads-num` threads finished with counter=$counter and time=$millis ms")
}

fun main() {
    warmup()
    delimiter()

    example5()
    delimiter()

    example6()
    delimiter()

    example7()
    delimiter()

    example8()
    delimiter()

    example9()
    delimiter()

    example10()
}

var log = false
var `threads-num` = 100
var `increments-num` = 1_000_000

fun warmup() {
    val out = System.out
    System.setOut(PrintStream(object : OutputStream() { override fun write(b: Int) {} }))
    `threads-num` = 10
    `increments-num` = 100
    example5()
    example6()
    example7()
    example8()
    example9()
    example10()
    example11()
    System.setOut(out)
    `threads-num` = 100
    `increments-num` = 1_000_000

}