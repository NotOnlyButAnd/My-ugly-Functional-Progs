fun askLanguage(Lang:String, userName:String) =
    when{
    Lang == "kotlin" || Lang == "prolog" -> println("\nWhat a TOADY,$userName!")
        else -> println("\nOk, $userName, that's fine")
    }

fun main(args: Array<String>) {
    println("What's your name?")
    val name = readLine()
    println("\nHello, $name \nWhat's your favourite language?")
    val lang = readLine()
    askLanguage(lang.toString(), name.toString())
}
