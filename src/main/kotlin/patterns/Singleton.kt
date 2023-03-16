import org.junit.jupiter.api.Test

object PrinterDriver {
    init {
        println("Initializing with object: $this")
    }

    fun print(): PrinterDriver =
        apply { println("Printing with object: $this") }
}

class SingletonTest {

    @Test
    fun Singleton() {
        println("Start")
        val printerFirst = PrinterDriver.print()
        val printerSecond = PrinterDriver.print()

        assert(printerFirst == PrinterDriver)
        assert(printerSecond == PrinterDriver)
    }
}
