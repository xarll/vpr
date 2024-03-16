
/**
 * Определите в объекте ниже функции not, and, or, nand, nor, xor, impl и equ,
 * которые возвращают true или false в зависимости от результата их соответствующих операций.
 *
 * Например and(A, B) истинно тогда и только тогда, когда и A, и B истинны.
 *
 * (!) При реализации функций запрещено использовать стандартные логическии операции &&, || (и т.д.),
 * а также операции сравнения на равенство или неравенство
 */
object Logic:

    /** Логическое отрицание */
    def not(a: Boolean): Boolean =
        if a then false else true

    /** Логическое И */
    def and(a: Boolean, b: Boolean): Boolean = 
        if a then b else false

    /** Логическое ИЛИ */
    def or(a: Boolean, b: Boolean): Boolean = 
        if a then true else b

    /** Логическое НЕ И */
    def nand(a: Boolean, b: Boolean): Boolean = 
        if a then not(b) else true

    /** Логическое исключающее ИЛИ */
    def xor(a: Boolean, b: Boolean): Boolean = 
        if a then not(b) else b

    /** Операция импликации */
    def impl(a: Boolean, b: Boolean): Boolean = 
        if a then b else true

    /** Логическая эквавалентность */
    def equ(a: Boolean, b: Boolean): Boolean = 
        if a then b else not(b)

    /** Печатает таблицу истинности логической функции
     *
     * Напишите функцию, которая печатает таблицу истинности заданной логической функции двух переменных.
     *
     * Например:
     * {{{
     *     scala> truthTable(and)
     *     A     B     RES
     *     true  true  true
     *     true  false true
     *     false true  false
     *     false false false
     * }}}
     *
     * @param func Логическая функция
     */
    def truthTable(func: (Boolean, Boolean) => Boolean): Unit = 
        println("A     B     RES")
        println(s"true  true  ${func(true, true)}")
        println(s"true  false ${func(true, false)}")
        println(s"false true  ${func(false, true)}")
        println(s"false false ${func(false, false)}")

    /** Неявное преобразование в тип Logic
     *
     * Допишите неявное преобразование типа данных так, чтобы с учетом дополненного ниже класса Logic
     * вы могли пользоваться логическими функциями, объявленными выше, как логическими операторами.
     *
     * Например:
     * {{{
     *     import Logic.given
     *     val (a, b) = (true, false)
     *     print(a and (a or not(b)))
     * }}}
     */
    given Conversion[Boolean, Logic] = 
        Logic(_)

end Logic


class Logic(a: Boolean):

    //TODO: Допишите бинарные инфиксные операторы логических операций одноименные функциям выше
    
    def not: Boolean = Logic.not(a)
    def and(b: Boolean): Boolean = Logic.and(a, b)
    def or(b: Boolean): Boolean = Logic.or(a, b)
    def nand(b: Boolean): Boolean = Logic.nand(a, b)
    def xor(b: Boolean): Boolean = Logic.xor(a, b)
    def impl(b: Boolean): Boolean = Logic.impl(a, b)
    def equ(b: Boolean): Boolean = Logic.equ(a, b)
    
end Logic
