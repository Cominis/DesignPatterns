import creational.builder.Builder
import creational.builder.InheritanceBuilder

private fun showMe(showcase: PatternShowcase) = showcase.start()

fun main(){

    showMe(Builder)
    showMe(InheritanceBuilder)
}

