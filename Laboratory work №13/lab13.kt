fun sumDigit(number:Int):Int{
    var tempNumber:Int = number
    var sum:Int = 0
    while(tempNumber != 0){
        sum += tempNumber%10
        tempNumber /= 10
    }
    return sum
}

fun mulDigit(number:Int):Int{
    var tempNumber:Int = number
    var mul:Int = 1
    while(tempNumber != 0){
        mul *= tempNumber%10
        tempNumber /= 10
    }
    return mul
}

fun minDigit(number:Int):Int{
    var tempNumber:Int = number
    var digit:Int
    var min:Int = 9
    while(tempNumber != 0){
        digit = tempNumber%10
        if (digit < min)
            min = digit
        tempNumber /= 10
    }
    return min
}

fun maxDigit(number:Int):Int{
    var tempNumber:Int = number
    var digit:Int
    var max:Int = 0
    while(tempNumber != 0){
        digit = tempNumber%10
        if (digit > max)
            max = digit
        tempNumber /= 10
    }
    return max
}

fun main(args: Array<String>) {
    println("\nHello, input some number (integer):")
    val number = readLine()!!.toInt()
    println("\nDigit sum of your number is: ${sumDigit(number)}")
}
