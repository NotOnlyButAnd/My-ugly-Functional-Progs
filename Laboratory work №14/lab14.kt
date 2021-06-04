// task 1
fun sumDigUp(number:Int):Int=  if (number != 0) number%10 + sumDigUp(number/10) else 0

// task 2
fun sumDigDown(number:Int, sum:Int):Int= if (number != 0) sumDigDown(number/10,sum + number%10) else sum

tailrec fun sumDigTail(number:Int,sum:Int):Int= if (number != 0) sumDigDown(number/10,sum + number%10) else sum

fun main() {
    //print(sumDigUp(333))
    //print(sumDigDown(122,0))
    print(sumDigTail(312,0))
}
