package org.example

fun example5() {
    var counter = 0
    log("run $`threads-num` threads with task 'increment counter by $`increments-num`'")
    val millis = experiment {
        if (log) log("start with counter=$counter")
        repeat(`increments-num`) { counter++ }
        if (log) log("finish with counter=$counter")
    }
    log("$`threads-num` threads finished with counter=$counter and time=$millis ms")
}

@Volatile
var `volatile-counter` = 0
fun example6() {
    `volatile-counter` = 0
    log("run $`threads-num` threads with task 'increment volatile-counter by $`increments-num`'")
    val millis = experiment {
        if (log) log("start with volatile-counter=$`volatile-counter`")
        repeat(`increments-num`) { `volatile-counter`++ }
        if (log) log("finish with volatile-counter=$`volatile-counter`")
    }
    log("$`threads-num` threads finished with counter=$`volatile-counter` and time=$millis ms")
}

fun example7() {
    var counter = 0
    @Synchronized
    fun increment() { counter++ }

    log("run $`threads-num` threads with task 'increment counter by $`increments-num` in synchronized func per increment'")
    val millis = experiment {
        if (log) log("start with counter=$counter")
        repeat(`increments-num`) { increment() }
        if (log) log("finish with counter=$counter")
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
    val millis = experiment {
        if (log) log("start with counter=$counter")
        increment()
        if (log) log("finish with counter=$counter")
    }
    log("$`threads-num` threads finished with counter=$counter and time=$millis ms")
}

fun example9() {
    var counter = 0
    val lock = Any()
    log("run $`threads-num` threads with task 'increment counter by $`increments-num` and sync on each increment'")
    val millis = experiment {
        if (log) log("start with counter=$counter")
        repeat(`increments-num`) { synchronized(lock) { counter++ } }
        if (log) log("finish with counter=$counter")
    }
    log("$`threads-num` threads finished with counter=$counter and time=$millis ms")
}

fun example10() {
    var counter = 0
    val lock = Any()
    log("run $`threads-num` threads with task 'increment counter by $`increments-num` and sync once for all increments'")
    val millis = experiment {
        if (log) log("start with counter=$counter")
        synchronized(lock) { repeat(`increments-num`) { counter++ } }
        if (log) log("finish with counter=$counter")
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

    executor.shutdown()
}
