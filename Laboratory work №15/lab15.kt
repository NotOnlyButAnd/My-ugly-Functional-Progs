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
//////////     Ввод из файла    //////////

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
    println("Откуда считывать массив?\n" +
            "1. Из файла\n" +
            "2. С клавиатуры")

    val choose = readLine()!!.toInt()
    if (choose == 2) {
        println("Введите имя файла: ")
        val name = readLine().toString()
        return inputFile("./src/${name}.txt")
    }
    else
        return initArray()
}

fun main() {
    var myFirstArray: Array<Int> = chooseInput()
    myFirstArray.forEach {
        print("$it ")
    }

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
