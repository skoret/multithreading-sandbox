package org.example

import java.util.UUID.randomUUID
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun example1() {
    val thread1 = Thread(runnable)

    log("run new thread from main thread")
    thread1.start()

    `thread sleep`()

    log("wait new thread")
    thread1.join()

    log("run new second thread from main thread")
    val thread2 = thread(start = true, isDaemon = false, name = "handy-kt-thread") {
        `thread started`()
        `thread sleep`(1000)
        `thread finished`()
    }

    log("wait new second thread")
    thread2.join()
    `thread finished`()
}

fun example2() {
//    val executor = ThreadPoolExecutor(?, ?, ?)
    val executor = Executors.newSingleThreadExecutor()

    log("run task in executor's pool")
    executor.execute(runnable)
    log("wait a while for task execution")
    `thread sleep`(1500)
    executor.shutdown()
}

fun example3() {
    val executor = Executors.newFixedThreadPool(2)

    log("run first task in executor's pool")
    val future1 = executor.submit(Callable {
        `thread started`()
        `thread sleep`(1000)
        val result = randomUUID()
        log("finished with result $result")
        result
    })

    log("run second task in executor's pool")
    val future2 = executor.submit {
        `thread started`()
        `thread sleep`(1500)
        `thread finished`()
    }

    log("wait for result from first task")
    val result = future1.get()
    log("result from first task: $result")

    log("cancel/interrupt second task")
    val cancelled = future2.cancel(true)
    log("second task was cancelled=$cancelled and finished=${future2.isDone}")
    executor.shutdown()
}

fun example4() {
    val executor = Executors.newScheduledThreadPool(2)
    val task1 = {
        log("task#1 started and sleep for 400 ms")
        Thread.sleep(400)
        log("task#1 finished")
    }
    val delays = listOf(400L, 500L)
    val task2 = {
        val ms = delays.random()
        log("task#2 started and sleep for $ms ms")
        Thread.sleep(ms)
        log("task#2 finished")
    }

    log("schedule task#1 at fixed rate 500 ms")
    executor.scheduleAtFixedRate(task1, 0, 500, TimeUnit.MILLISECONDS)

    log("schedule task#2 with fixed delay 400 ms")
    executor.scheduleWithFixedDelay(task2, 0, 100, TimeUnit.MILLISECONDS)

    log("wait for 3 sec and shutting down executor"); Thread.sleep(3_000)
    executor.shutdown()
}

fun main() {
    example1()
    delimiter()

    example2()
    delimiter()

    example3()
    delimiter()

    example4()
}
