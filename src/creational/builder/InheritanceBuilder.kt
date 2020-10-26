package creational.builder

import PatternShowcase

class Human(var name: String = "", var position: String = "")
{
    class Builder : HumanJobBuilder<Builder>()
}

abstract class HumanBuilder<ITSELF> where ITSELF : HumanBuilder<ITSELF>
{
    protected val human = Human()
    fun build() = human
}

open class HumanInfoBuilder<ITSELF> : HumanBuilder<HumanInfoBuilder<ITSELF>>()
    where ITSELF : HumanInfoBuilder<ITSELF>
{
    fun called(name: String) : ITSELF {
        human.name = name
        return this as ITSELF
    }
}

open class HumanJobBuilder<ITSELF> : HumanInfoBuilder<HumanJobBuilder<ITSELF>>()
    where ITSELF : HumanJobBuilder<ITSELF>
{
    fun worksAs(position: String) : ITSELF {
        human.position = position
        return this as ITSELF
    }
}

object InheritanceBuilder : PatternShowcase {

    override fun start() {
        builderWithRecursiveGenerics()
    }

    private fun builderWithRecursiveGenerics() {
        val person = Human.Builder()
                .worksAs("Programmer")
                .called("Daumntas")
                .build()

        Human() //works
        Human().name //works

        println(person)
        println()

    }

}