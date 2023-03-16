import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    println("Hello World!")
    flowConflate()

}

/**
 * para colectores lentos este operador no espera por tanto en este ejmplo
 * si el colector tiene un delay de 1 segundo el emisor sigue trabajando
 * y una vez que el colector recolecta nuevamente al pasar 1 segundo ya pasaron 10 datos emitidos.
 */
fun flowConflate() =
    runBlocking {
        val flow = flow {
            for (i in 1..30) {
                delay(100)
                emit(i)
            }
        }
        val result = flow.conflate().onEach {
            println(it)
            delay(1000)
        }.toList()
        assertEquals(listOf(1, 10, 20, 30), result)
    }


/**
 * el ejemplo es una sumatoria pero el general hay un acumulador que mantiene el resultado
 * y el value es el valor que viene desde el flow en ese momento
 */
fun flowReduce() = runBlocking {
    val saving =
        getDataByFlow()
            .map {
                it.toInt()
            }
            .reduce { accumulator, value ->
                accumulator + value
            }
    println(saving)

}

fun flowFilter() = runBlocking {
    getDataByFlow()
        .map {
            it.toInt()
        }
        .filter {
            it <= 50
        }.collect {
            println(it)
        }
}

fun flowMap() = runBlocking {
    getDataByFlow().map {
        it.map1()
        it.map2()
    }.collect {
        println(it)
    }
}

fun getDataByFlow() = flow {
    for (i in 1..100)
        emit("$i")
}

fun String.map1(): String = "${this.toInt() + 10}"
fun String.map2(): String = "${this.toInt() + 100}"