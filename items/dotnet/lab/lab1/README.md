# Лаб 1

## Варианты

1.  Ввести с консоли массив целых чисел и отсортировать его методом
прямого включения.
2.  Ввести с консоли массив целых чисел и отсортировать его методом
прямого выбора.
3.  Ввести с консоли массив целых чисел и отсортировать его методом
пузырька.
4.  Используя массивы, ввести с клавиатуры две прямоугольных
матрицы и вывести на экран результат суммирования первой из них
с транспонированной второй матрицей.
5.  Ввести с клавиатуры массив строк, отсортировать полученный
массив по длине строки и вывести результат на экран.
6.  Ввести с консоли массив вещественных чисел, вычислить
среднегеометрическое и среднеарифметическое значения и вывести
их на экран.
7.  Ввести с консоли массив вещественных чисел, нормализовать его
относительно наибольшего элемента и вывести результаты на экран.
8.  Рассматривая два массива чисел как координаты векторов
комплексной плоскости, найти пару векторов, образующих при
взаимном перемножении вектор наибольшей длины.

## Решение варианта №2

```C#


void ErrorHandler(string errorMessage)
{
    Console.Write($"Некорректный ввод: {errorMessage}\n ---> Попробовать снова или выйти из программы? [y/n]: ");
    var yesOrNo = Console.ReadLine();
    if (yesOrNo is null)
        Environment.FailFast("Ошибка ввода");

    yesOrNo = yesOrNo.Replace(" ", "").ToLower();
    if (yesOrNo == "y")
        return;
    
    if (yesOrNo == "n")
        Environment.Exit(0);
    else
        Environment.FailFast("Ошибка ввода");
}



void SelectionSort(int[] array)
{
    for (var i = 0; i < array.Length - 1; i++) 
    {
        var min = i; 
        for (int j = i + 1; j < array.Length; j++)
        {
            if (array[j] < array[min]) 
                min = j;      
        }
        (array[i], array[min]) = (array[min], array[i]);
    }
}

UInt32 arraySize;

while (true)
{
    try
    {
        Console.Write("Введите длину массива в пределах 32бит: ");
        arraySize = Convert.ToUInt32(Console.ReadLine());

        if (arraySize == 0)
        {
            ErrorHandler("Длина массива не может быть равной 0!");
            continue;
        }
        
        break;
    }
    catch (FormatException error)
    {
        ErrorHandler(error.Message);
    }
    catch (OverflowException error)
    {
        ErrorHandler(error.Message);
    }
    
}

var userArray = new int[arraySize];

Console.WriteLine("\nЗаполните массив:");

for (var i = 0; i < arraySize; i++)
{
    try
    {
        Console.Write($"[{i}] Введите значение в пределах 32бит: ");
        userArray[i] = Convert.ToInt32(Console.ReadLine());
    }
    catch (FormatException error)
    {
        ErrorHandler(error.Message);
        i--;
    }
    catch (OverflowException error)
    {
        ErrorHandler(error.Message);
        i--;
    }
}

Console.Write("\nОтсортированный массив: [");

SelectionSort(userArray);

foreach (var value in userArray)
{
    Console.Write($"{value}, ");
}

Console.WriteLine("]");
```
