package org.example

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

fun example11() {
    val counter = AtomicInteger()
    log("run $`threads-num` threads with task 'increment atomic-counter by $`increments-num`'")
    val millis = experiment {
        if (log) log("start with counter=${counter.get()}")
        repeat(`increments-num`) { counter.incrementAndGet() }
        if (log) log("finish with counter=${counter.get()}")
    }
    log("$`threads-num` threads finished with counter=${counter.get()} and time=$millis ms")
}

fun example12() {
    var counter = 0
    val lock = ReentrantLock()
    log("run $`threads-num` threads with task 'increment counter by $`increments-num`' under lock per increment")
    val millis = experiment {
        if (log) log("start with counter=${counter}")
        repeat(`increments-num`) {
            try {
                lock.lock()
                counter++
            } finally {
                lock.unlock()
            }
        }
        if (log) log("finish with counter=${counter}")
    }
    log("$`threads-num` threads finished with counter=${counter} and time=$millis ms")
}

fun example13() {
    var counter = 0
    val lock = ReentrantLock()
    log("run $`threads-num` threads with task 'increment counter by $`increments-num`' under lock per thread")
    val millis = experiment {
        if (log) log("start with counter=${counter}")
        try {
            lock.lock()
            repeat(`increments-num`) { counter++ }
        } finally {
            lock.unlock()
        }
        if (log) log("finish with counter=${counter}")
    }
    log("$`threads-num` threads finished with counter=${counter} and time=$millis ms")
}

fun main() {
    warmup()

    example9()
    delimiter()

    example10()
    delimiter()

    example11()
    delimiter()

    example12()
    delimiter()

    example13()

    executor.shutdown()
}
