import RobotCommand.Go

/** Допустимые команды, исполняемые роботом */
enum RobotCommand:
    case TurnRight, TurnLeft, Go

/** Направление движения робота */
enum Direction:
    case Up, Down, Left, Right

/** Тип данных, отражающий положение робота на сетке */
type Position = (Int, Int)


/**
 * Напишите симулятор робота.
 *
 * Испытательной лаборатории завода роботов нужна программа для проверки роботов.
 * Роботы могут выполнять три возможные команды: Поверни направо, Поверни налево, Иди вперед.
 *
 * Роботы размещаются на гипотетической бесконечной двухмерной сетке с целочисленными координатами
 * и условными направлениями север (вверх), юг (вниз), запад (лево) и восток (право).
 * Координаты увеличиваются снизу вверх и слева направо.
 *
 * При инициализации робота считать, что изначально он направлен на север (вверх).
 *
 * Вы можете дорабатывать класс добавляя поля, свойства и необходимые методы по своему усмотрению.
 * Удалять или модифицировать объявленные ниже элементы класса нельзя.
 *
 * @param startX Начальная X-координата робота
 * @param startY Начальная Y-координата робота
 */
class RobotSimulator(startX: Int, startY: Int):

    private var _pos = new Position(startX, startY)
    private var _direction = Direction.Up

    /** Возвращает текущее положение робота */
    def position: Position = this._pos

    /** Возвращает текущее направление робота */
    def direction: Direction = this._direction

    /** Исполняет команды, перемещая робота по сетке
     *
     * Метод исполняет переданные команды последовательно, изменяя при необходимости текущее положение
     * робота на сетке.
     *
     * @param commands Список команд для исполнения
     * @return Новое положение робота
     */
    def walk(commands: List[RobotCommand]): Position =
        for command <- commands do
            command match
                case RobotCommand.TurnRight =>
                    this._direction = this._direction match
                        case Direction.Up => Direction.Right
                        case Direction.Right => Direction.Down
                        case Direction.Down => Direction.Left
                        case Direction.Left => Direction.Up

                case RobotCommand.TurnLeft =>
                    this._direction = this._direction match
                        case Direction.Up => Direction.Left
                        case Direction.Left => Direction.Down
                        case Direction.Down => Direction.Right
                        case Direction.Right => Direction.Up

                case RobotCommand.Go =>
                    this._pos = this._direction match
                        case Direction.Up => (this._pos._1, this._pos._2 + 1)
                        case Direction.Down => (this._pos._1, this._pos._2 - 1)
                        case Direction.Left => (this._pos._1 - 1, this._pos._2)
                        case Direction.Right => (this._pos._1 + 1, this._pos._2)
        this.position

    /** Исполняет команды, перемещая робота по сетке
     *
     * Метод исполняет переданные команды последовательно, изменяя при необходимости текущее положение
     * робота на сетке.
     *
     * @param commands Список команд для исполнения. Команды задаются символами строки:
     *                 R - поверхнуть направо,
     *                 L - повернуть налево,
     *                 G - двигаться вперед.
     * @return Новое положение робота
     */
    def walk(commands: String): Position =
        val commandsList = commands.toList.map {
            case 'R' => RobotCommand.TurnRight
            case 'L' => RobotCommand.TurnLeft
            case 'G' => RobotCommand.Go
        }
        this.walk(commandsList)

end RobotSimulator
