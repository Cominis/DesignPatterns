package creational.builder

import PatternShowcase
import java.lang.StringBuilder

class HtmlElement(private val name: String, private val text: String) {

    val elements = mutableListOf<HtmlElement>()

    private val indentSize = 2

    private fun toStringImpl(indent: Int) : String {
        val sb = StringBuilder()
        val tagIndent = " ".repeat(indentSize * indent)
        sb.append("$tagIndent<$name>\n")
        if(!text.isBlank()) {
            val textIndent = " ".repeat(indentSize * (indent + 1))
            sb.append("$textIndent$text\n")
        }

        for(e in elements) {
            val stringImpl = e.toStringImpl(indent + 1)
            sb.append(stringImpl)
        }

        sb.append("$tagIndent</$name>\n")

        return sb.toString()
    }

    override fun toString(): String {
        return toStringImpl(0)
    }
}

class HtmlBuilder(rootName: String) {

    var root = HtmlElement(rootName, "")

    //not fluent
    fun addChild(childName: String, childText: String) {
        val e = HtmlElement(childName, childText)
        root.elements.add(e)
    }

    fun addChildFluent(childName: String, childText: String) : HtmlBuilder {
        val e = HtmlElement(childName, childText)
        root.elements.add(e)
        return this
    }

    override fun toString(): String {
        return root.toString()
    }
}

object Builder : PatternShowcase
{
    override fun start() {
        usingStringBuilder()
        usingNonFluentBuilder()
        usingFluentBuilder()
    }

    private fun usingStringBuilder() {
        val words = listOf("hello", "world")
        val sb = StringBuilder()
        sb.append("<ul>")
        for( word in words)
            sb.append("<li>$word</li>")
        sb.append("</ul>")
        println("Using string creational.builder")
        println(sb.toString())
        println()
    }

    private fun usingNonFluentBuilder() {
        val builder = HtmlBuilder("ul")
        builder.addChild("li", "hello")
        builder.addChild("li", "world")
        println("Using non-fluent creational.builder")
        println(builder.toString())
        println()
    }

    private fun usingFluentBuilder() {
        val builder = HtmlBuilder("ul")
                .addChildFluent("li", "hello")
                .addChildFluent("li", "world")
        println("Using fluent creational.builder")
        println(builder.toString())
        println()
    }
}