import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

open class Equipment(
    open val price: Int,
    val name: String
)

open class Composite(name: String) : Equipment(0, name) {

    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.sumOf { it.price }


    fun add(equipment: Equipment) =
        apply { equipments.add(equipment) }
}

class PersonalComputer : Composite("PC")
class Processor : Equipment(1070, "Processor")
class HardDrive : Equipment(250, "Hard Drive")
class Memory : Equipment(280, "Memory")


class CompositeTest {

    @Test
    fun Composite() {
        val pc = PersonalComputer()
            .add(Processor())
            .add(HardDrive())
            .add(Memory())

        println(pc.price)

        Assertions.assertTrue(pc.name == "PC")
        Assertions.assertTrue(pc.price == 1600)
    }
}