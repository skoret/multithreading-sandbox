package org.example

import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

fun example14() {
//    val list = mutableListOf<Int>()
    val list = ArrayList<Int>(`threads-num` * `elements-num`)
    log("run $`threads-num` threads with task 'add and remove $`elements-num` elements to list'")
    experiment { i ->
        if (log) log("add $i to list $`elements-num` times, list.size=${list.size}")
        repeat(`elements-num`) { list.add(i) }
        if (log) log("remove $i from list $`elements-num` times, list.size=${list.size}")
        repeat(`elements-num`) { list.remove(i) }
        if (log) log("task for $i element finished, list.size=${list.size}")
    }
    log("$`threads-num` threads finished with list.size=${list.size}")
}

fun example15() {
    val list = Collections.synchronizedList(mutableListOf<Int>())
    log("run $`threads-num` threads with task 'add and remove $`elements-num` elements to synced list'")
    experiment { i ->
        if (log) log("add $i to list $`elements-num` times, list.size=${list.size}")
        repeat(`elements-num`) { list.add(i) }
        if (log) log("remove $i to list $`elements-num` times, list.size=${list.size}")
        repeat(`elements-num`) { list.remove(i) }
        if (log) log("task for $i element finished, list.size=${list.size}")
    }
    log("$`threads-num` threads finished with list.size=${list.size}")
}

fun example16() {
    val map = hashMapOf("counter" to 0)
    log("run $`threads-num` threads with task 'increment counter by $`increments-num`' on hash map")
    experiment {
        if (log) log("start with counter=${map["counter"]}")
        repeat(`increments-num`) { map.computeIfPresent("counter") { _, value -> value + 1 } }
        if (log) log("finish with counter=${map["counter"]}")
    }
    log("$`threads-num` threads finished with counter=${map["counter"]}")
}

fun example17() {
    val map = ConcurrentHashMap(mapOf("counter" to 0))
    log("run $`threads-num` threads with task 'increment counter by $`increments-num`' on concurrent hash map")
    experiment {
        if (log) log("start with counter=${map["counter"]}")
        repeat(`increments-num`) { map.computeIfPresent("counter") { _, value -> value + 1 } }
        if (log) log("finish with counter=${map["counter"]}")
    }
    log("$`threads-num` threads finished with counter=${map["counter"]}")
}

fun main() {
    example14()
    delimiter()

    example15()
    delimiter()

    example16()
    delimiter()

    example17()

    executor.shutdown()
}
