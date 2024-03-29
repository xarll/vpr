# Лаб 3 Интерфейсы

## Задание

В соответствие с вариантом задания (Таблица №1) описать указанные
интерфейсы и реализовать их в перечисленных классах. Реализовать события
для всех основных действий, допустимых по отношению к конкретному
классу. Создать программу на языке C#, демонстрирующую работу
реализованных классов, использование интерфейсов, возникновение и
обработку событий.

**Варианты:**


1 Реализовать системы электрических источников и
приборов, соединенных между собой через шнуры. В
интерфейсах должны быть предусмотрена возможность
получения информации о напряжении и максимальной
мощности, которую поддерживает элемент. Прибор
должен иметь наименование, потребляемую мощность, а
источник и провод – списки подключенных приборов.

Интерфейсы:
- IElectricSource (источник тока)
- IElectricAppliance (электрический прибор)
- IElectricWire (электрический шнур)

Классы:
- SolarBattery (солнечная батарея)
- DieselGenerator (дизельный генератор)
- NuclearPowerPlant (атомная электростанция)
- Kettle (чайник)
- Lathe (токарный станок)
- Refrigerator (холодильник)
- ElectricPowerStrip (электрический удлинитель)
- HighLine (высоковольтная линия)
- StepDownTransformer (понижающий трансформатор,
должен реализовывать интерфейсы и потребителя и
источника тока)


2 Реализовать компоненты компьютерной системы,
связанные между собой через определенные интерфейсы.
Обеспечить возможность стыковки элементов системы
между собой в случае совпадения интерфейсов
взаимодействия. Интерфейсы в обязательном порядке
поддерживать информацию о максимальной скорости
передачи данных и возможность передавать как минимум
побайтовые данные.

Интерфейсы:
- IUsbBus (шина USB)
- ISata (шина SATA)
- INetwork (сеть)
- IInnerBus (внутренняя шина компьютера)

Классы:
- MotherBoard (материнская плата с процессором)
- RamMemory (оперативная память)
- HardDisk (жесткий диск)
- Printer (принтер)
- Scanner (сканер изображений)
- NetworkCard (сетевая карта)
- Keyboard (клавиатура)


3 Реализовать набор коллекций, реализующих стандартные
интерфейсы по работе с коллекциями из пространства
имен System.Collections.

Интерфейсы:
- IEnumerable (последовательность элементов)
- ICollection (коллекция)
- IList (список)
- IDictinary (словарь)

Классы:
- List (список)
- Queue (очередь)
- Dictinary (словарь)

## Решение варианта №2
