// task 1
fun sumDigUp(number:Int):Int=  if (number != 0) number%10 + sumDigUp(number/10) else 0

// task 2
fun sumDigDown(number:Int, sum:Int):Int= if (number != 0) sumDigDown(number/10,sum + number%10) else sum

tailrec fun sumDigTail(number:Int,sum:Int):Int= if (number != 0) sumDigTail(number/10,sum + number%10) else sum

// task 3
fun mulDigUp(number:Int):Int= if(number != 0) number%10 * mulDigUp(number/10) else 1

tailrec fun mulDigTail(number:Int,mul:Int):Int= if (number != 0) mulDigTail(number/10,mul * number%10) else mul

fun maxDigUp(number:Int):Int=
    if(number != 0)
        if (number%10 > maxDigUp(number/10))
            number%10
        else
            maxDigUp(number/10)
    else
        0

tailrec fun maxDigTail(number:Int,max:Int):Int=
    if(number != 0)
        if(number%10 > max)
            maxDigTail(number/10,number%10)
        else
            maxDigTail(number/10,max)
    else
        max

fun minDigUp(number:Int):Int=
    if(number != 0)
        if (number%10 < minDigUp(number/10))
            number%10
        else
            minDigUp(number/10)
    else
        9

tailrec fun minDigTail(number:Int,min:Int):Int=
    if(number != 0)
        if(number%10 < min)
            minDigTail(number/10,number%10)
        else
            minDigTail(number/10,min)
    else
        min

fun main() {
    //print(sumDigUp(333))
    //print(sumDigDown(122,0))
    //print(sumDigTail(312,0))
    //print(mulDigUp(1201))
    //print(mulDigTail(1201,1))
    //print(maxDigUp(531641))
    //print(maxDigTail(41234123,0))
    //print(minDigUp(52423))
    print(minDigTail(57263,9))
}
