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
//////////       массив         ///////////
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
fun task_4_3_22(array: Array<Int>, a: Int, b: Int): Int{
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
fun task_4_4_24(array: Array<Int>): Array<Int>{
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

///////////////////////////////////////////
//////////     Задание 4.9.58    //////////
fun isSumOf2Other(array: IntArray, counter: Int = 0, value: Int): Boolean{
    var n: Int = 1
    array.forEach {
        val temp = it
        val newArray: IntArray = array.drop(n).toIntArray()
        newArray.forEach {
            if(it + temp == value)
                return true
        }
        n++
    }
    return false
}

fun task_4_9_58(array: Array<Int>){
    var count:Int = 0
    var curElem:Int = 1
    var elems: String = ""
    array.forEach {
        val firstPart: IntArray = array.take(curElem-1).toIntArray()
        val secondPart: IntArray = array.drop(curElem).toIntArray()
        val newArray: IntArray = firstPart + secondPart

        if (isSumOf2Other(newArray,value = it))
        {
            count++
            elems += "$it "
        }
        curElem++
    }

    println("\n\nКоличество, которые могут быть получены как сумма других 2-х: $count;\nЭлементы: $elems")
}

//////////////////////////////////////////
//////////////   СПИСКИ  /////////////////
//////////////////////////////////////////
// инициализация списка
fun initList(): List<Int> {
    print("Вводите элементы списка... (завершить ввод - q)\n")
    val list = listOf<Int>()
    return listInput(list)
}
//Ввод элементов списка с клавиатуры
fun listInput(list : List<Int>) : List<Int>{
    val elem:String = readLine()!!
    return if (elem != "q")
                listInput(list.plus(elem.toInt()))
            else
                list
}

//Функция перебора элементов списка (без методов List)
tailrec fun listOp(a: Iterator<Int>, f: (Int, Int) -> Int, result: Int): Int =
    if (!a.hasNext())
        result
    else
        listOp(a, f, f(a.next(),result))

// Перебор элементов списка (с методами List)
tailrec fun listOp(list: List<Int>, f:(Int, Int) -> Int, acum:Int, counter:Int = 0):Int =
    if (list.size == counter)
        acum
    else
        listOp(list, f, f(list[counter], acum), counter + 1)

// Сумма элементов списка
fun sumOfList(list: List<Int>):Int = listOp(list,{ elem:Int, sum:Int -> elem + sum }, 0)

// Произведение элементов списка
fun mulOfList(list: List<Int>):Int = listOp(list,{ elem:Int, mul:Int -> elem * mul}, 1)

// Минимальный элемент списка
fun minListElem(list: List<Int>): Int = listOp(list,{ elem:Int, min:Int -> if (elem < min) elem else min}, list[0])

// Максимальный элемент списка
fun maxListElem(list: List<Int>): Int = listOp(list,{ elem:Int, max:Int -> if (elem > max) elem else max}, list[0])

///////////////////////////////////////////
//////////     Ввод из файла    ///////////
//////////        список        ///////////

// создание списка и вызов заполнения из файла
fun listInputFile(input : Map<Int, Int>) : List<Int> {
    val list:List<Int> = listOf()
    return listInputFile(list, 0, input)
}

//Заполнение списка элементами из файла
fun listInputFile(list: List<Int>, counter : Int, input : Map<Int, Int>) : List<Int> =
    if (counter == input.size)
        list
    else
    {
        listInputFile(list.plus(input[counter]!!), counter + 1,  input)
    }

//Организация чтения из файла
//Одна строка - одно число, возвращает мэп индексированный
fun listInputFile(fileName:String) : List<Int> {
    val input = File(fileName).readLines()
        .withIndex() //Возвращает ленивую итерацию, которая обертывает каждый элемент исходного массива в IndexedValue, содержащий индекс этого элемента и сам элемент.
        .map { indexedValue -> indexedValue.index to indexedValue.value.toInt() }  // Создаёт мапу
        .toMap() //Возвращает мапу
    return listInputFile(input)
}

// Выбор откуда считать список
fun listChooseInput() : List<Int> {
    println(
        "Откуда считывать список?\n" +
                "1. Клавиатура\n" +
                "2. Файл"
    )
    val type = readLine()!!.toInt()
    if (type == 2) {
        println("Введите имя файла: ")
        val name = readLine().toString()
        return listInputFile("./src/${name}.txt")
    }
    else
        return initList()
}

///////////////////////////////////////////
//////////     Задание 4.1.9    ///////////
fun task_4_1_9_List(list: List<Int>): List<Int>{
    val minimum = minListElem(list)
    val indexOfLastMIn = list.indexOfLast { a -> a == minimum }
    return list.take(indexOfLastMIn)
}

///////////////////////////////////////////
//////////     Задание 4.2.10    //////////
tailrec fun task_4_2_10_List(list1: List<Int>, list2: List<Int>, acum: Int = 0, counter: Int = 0): Int=
    if (counter == list1.size)
        acum
    else
        task_4_2_10_List(list1,list2, if (list2.contains(list1[counter])) acum + 1 else acum, counter + 1 )

///////////////////////////////////////////
//////////     Задание 4.3.22    //////////
fun task_4_3_22_List(list: List<Int>, a: Int, b: Int): Int{
    val minimum = minListElem(list)
    return countMinInAB_List(list, b, a + 1, 0, minimum)
}

tailrec fun countMinInAB_List(list: List<Int>, b: Int, counter: Int, acum: Int, min: Int): Int=
    if (counter == b)
        acum
    else
        countMinInAB_List(list, b,counter + 1, if(list[counter] == min) acum + 1 else acum, min)

///////////////////////////////////////////
//////////     Задание 4.4.24    //////////
////   1 2 3 4 5  max1 = 5, max2 = 4   ////
fun task_4_4_24_List(list: List<Int>): List<Int>{
    val max1 = maxListElem(list)
    val min = minListElem(list)
    val indexMax1 = list.indexOf(max1)
    val list1: List<Int> = list.take(indexMax1 - 1)
    val list2: List<Int> = list.drop(indexMax1)
    val listConcat: List<Int> = list1 + list2
    val max2 = maxListElem(listConcat)
    val newList: List<Int> = listOf<Int>()
    newList.plus(max1)
    newList.plus(max2)
    return newList
}

///////////////////////////////////////////
//////////     Задание 4.5.31    //////////
tailrec fun countEvenArr_List(list: List<Int>, counter: Int = 0, acum: Int = 0): Int=
    if (counter == list.size)
        acum
    else
        countEvenArr_List(list,counter + 1, if(list[counter]%2 == 0) acum + 1 else acum)

///////////////////////////////////////////
//////////     Задание 4.6.34    //////////
// [2,4] - отрезок. массив - 4 3 1 2 5
tailrec fun task_4_6_34_List(list: List<Int>, a: Int, b: Int, counter: Int = 0, elems: String = ""): String=
    if (counter == list.size)
        elems
    else
        task_4_6_34_List(list, a, b,counter + 1,
            if(list[counter] in a..b) elems + list[counter].toString() + " " else elems)

///////////////////////////////////////////
//////////     Задание 4.7.40    //////////
fun task_4_7_40_List(list: List<Int>){
    val check = evenInArr_List(list)
    if (check != -1)
        println("\nМинимальное четное: ${minEvenArr_List(list, acum = check)}")
    else
        println("\nВ массиве нет четных!")
}

tailrec fun evenInArr_List(list: List<Int>, counter: Int = 0, acum: Int = -1): Int=
    if (counter == list.size)
        acum
    else
        if(list[counter]%2 == 0)
            list[counter]
        else
            evenInArr_List(list,counter + 1, acum)

tailrec fun minEvenArr_List(list: List<Int>, counter: Int = 0, acum: Int): Int=
    if (counter == list.size)
        acum
    else
        minEvenArr_List(list,counter + 1, if(list[counter]%2 == 0 && list[counter] < acum) list[counter] else acum)

///////////////////////////////////////////
//////////     Задание 4.8.46    //////////
fun task_4_8_46_List(list: List<Int>){
    val negative: String = getNegativeList(list)
    val positive: String = getPositiveList(list)
    println("\n\n$negative; $positive")
}

tailrec fun getNegativeList(list: List<Int>, counter: Int = 0, negative: String = ""): String=
    if (counter == list.size)
        negative
    else
        getNegativeList(list,counter + 1,
            if(list[counter] < 0) negative + list[counter].toString() + " " else negative)

tailrec fun getPositiveList(list: List<Int>, counter: Int = 0, positive: String = ""): String=
    if (counter == list.size)
        positive
    else
        getPositiveList(list,counter + 1,
            if(list[counter] > 0) positive + list[counter].toString() + " " else positive)

///////////////////////////////////////////
//////////     Задание 4.9.58    //////////
fun isSumOf2Other_List(list: List<Int>, counter: Int = 0, value: Int): Boolean{
    var n: Int = 1
    list.forEach {
        val temp = it
        val newList: List<Int> = list.drop(n)
        newList.forEach {
            if(it + temp == value)
                return true
        }
        n++
    }
    return false
}

fun task_4_9_58_List(list: List<Int>){
    var count:Int = 0
    var curElem:Int = 1
    var elems: String = ""
    list.forEach {
        val firstPart: List<Int> = list.take(curElem-1)
        val secondPart: List<Int> = list.drop(curElem)
        val newList: List<Int> = firstPart + secondPart

        if (isSumOf2Other_List(newList,value = it))
        {
            count++
            elems += "$it "
        }
        curElem++
    }

    println("\n\nКоличество, которые могут быть получены как сумма других 2-х: $count;\nЭлементы: $elems")
}

fun main() {
    var myFirstList: List<Int> = listChooseInput()
    myFirstList.forEach {
        print("$it ")
    }

    //println("\nSum: ${sumOfList(myFirstList)}; Mul: ${mulOfList(myFirstList)}\nMin: ${minListElem(myFirstList)}, Max: ${maxListElem(myFirstList)}")
    // инициализация массива
    /*
    var myFirstArray: Array<Int> = chooseInput()
    myFirstArray.forEach {
        print("$it ")
    }
     */

    // Задание 4.9.58
    //task_4_9_58(myFirstArray)

    // Задание 4.8.46
    //task_4_8_46(myFirstArray)

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
