
@main
def main(): Unit =

    //  Бинарное Дерево Поиска --------------------------------------------------------
    val tree = Node(5, End, End)
    val updatedTree = tree.addValue(3).addValue(7).addValue(1).addValue(6)

    assert(updatedTree.find(5).isDefined, "Tree should contain 5")
    assert(updatedTree.find(3).isDefined, "Tree should contain 3")
    assert(updatedTree.find(7).isDefined, "Tree should contain 7")
    assert(updatedTree.find(1).isDefined, "Tree should contain 1")
    assert(updatedTree.find(6).isDefined, "Tree should contain 6")
    assert(updatedTree.find(10).isEmpty, "Tree should not contain 10")

    val treeWithRemovedElement = updatedTree.remove(7)
    assert(treeWithRemovedElement.find(7).isEmpty, "Tree should not contain 7 after removal")

    val symmetricTree = Node(5, Node(3, End, End), Node(7, End, End))
    assert(symmetricTree.isSymmetric, "Tree should be symmetric")

    val nonSymmetricTree = Node(5, Node(3, End, End), Node(7, Node(6, End, End), End))
    assert(!nonSymmetricTree.isSymmetric, "Tree should not be symmetric")
    println("Binary Search Tree tests passed")

    //  Робот -------------------------------------------------------------------------
    val robot = RobotSimulator(0, 0)

    // Проверка поворота направо
    var position = robot.walk(List(RobotCommand.TurnRight))
    assert(
        position._1 == 0 && position._2 == 0,
        "Robot should stay in place when turning"
    )

    // Проверка движения вперед
    position = robot.walk(List(RobotCommand.Go))
    assert(position._1 == 1 && position._2 == 0, "Robot should move forward")

    // Проверка поворота налево и движения вперед
    position = robot.walk(List(RobotCommand.TurnLeft, RobotCommand.Go))
    assert(position._1 == 1 && position._2 == 1, "Robot should turn left and move forward")

    // Проверка последовательности команд
    position = robot.walk(List(
        RobotCommand.TurnRight,
        RobotCommand.Go,
        RobotCommand.TurnRight,
        RobotCommand.Go
    ))
    assert(position._1 == 2 && position._2 == 0, "Robot should execute sequence of commands correctly")

    position = robot.walk("RGRG")
    assert(position._1 == 1 && position._2 == 1, "Robot should execute sequence of commands correctly")

    println("Robot tests passed")


    // IntHelper ---------------------------------------------------------------------

    // Проверка метода isPrime
    assert(IntHelper.isPrime(7), "7 should be a prime number")
    assert(!IntHelper.isPrime(4), "4 should not be a prime number")

    // Проверка метода gcd
    assert(IntHelper.gcd(56, 16) == 8, "GCD of 56 and 16 should be 8")

    // Проверка метода isCoprime
    assert(IntHelper.isCoprime(35, 64), "35 and 64 should be coprime")

    // Проверка метода totient
    assert(IntHelper.totient(10) == 4, "Totient of 10 should be 4")

    // Проверка метода primeFactors
    assert(IntHelper.primeFactors(126) == List(2, 3, 3, 7), "Prime factors of 126 should be List(2, 3, 3, 7)")

    // Проверка метода listPrimesInRange
    assert(IntHelper.listPrimesInRange(7 to 31) == List(7, 11, 13, 17, 19, 23, 29, 31), "Primes in range 7 to 31 should be List(7, 11, 13, 17, 19, 23, 29, 31)")

    // Проверка метода goldbach
    assert(IntHelper.goldbach(28) == (5, 23), "Goldbach pair for 28 should be (5, 23)")

    println("IntHelper tests passed")

    // Lists -------------------------------------------------------------------------
    // Проверка метода isPrime
    assert(IntHelper.isPrime(7), "7 should be a prime number")
    assert(!IntHelper.isPrime(4), "4 should not be a prime number")

    // Проверка метода gcd
    assert(IntHelper.gcd(56, 16) == 8, "GCD of 56 and 16 should be 8")

    // Проверка метода isCoprime
    assert(IntHelper.isCoprime(35, 64), "35 and 64 should be coprime")

    // Проверка метода totient
    assert(IntHelper.totient(10) == 4, "Totient of 10 should be 4")

    // Проверка метода primeFactors
    assert(IntHelper.primeFactors(126) == List(2, 3, 3, 7), "Prime factors of 126 should be List(2, 3, 3, 7)")

    // Проверка метода listPrimesInRange
    assert(IntHelper.listPrimesInRange(7 to 31) == List(7, 11, 13, 17, 19, 23, 29, 31), "Primes in range 7 to 31 should be List(7, 11, 13, 17, 19, 23, 29, 31)")

    // Проверка метода goldbach
    assert(IntHelper.goldbach(28) == (5, 23), "Goldbach pair for 28 should be (5, 23)")

    println("Lists tests passed")

    // Logic -------------------------------------------------------------------------

    // Проверка метода and
    assert(Logic.and(true, true), "and(true, true) should be true")
    assert(!Logic.and(true, false), "and(true, false) should be false")

    // Проверка метода or
    assert(Logic.or(true, false), "or(true, false) should be true")
    assert(!Logic.or(false, false), "or(false, false) should be false")

    // Проверка метода not
    assert(!Logic.not(true), "not(true) should be false")
    assert(Logic.not(false), "not(false) should be true")

    // Проверка метода nand
    assert(!Logic.nand(true, true), "nand(true, true) should be false")
    assert(Logic.nand(true, false), "nand(true, false) should be true")

    // Проверка метода xor
    assert(Logic.xor(true, false), "xor(true, false) should be true")
    assert(!Logic.xor(true, true), "xor(true, true) should be false")

    // Проверка метода impl
    assert(!Logic.impl(true, false), "impl(true, false) should be false")
    assert(Logic.impl(false, true), "impl(false, true) should be true")

    // Проверка метода equ
    assert(Logic.equ(true, true), "equ(true, true) should be true")
    assert(Logic.equ(false, false), "equ(false, false) should be true")
    assert(!Logic.equ(true, false), "equ(true, false) should be false")
    assert(!Logic.equ(false, true), "equ(false, true) should be false")

    println("Logic tests passed")

    println("\nAll tests passed")

