import kotlin.math.max
import kotlin.math.min

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

// task 4
fun goOnNumber1(number:Int, operation: (Int,Int)-> Int, init:Int = 0):Int=
    if(number != 0)
        operation(number%10, goOnNumber1(number/10, operation, init))
    else
        init

// task 5
fun goOnNumber2(number:Int, operation: (Int,Int)-> Int, init:Int = 0, check: (Int) -> Boolean):Int=
    if(number != 0)
        if (check(number%10))
            operation(number%10, goOnNumber2(number/10, operation, init, check))
        else
            goOnNumber2(number/10,operation, init, check) * 10 + number%10
    else
        init

fun main() {
    //print(sumDigUp(333))
    //print(sumDigDown(122,0))
    //print(sumDigTail(312,0))
    //print(mulDigUp(1201))
    //print(mulDigTail(1201,1))
    //print(maxDigUp(531641))
    //print(maxDigTail(41234123,0))
    //print(minDigUp(52423))
    //print(minDigTail(57263,9))

    //// по сути - сумма цифр числа
    //print(goOnNumber1(12345, {a, b -> a+b}))
    //// по сути - произведение цифр числа
    //print(goOnNumber1(123, {a, b -> a*b}, 1))
    //// по сути - минимальная цифра числа
    //print(goOnNumber1(23415, {a, b -> min(a,b)}, 9))
    //// по сути - максимальная цифра числа
    //print(goOnNumber1(22311, {a, b -> max(a,b)}))

    //// По сути - проверка четности и деление таких цифр на 2
    //print(goOnNumber2(2431354, {a, b -> b*10 + a/2},0, {a -> a%2 == 0}))
    //// По сути - проверка равна ли цифра 5 и замена ее на 0
    //print(goOnNumber2(24531534, {a, b -> b*10},0, {a -> a == 5}))
    //// По сути - проверка больше ли цифра чем 5. Если да - то отнять от нее 5
    print(goOnNumber2(28511594, {a, b -> b*10 + a-5},0, {a -> a > 5}))
}
