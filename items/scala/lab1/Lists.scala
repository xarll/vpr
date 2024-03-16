
class Lists:

    /** Возвращает последний элемент списка
     *
     * Если последнего элемента в списке нет, генерирует исключение NoSuchElementException

     * (!) При реализации данного метода нельзя использовать никакие стандартные
     * методы или свойства класса List кроме .head и .tail (но лучше и без них).
     */
    def last[A](ls: List[A]): A =
        ls match
            case Nil => throw new NoSuchElementException
            case last :: Nil => last
            case head :: tail => last(tail)

    /** Возвращает предпоследний элемент списка
     *
     * Если предпоследнего элемента в списке нет, генерирует исключение NoSuchElementException
     */
    def penultimate[A](ls: List[A]): A =
        ls match
            case Nil => throw new NoSuchElementException
            case pre :: last :: Nil => pre
            case head :: tail => penultimate(tail)

    /** Возвращает заданный по номеру элемент списка
     *
     * (!) В ходе реализации нельзя использовать стандартный метод доступа к элементу списка по номеру
     *
     * @param n Порядковый номер элемента
     * @param ls Исходный список
     * @tparam A Тип элемента списка
     * @return Заданный по номеру элемент.
     *         Если такого элемента нет - генерирует исключение NoSuchElementException
     */
    def nth[A](n: Int, ls: List[A]): A =
        if n < 0 then
            throw new NoSuchElementException
        else
            ls match
                case Nil => throw new NoSuchElementException
                case head :: tail if n == 0 => head
                case head :: tail => nth(n - 1, tail)

    /** Возвращает длину списка
     *
     * (!) В ходе реализации нельзя использовать стандартное свойство .length списка, а также циклы for и while
     *
     * @param ls Исходный список
     * @tparam A Тип элемента списка
     * @return Длина списка
     */
    def len[A](ls: List[A]): Int =
        ls match
            case Nil => 0
            case first :: tail => 1 + len(tail)

    /** Возвращает новый список в котором элементы исходного списка записаны в обратном порядке
     *
     * (!) В ходе реализации нельзя использовать стандартный метод .reverse списка
     *
     * @param ls Исходный список
     * @tparam A Тип элемента списка
     * @return Инверсированный список
     */
    def reverse[A](ls: List[A]): List[A] =
        ls match
            case Nil => Nil
            case head :: tail => reverse(tail) ::: List(head)

    /** Определяет, является ли список палиндромом
     *
     * Палиндромом называют списки или строки которые читаются одинаково в обоих направлениях.
     *
     *
     * Например:
     * {{{
     *     scala> isPalindrome( List(1, 4, 2, 3, 2, 4, 1) )
     *     res0: true
     * }}}
     *
     * @param ls Исходный список
     * @tparam A Тип элемента списка
     * @return Признак того, что исходный список является полиндромом
     */
    def isPalindrome[A](ls: List[A]): Boolean =
        ls == reverse(ls)

    /** Возвращает список, не содержащий других вложенных списков ("распрямляет" список)
     *
     * Функция раскрывает все вложенные в исходный список дополнительные списки, объединяя
     * их содержимое в единый линейный список. Например:
     * {{{
     *     scala> flatten(List(List(1, 7), 2, List(9, List(8))))
     *     res0: List[Any] = List(1, 7, 2, 9, 8)
     * }}}
     */
    def flatten(ls: List[Any]): List[Any] =
        ls match
            case Nil => Nil
            case (head: List[Any]) :: tail => flatten(head) ::: flatten(tail)
            case head :: tail => head :: flatten(tail)

    /** Удаляет последовательно расположенные дубликаты элементов в списке, возвращая новый список
     *
     * Если список содержит повторяющиеся элементы, их следует заменить единственной копией элемента.
     * Порядок элементов не должен меняться.
     * Например:
     * {{{
     *     scala> compress(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
     *     res0: List[Int] = List(1, 2, 3, 1, 4, 5)
     * }}}
     */
    def compress[A](ls: List[A]): List[A] =
        ls match
            case Nil => Nil
            case head :: tail => head :: compress(tail.dropWhile(_ == head))

    /** Сворачивает последовательные дубликаты элементов списка во вложенные подсписки.
     *
     * Если список содержит повторяющиеся элементы, их следует поместить в отдельные подсписки.
     * Например:
     * {{{
     *     scala> pack(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
     *     res0: List[List[Int]] = List(List(1, 1, 1, 1), List(2), List(3, 3), List(1, 1), List(4), List(5, 5, 5, 5))
     * }}}
     */
    def pack[A](ls: List[A]): List[List[A]] =
        ls match
            case Nil => Nil
            case head :: tail =>
                val (packed, next) = ls.span(_ == head)
                packed :: pack(next)

    /** Возвращает закодированный методом RLE список
     *
     * Используйте результат функции [[pack]] для реализации так называемого метода сжатия
     * Run-length encoding (RLE). Последовательные дубликаты элементов кодируются кортежами (N, E),
     * где N — количество дубликатов элемента E.
     * Например:
     * {{{
     *     scala> rleEncode(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
     *     res0: List[(Int, Int)] = List((4, 1), (1, 2), (2, 3), (2, 1), (1, 4), (4, 5))
     * }}}
     */
    def rleEncode[A](ls: List[A]): List[(Int, A)] =
        pack(ls).map(x => (x.length, x.head))

    /** Модифицированный метод Run-length encoding (RLE)
     *
     * Измените результат функции [[rleEncode]] таким образом, чтобы, если у элемента нет дубликатов,
     * он просто копировался в список результатов. Только элементы с дубликатами остаются кортежами
     * вида (N, E).
     * Например:
     * {{{
     *     scala> rleEncode(List(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5))
     *     res0: List[(Int, Int)] = List((4, 1), 2, (2, 3), (2, 1), 4, (4, 5))
     * }}}
     */
    def encodeModified[A](ls: List[A]): List[Any] =
        rleEncode(ls).map(x => if x._1 == 1 then x._2 else x)

    /** Декодирует список, сжатый методом Run-length encoding (RLE)
     *
     * Имея на входе список аналогичный возвращаемому функцией [[rleEncode]],
     * разверните его в исходный список.
     * Например:
     * {{{
     *     scala> rleDecode(List( (3, 'a'), (2, 'z'), (4, 'b') ))
     *     res0: List('a', 'a', 'a', 'z', 'z', 'b', 'b', 'b', 'b')
     * }}}
     */
    def rleDecode[A](ls: List[(Int, A)]): List[A] =
        ls.flatMap(x => List.fill(x._1)(x._2))

    /** Возвращает список в котором каждый элемент исходного списка повторен дважды
     *
     * (!) В ходе реализации нельзя использовать циклы
     *
     * Например:
     * {{{
     *     scala> duplicate(List(1, 2, 3))
     *     res0: List(1, 1, 2, 2, 3, 3)
     * }}}
     */
    def duplicate[A](ls: List[A]): List[A] =
        ls.flatMap(x => List(x, x))

    /** Возвращает список в котором каждый элемент исходного списка повторен указанное количество раз
     *
     * Например:
     * {{{
     *     scala> duplicateN(3, List(1, 2, 3))
     *     res0: List(1, 1, 1, 2, 2, 2, 3, 3, 3)
     * }}}
     */
    def duplicateN[A](n: Int, ls: List[A]): List[A] =
        ls.flatMap(x => List.fill(n)(x))

    /** Удаляет каждый N-й элемент исходного списка
     *
     * Например:
     * {{{
     *     scala> dropN(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'))
     *     res0: List('a', 'b', 'd', 'e', 'g', 'h')
     * }}}
     */
    def dropN[A](n: Int, ls: List[A]): List[A] =
        ls.zipWithIndex.filter(x => (x._2 + 1) % n != 0).map(_._1)


    /** Разбивает исходный список на две части
     *
     * Разбивает исходный список на две части. Длина первой части задается в виде параметра.
     * Например:
     * {{{
     *     scala> split(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'))
     *     res0: (List('a', 'b', 'c'), List('d', 'e', 'f', 'g', 'h'))
     * }}}
     *
     * @param n Длина первой части
     * @param ls Исходный список
     * @tparam A Тип элемента списка
     * @return Кортеж с двумя частями исходного списка
     */
    def split[A](n: Int, ls: List[A]): (List[A], List[A]) = 
        (ls.take(n), ls.drop(n))

    /** Возвращает срез списка
     *
     * Учитывая два индекса, I и K, формирует срез, содержащий элементы от I-го элемента включительно
     * до K-го элемента исходного списка, но не включая его.
     *
     * (!) В ходе реализации нельзя использовать стандартные методы списка для формирования среза.
     *
     * Например:
     * {{{
     *     scala> slice(3, 6, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'))
     *     res0: List('d', 'e', 'f')
     * }}}
     *
     * @param start Начальный элемент среза
     * @param end Конечный элемент среза (не включая)
     * @param ls Исходный список
     * @tparam A Тип элемента списка
     * @return Сформированный список-срез
     */
    def slice[A](start: Int, end: Int, ls: List[A]): List[A] = 
        ls.drop(start).take(end - start)

    /** Формроует список, содержащий все целые числа в заданном диапазоне
     *
     * (!) В ходе реализации нельзя использовать стандартные методы списка для формирования интервалов
     */
    def range(start: Int, end: Int): List[Int] = 
        if start > end then 
            Nil
        else 
            start :: range(start + 1, end)

    /** Выбирает заданное количество случайно выбранных элементов из списка (без повторов) */
    def randomSelect[A](n: Int, ls: List[A]): List[A] = 
        if n <= 0 then 
            Nil
        else 
            val unicList = compress(ls);
            val index = util.Random.nextInt(unicList.length)
            unicList(index) :: randomSelect(n - 1, ls.filter(_ != unicList(index)))
        

    /** Выбирает заданное количество различных случайных чисел из диапазона 1..max */
    def lotto(count: Int, max: Int): List[Int] = 
        randomSelect(count, range(1, max + 1))

end Lists
