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

// для 14 - 2,4,6,7,8,10,12 - их 6 штук
fun countChetNotVzProst(number:Int):Int {
    var tempNumber = 2
    var count = 0
    while (tempNumber < number) {
        var del = 2
        var flag: Boolean = false
        while (del <= tempNumber) {
            if (tempNumber % del == 0 && number % del == 0) {
                flag = true
                break
            }
            del++
        }
        if (flag && tempNumber % 2 == 0)
            count++
        tempNumber++
    }
    return count
}

fun main(args: Array<String>) {
    println("\nHello, input some number (integer):")
    val number = readLine()!!.toInt()
    println("\nCount: ${countChetNotVzProst(number)}")
}
