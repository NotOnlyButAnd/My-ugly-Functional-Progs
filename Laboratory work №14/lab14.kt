import java.math.BigInteger
import java.util.*
import kotlin.math.max
import kotlin.math.min

///////////////// task 1 /////////////////
fun sumDigUp(number:Int):Int=  if (number != 0) number%10 + sumDigUp(number/10) else 0

///////////////// task 2 /////////////////
fun sumDigDown(number:Int, sum:Int):Int= if (number != 0) sumDigDown(number/10,sum + number%10) else sum

tailrec fun sumDigTail(number:Int,sum:Int):Int= if (number != 0) sumDigTail(number/10,sum + number%10) else sum

///////////////// task 3 /////////////////
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

///////////////// task 4 /////////////////
fun goOnNumber1(number:Int, operation: (Int,Int)-> Int, init:Int = 0):Int=
    if(number != 0)
        operation(number%10, goOnNumber1(number/10, operation, init))
    else
        init

///////////////// task 5 /////////////////
fun goOnNumber2(number:Int, operation: (Int,Int)-> Int, init:Int = 0, check: (Int) -> Boolean):Int=
    if(number != 0)
        if (check(number%10))
            operation(number%10, goOnNumber2(number/10, operation, init, check))
        else
            goOnNumber2(number/10,operation, init, check) * 10 + number%10
    else
        init

///////////////// task 7 /////////////////
tailrec fun countEvenDigNotMutPrime(number1:Int, number2:Int = number1 - 1, count:Int = 0):Int=
    if(number2 != 1)
        if (number2%2 == 0 && nod(number1,number2) != 1)
            countEvenDigNotMutPrime(number1, number2 - 1, count + 1)
        else
            countEvenDigNotMutPrime(number1, number2 - 1, count)
    else
        count

tailrec fun nod(number1:Int, number2:Int, curDel:Int = number2, del:Int = 1):Int=
    if (curDel != 1)
        if (number1%curDel == 0 && number2%curDel == 0)
            nod(number1, number2, curDel - 1, curDel)
        else
            nod(number1, number2, curDel - 1, del)
    else
        del

tailrec fun maxNotDiv3 (number:Int,max:Int = -1):Int=
    if(number != 0)
        if(number%10 > max && (number%10)%3 != 0)
            maxNotDiv3(number/10,number%10)
        else
            maxNotDiv3(number/10,max)
    else
        max

tailrec fun smallestDivisor(number:Int, curDel:Int = number / 2, del:Int = number):Int=
    if (curDel != 1)
        if (number%curDel == 0)
            smallestDivisor(number, curDel - 1, curDel)
        else
            smallestDivisor(number, curDel - 1, del)
    else
        del

tailrec fun maxNotMutPrimeNotDivSD(number1:Int, number2:Int = number1 - 1, smallestDel:Int = smallestDivisor(number1), max:Int = 1):Int=
    if(number2 != 1)
        if (number2 % smallestDel != 0 && nod(number1,number2) != 1 && number2 > max)
            maxNotMutPrimeNotDivSD(number1, number2 - 1, smallestDel, number2)
        else
            maxNotMutPrimeNotDivSD(number1, number2 - 1, smallestDel, max)
    else
        max

tailrec fun sumDigLess5(number:Int,sum:Int = 0):Int=
    if (number != 0)
        if (number%10 < 5)
            sumDigLess5(number/10,sum + number%10)
        else
            sumDigLess5(number/10,sum)
    else sum

fun task_7_3(number:Int):Int=
    maxNotMutPrimeNotDivSD(number) * sumDigLess5(number)

///////////////// task 8 /////////////////

// Выбор операции над числами
fun op(operator: String?): (Int, Int) -> Int =
    when (operator) {
        "+" -> {a: Int, b: Int -> a + b}
        "-" -> {a: Int, b: Int -> a - b}
        "*" -> {a: Int, b: Int -> a * b}
        else -> throw IllegalArgumentException("Try again")
    }

///////////////// Задание 9.20 /////////////////

// tailrec fun mulDigTail(number:Int,mul:Int):Int=
//      if (number != 0)
//          mulDigTail(number/10,mul * number%10)
//      else
//          mul

// Хвостовая не работает(
//tailrec fun factorial(number:BigInteger, fac:BigInteger = BigInteger.ONE):BigInteger=
//    if (number != BigInteger.ONE)
//        factorial(number - BigInteger.ONE, fac * number)
//    else
//        BigInteger.ONE

fun factorial(number:BigInteger):BigInteger=
    if (number != BigInteger.ONE)
        number * factorial(number - BigInteger.ONE)
    else
        BigInteger.ONE

tailrec fun digitSum(number:BigInteger, sum:BigInteger = BigInteger.ZERO):BigInteger=
    if (number != BigInteger.ZERO)
        digitSum(number/BigInteger.TEN, sum + number%BigInteger.TEN)
    else
        sum

///////////////// Задание 9.40 /////////////////
tailrec fun countDigs(number1:Int, count:Int = 0):Int=
    if(number1 != 0)
        countDigs(number1/10, count + 1)
    else
        count

tailrec fun getDigFromRight(number1:Int, curI:Int = 1, i:Int):Int=
    if(curI != i)
        getDigFromRight(number1/10, curI + 1, i)
    else
        number1%10

//tailrec fun getSomeStr(curNumber:Int = 1, curN:Int = 1, mul:Int = 1, N:Int = 10):Int=
//    if (curN < 101)
//        if (curN >= N)
//            getSomeStr(curNumber + 1, curN + countDigs(curNumber), mul * getDigFromRight(curNumber,1, curN - N + 1), N * 10)
//        else
//            getSomeStr(curNumber + 1,curN + countDigs(curNumber), mul, N)
//    else
//        mul

tailrec fun getSomeStr(curNumber:Int = 1, curN:Int = 1, mul:Int = 1, N:Int = 10):Int{
    if (curN < 1000010)
        if (curN + countDigs(curNumber) - 1 >= N)
        {
            println("curNumber - $curNumber; curN - $curN; n - $N; dig = ${getDigFromRight(curNumber,1, curN + countDigs(curNumber) - N)}")
            return getSomeStr(curNumber + 1, curN + countDigs(curNumber), mul * getDigFromRight(curNumber,1, curN + countDigs(curNumber) - N), N * 10)
        }
        else
        {
            println("curNumber - $curNumber; curN - $curN; n - $N;")
            return getSomeStr(curNumber + 1,curN + countDigs(curNumber), mul, N)
        }
    else
        return mul
}

// Выбор операции над числом 2
fun op2(operator: String?): (Int) -> Int =
    when (operator) {
        "1" -> sumDigUp() 
        "2" -> mulDigUp()
        "3" -> maxDigUp()
        "4" -> minDigUp()
        else -> throw IllegalArgumentException("Try again")
    }

// Выбор операции над числом 3
fun op3(operator: String?): (Int) -> Int =
    when (operator) {
        "1" -> {a -> sumDigUp(a)}
        "2" -> {a -> mulDigUp(a)} 
        "3" -> {a -> maxDigUp(a)}
        "4" -> {a -> minDigUp(a)}
        else -> throw IllegalArgumentException("Try again")
    }


fun main() {
    //print(sumDigUp(333))
    //print(sumDigDown(122,0))
    //print(sumDigTail(312,0))
    //print(mulDigUp(1201))
    //print(mulDigTail(121,1))
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
    //print(goOnNumber2(28511594, {a, b -> b*10 + a-5},0, {a -> a > 5}))

    // проверка работы НОД (14,8 -> 2), (14,7 -> 7), (26,13 -> 13)
    // когда меньшее число на первом месте - тоже работает
    //print(nod(8,14))

    // Метод 1. Задание 7
    // Делители 14: 1,2,7
    // Числа меньше 14: 2,3,4,5,6,7,8,9,10,11,12,13
    // Чётные: 2,4,6,8,10,12. НЕ взаимно простые - все (общий делитель 2)
    //print(countEvenDigNotMutPrime(14))

    // Метод 2. Задание 7
    //print(maxNotDiv3(333))

    // Метод 3. Задание 7
    //print(smallestDivisor(49))
    // Из проверки метода 1 делаем вывод, что наибольшее не вз пр с 14, которое не делится на наим делитель = 2, это число 7
    //print(maxNotMutPrimeNotDivSD(14))
    //print(sumDigLess5(14))
    // максимальный бла бла бла = 7, сумма цифр меньше 5 = 5 => 7 * 5 = 35
    // print(task_7_3(14))

    // Задание 8
    /*
    val scanner = Scanner(System.`in`)

    print("Введите число1: ")
    val num1 = scanner.nextInt()

    print("Введите число2: ")
    val num2 = scanner.nextInt()

    print("Выберите, какую операцию хотите использовать (+,-,*): ")
    var method: String? = scanner.next()

    print("Результат: ")
    print(op(method)(num1,num2))
     */

// Задание 8*
    /*
    val scanner = Scanner(System.`in`)

    print("Введите число: ")
    val num = scanner.nextInt()

    println("Выберите, какую операцию хотите использовать (+,-,*): ")
    println("1 -> сумма цифр числа;)
    println("2 -> произведение цифр числа;)
    println("3 -> максимальная цифра числа;)
    println("4 -> минимальная цифра числа;)
    var method: String? = scanner.next()

    print("Результат: ")
    print(op2(method)(num))
     */

    // Задание 9.20
    /*
    val n:BigInteger = BigInteger.valueOf(100)
    //print("Factorial ${n} = ${factorial(n)}")
    print("Digit sum factorial ${n} = ${digitSum(factorial(n))}")
     */

    // Задание 9.40
    //print(getDigFromRight(1234,1,2))
    print(getSomeStr())
}
