package org.example

import java.io.OutputStream
import java.io.PrintStream
import java.time.Clock
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

val runnable = {
    `thread started`()
    `thread sleep`(1000)
    `thread finished`()
}

val utc: Clock = Clock.systemUTC()
fun time(): LocalTime = LocalTime.ofInstant(utc.instant(), ZoneOffset.UTC)

fun log(message: String) = println("[${time()}] [${Thread.currentThread().name}] $message")
fun `thread started`() = log("started")
fun `thread sleep`(ms: Long = 500) { log("sleep for $ms ms"); Thread.sleep(ms); log("waked up") }
fun `thread finished`() = log("finished")
fun delimiter() = println("\n----------------------\n")

const val log = false
var `threads-pool` = 6
var `threads-num` = 100
var `increments-num` = 100_000
const val `elements-num` = 10

val executor: ExecutorService = Executors.newFixedThreadPool(`threads-pool`)

fun experiment(block: (Int) -> Unit): Long = measureTimeMillis {
    (1..`threads-num`).map {
        executor.submit { block(it) }
    }.forEach { future ->
        try { future.get() }
        catch (e: Exception) { log("oh jeez, ${e::class.simpleName}[${e.message}]") }
    }
}

fun warmup() {
    val out = System.out
    System.setOut(PrintStream(object : OutputStream() { override fun write(b: Int) {} }))
    val memo = `threads-num` to `increments-num`
    `threads-num` = 2
    `increments-num` = 100
    example5()
    example6()
    example7()
    example8()
    example9()
    example10()
    example11()
    System.setOut(out)
    `threads-num` = memo.first
    `increments-num` = memo.second
}
