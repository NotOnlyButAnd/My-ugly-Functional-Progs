import java.io.File
import java.util.Scanner

// Создание массива
fun initArray():Array<Int> {
    val scanner = Scanner(System.`in`)
    print("Введите количество элементов массива: ")
    val size = scanner.nextInt()
    val array: Array<Int> = Array<Int>(size) { 0 }
    println("Вводите элементы массива...")
    return inputArray(0,array)
}

// Ввод элементов массива
tailrec fun inputArray(counter:Int, array: Array<Int>): Array<Int> =
    if(counter == array.size) array else
    {
        array[counter] = readLine()!!.toInt()
        inputArray(counter + 1, array)
    }

// Перебор элементов массива (без методов Array)
tailrec fun arrayOp(array: Iterator<Int>, f: (Int, Int) -> Int, acum:Int):Int =
    if(!array.hasNext())
        acum
    else
        arrayOp(array, f, f(array.next(), acum))

// Перебор элементов массива (с методами Array)
tailrec fun arrayOp(array: Array<Int>, f:(Int, Int) -> Int, acum:Int, counter:Int):Int =
    if (array.size == counter)
        acum
    else
        arrayOp(array, f, f(array[counter], acum), counter + 1)

// Сумма элементов массива
fun sumOfElem(array:Array<Int>):Int = arrayOp(array,{ elem:Int, sum:Int -> elem + sum }, 0, 0)

// Произведение элементов массива
fun mulOfElem(array:Array<Int>):Int = arrayOp(array,{ elem:Int, mul:Int -> elem * mul}, 1, 0)

// Минимальный элемент массива
fun minElem(array: Array<Int>): Int = arrayOp(array,{ elem:Int, min:Int -> if (elem < min) elem else min}, array[0], 0)

// Максимальный элемент массива
fun maxElem(array:Array<Int>): Int = arrayOp(array,{ elem:Int, max:Int -> if (elem > max) elem else max}, array[0], 0)

///////////////////////////////////////////
//////////     Ввод из файла    ///////////

fun arrayInputFile(input : Map<Int, Int>) : Array<Int> {
    val array:Array<Int> = Array(input.size){0}
    return arrayInputFile(array, 0, input.size, input)
}

// Заполнение массива элементами из файла
fun arrayInputFile(array : Array<Int>, counter : Int, size : Int, input : Map<Int, Int>) : Array<Int> =
    if (counter == size)
        array
    else {
        array[counter] = input[counter]!!
        arrayInputFile(array, counter + 1, size, input)
    }

// Организация чтения из файла
// В одной строке одно число, возвращает мэп индексированный
fun inputFile(fileName:String) : Array<Int> {
    val input = File(fileName).readLines()
        .withIndex() //Возвращает ленивую итерацию, которая обертывает каждый элемент исходного массива в IndexedValue, содержащий индекс этого элемента и сам элемент.
        .map { indexedValue -> indexedValue.index to indexedValue.value.toInt() }  // Создаёт мапу
        .toMap() //Возвращает мапу
    return arrayInputFile(input)
}

fun chooseInput(): Array<Int>{
    println("\n\nОткуда считывать массив?\n" +
            "1. Из файла\n" +
            "2. С клавиатуры")

    val choose = readLine()!!.toInt()
    if (choose == 1) {
        println("Введите имя файла: ")
        val name = readLine().toString()
        return inputFile("./src/${name}.txt")
    }
    else
        return initArray()
}

///////////////////////////////////////////
//////////     Задание 4.1.9    ///////////
fun task_4_1_9(array: Array<Int>): IntArray{
    val minimum = minElem(array)
    val indexOfLastMIn = array.indexOfLast { a -> a == minimum }
    return array.take(indexOfLastMIn).toIntArray()
}

///////////////////////////////////////////
//////////     Задание 4.2.10    //////////
tailrec fun task_4_2_10(array1: Array<Int>, array2: Array<Int>, acum: Int = 0, counter: Int = 0): Int=
    if (counter == array1.size)
        acum
    else
        task_4_2_10(array1,array2, if (array2.contains(array1[counter])) acum + 1 else acum, counter + 1 )

///////////////////////////////////////////
//////////     Задание 4.3.22    //////////
tailrec fun task_4_3_22(array: Array<Int>, a: Int, b: Int): Int{
    val minimum = minElem(array)
    return countMinInAB(array, b, a + 1, 0, minimum)
}

tailrec fun countMinInAB(array: Array<Int>, b: Int, counter: Int, acum: Int, min: Int): Int=
    if (counter == b)
        acum
    else
        countMinInAB(array, b,counter + 1, if(array[counter] == min) acum + 1 else acum, min)

///////////////////////////////////////////
//////////     Задание 4.4.24    //////////
////   1 2 3 4 5  max1 = 5, max2 = 4   ////
tailrec fun task_4_4_24(array: Array<Int>): Array<Int>{
    val max1 = maxElem(array)
    val min = minElem(array)
    array.set(array.indexOf(max1), min)
    val max2 = maxElem(array)
    val newArray = Array<Int>(2) {0}
    newArray[0] = max1
    newArray[1] = max2
    return newArray
}

///////////////////////////////////////////
//////////     Задание 4.5.31    //////////
tailrec fun countEvenArr(array: Array<Int>, counter: Int = 0, acum: Int = 0): Int=
    if (counter == array.size)
        acum
    else
        countEvenArr(array,counter + 1, if(array[counter]%2 == 0) acum + 1 else acum)

///////////////////////////////////////////
//////////     Задание 4.6.34    //////////
// [2,4] - отрезок. массив - 4 3 1 2 5
tailrec fun task_4_6_34(array: Array<Int>, a: Int, b: Int, counter: Int = 0, elems: String = ""): String=
    if (counter == array.size)
        elems
    else
        task_4_6_34(array, a, b,counter + 1,
            if(array[counter] in a..b) elems + array[counter].toString() + " " else elems)

///////////////////////////////////////////
//////////     Задание 4.7.40    //////////
fun task_4_7_40(array: Array<Int>){
    val check = evenInArr(array)
    if (check != -1)
        println("\nМинимальное четное: ${minEvenArr(array, acum = check)}")
    else
        println("\nВ массиве нет четных!")
}

tailrec fun evenInArr(array: Array<Int>, counter: Int = 0, acum: Int = -1): Int=
    if (counter == array.size)
        acum
    else
        if(array[counter]%2 == 0)
            array[counter]
        else
            evenInArr(array,counter + 1, acum)

tailrec fun minEvenArr(array: Array<Int>, counter: Int = 0, acum: Int): Int=
    if (counter == array.size)
        acum
    else
        minEvenArr(array,counter + 1, if(array[counter]%2 == 0 && array[counter] < acum) array[counter] else acum)

///////////////////////////////////////////
//////////     Задание 4.8.46    //////////
fun task_4_8_46(array: Array<Int>){
    val negative: String = getNegativeArr(array)
    val positive: String = getPositiveArr(array)
    println("\n\n$negative; $positive")
}

tailrec fun getNegativeArr(array: Array<Int>, counter: Int = 0, negative: String = ""): String=
    if (counter == array.size)
        negative
    else
        getNegativeArr(array,counter + 1,
            if(array[counter] < 0) negative + array[counter].toString() + " " else negative)

tailrec fun getPositiveArr(array: Array<Int>, counter: Int = 0, positive: String = ""): String=
    if (counter == array.size)
        positive
    else
        getPositiveArr(array,counter + 1,
            if(array[counter] > 0) positive + array[counter].toString() + " " else positive)

fun main() {
    var myFirstArray: Array<Int> = chooseInput()
    myFirstArray.forEach {
        print("$it ")
    }

    // Задание 4.8.46
    task_4_8_46(myFirstArray)

    // Задание 4.7.40
    // task_4_7_40(myFirstArray)

    // Задание 4.6.34
    /*
    val a = 2
    val b = 4
    println("\nЭлементы, значения которых из [$a,$b]: ${task_4_6_34(myFirstArray,a,b)}")
     */

    // Задание 4.5.31
    //println("\nКоличество четных эл-тов: ${countEvenArr(myFirstArray)}")

    // Задание 4.4.24
    // 2 наибольших элемента
    /*
    print("\n2 наибольших элемента: ")
    var mySecondArray: Array<Int> = task_4_4_24(myFirstArray)
    mySecondArray.forEach {
        print("$it ")
    }
     */


    // Задание 4.3.22
    // количество минимумов в интервале (a,b)
    /*
    val a = 0
    val b = 4
    println("\nКоличество минимальных в интервале ($a,$b): ${task_4_3_22(myFirstArray,a,b)}")
     */

    // Задание 4.2.10
    // количество повторяющихся из 2-х массивов
    /*
    var mySecondArray: Array<Int> = chooseInput()
    mySecondArray.forEach {
        print("$it ")
    }

    println("\n\nCount of repeating elems: ${task_4_2_10(myFirstArray,mySecondArray)}")
     */

    // Задание 4.1.9
    // Все элементы, до последнего минимального элемента
    /*
    println("\nВсе элементы, до последнего минимального элемента:\n")
    var mySecondArray = task_4_1_9(myFirstArray)
    mySecondArray.forEach {
        print("$it ")
    }
     */

    // val arrIterator = myFirstArray.iterator()

    // вызов метода arrayOp с итератором
    // println("Sum of elements: ${arrayOp(arrIterator, { elem:Int, sum:Int -> elem + sum },0)}")

    // вызов метода sum
    // println("Sum of elements: ${sumOfElem(myFirstArray)}")

    // вызов метода mul
    // println("Mul of elements: ${mulOfElem(myFirstArray)}")

    // вызов метода minElem
    // println("Minimal element: ${minElem(myFirstArray)}")

    // вызов метода maxElem
    // println("Maximal element: ${maxElem(myFirstArray)}")
}
