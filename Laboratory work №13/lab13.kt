fun sumDigit(number:Int):Int{
    var tempNumber:Int = number
    var digit:Int
    var sum:Int = 0
    while(tempNumber != 0){
        digit = tempNumber%10
        sum += digit
        tempNumber /= 10
    }
    return sum
}

fun main(args: Array<String>) {
    println("\nHello, input some number (integer):")
    val number = readLine()!!.toInt()
    println("\nDigit sum of your number is: ${sumDigit(number)}")
}
