# Лабораторная работа #12 - Cетевые утилиты Linux.

Вам предстоит изучить утилиты для захвата сетевого трафика, научиться анализировать захваченные пакеты, извлекать из них полезную информацию.

## Материалы
- [Лабораторная_работа_№_12_Сетевые_утилиты.docx](./Лабораторная_работа_№_12_Сетевые_утилиты.docx)

## Работа

Нам потребуется 2 ВМ. Я буду использовать одну [Ubuntu Desktop](https://ubuntu.com/download/desktop#downloads) а вторую [Ubuntu Server](https://ubuntu.com/download/server#downloads)

Также нам потребуется настроить [**Сеть NAT**](https://github.com/xarll/vpr/blob/main/items/os1/lab6_2/README.md#%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B8%D0%BC-%D1%81%D0%B5%D1%82%D1%8C-nat) или любую другую сеть между машинами


#### 1. Утилита TCPDump

Утилита TCPDump представляет собой мощный и популярный инструмент для перехвата и анализа сетевых пакетов. Она позволяет просматривать все входящие и исходящие из определенного интерфейса пакеты и работает в командной строке. Её использование необходимо в случаях, когда невозможно запустить Wireshark, например, в операционных системах без графической оболочки. Данная утилита часто используется для устранения неполадок в сети, а также для обеспечения безопасности.


Если tcpdump запустить без параметров, он будет выводить информацию обо всех сетевых пакетах. С помощью параметра -i можно указать сетевой интерфейс, с которого следует принимать данные, например:

```bash
sudo tcpdump -i enp0s8
```

Чтобы посмотреть названия всех доступных для захвата интерфейсов, нужно запустить `TCPDump` с ключом `-D`.
Для более точной настройки работы `TCPDump` можно использовать один из перечисленных далее параметров, или их комбинацию.
—	выводить сокращённую информацию: `-q`;
—	выводить подробную информацию: `-v` `-vv` `-vvv` (чем больше v, тем подробнее информация);
—	не преобразовывать порты: `-n` (вместо названий, например, http или ftp будут показываться номера, для указанных протоколов: 80 и 21);
—	не преобразовывать имена: `-nn` (вместо имён компьютеров, например, UbuntuServer1804 будут показываться их IP-адреса);
—	показывать MAC-адреса: `-e`;
—	захватить только определённое количество пакетов: `-с количество`;

Для протокола, чьи пакеты должны захватываться, достаточно указать его название, например:
```bash
tcpdump icmp
```
или
```bash
tcpdump udp port 53
```
Параметр port позволяет ограничить для захватываемого трафика порт или их диапазон. Данный параметр применим только к протоколам `TCP` и `UDP`.
По умолчанию `TCPDump` выводит информацию о захваченных пакетах на экран, однако её можно записывать в файл. Для это используется параметр `-w`:
```bash
tcpdump -w /home/student/1.cap
```
Полученный в результате файл имеет формат, совместимый с `Wireshark` и может быть в дальнейшем проанализирован в данной программе с использованием более наглядного графического интерфейса, а также широких возможностей по созданию фильтров и подсчёту статистики.

#### 1.1. С помощью утилиты TCPDump захватите и выведите на экран информацию из входящих пакетов протоколов HTTP и ICMP.

```bash
tcpdump "icmp or (port 80 or port 443)"
```

![image](https://github.com/xarll/vpr/assets/76239707/b7c30b7c-3aa7-40f9-b146-08f0b8513d34)

Со 2й ВМ выполните `ping <ip адрес> -c 3` и `curl <ip адрес>`
Также чтобы захватить `http`, можете открыть в web браузере любой вебсайт

![image](https://github.com/xarll/vpr/assets/76239707/d30d7626-7050-4936-8fb6-4801450bc2ce)


#### 1.2. С помощью утилиты TCPDump захватите и запишите в файл трафик, передаваемый виртуальной машиной с Ubuntu Server в процессе обращения к ней по протоколу HTTP и выполнения команды ping.

Действия как и в П.1.1, но добавляется параметр для сохранения результата `... -w /home/jkearnsl/supadump.cap`

```bash
tcpdump "icmp or (port 80 or port 443)" -w /home/jkearnsl/supadump.cap
```

#### 2. Wireshark

Wireshark — программа-анализатор трафика для компьютерных сетей Ethernet и некоторых других. Имеет графический пользовательский интерфейс. 
Позволяет анализировать шифрованный/незашифрованный трафик, дешифровать шифрованный график (при наличии сертификата)

![image](https://github.com/xarll/vpr/assets/76239707/a4bd0224-8f4f-42c2-9f92-4b4198189f00)


##### Установка

```bash
sudo apt-get install wireshark
```

```bash
sudo dpkg-reconfigure wireshark-common
```

```bash
sudo adduser $USER wireshark
```

##### Запуск

```bash
wireshark
```

#### 2.1. Передайте полученный трафик на виртуальную машину с десктопной версией Linux и установленной программой Wireshark.

Нажмите: `Файл` --> `Открыть`

![image](https://github.com/xarll/vpr/assets/76239707/5c0deb2c-7270-4422-a711-8586dfe901bb)

--> `<выберите файл, который мы сохранили в П.1.2>` --> `Открыть`

![image](https://github.com/xarll/vpr/assets/76239707/bb9a4d1e-282d-4c54-b53b-f0fbf0a58632)


#### 2.2. С использованием Wireshark выделите из общего трафика отдельно HTTP и отдельно ICMP-пакеты.

В wireshark есть фильтр, вбиваем туда `icmp or http` чтобы отобразить только `icmp` и `http` протоколы.

![image](https://github.com/xarll/vpr/assets/76239707/a5d74a89-76ac-4be5-876d-36bc790cc564)

#### 2.3. Определите все протоколы а также, все MAC-адреса, чьи пакеты присутствуют в захваченном трафике.

Нажмите: `Статистика` --> `Иерархия Протоколов`

![image](https://github.com/xarll/vpr/assets/76239707/d695fd58-c9f6-4beb-9c32-738940b16309)

Нажмите: `Статистика` --> `Диалоги`

![image](https://github.com/xarll/vpr/assets/76239707/d234f201-4fe1-4e75-8cf8-ec0506540da4)


#### 2.4. Определите все IP-адреса, которые присутствуют в захваченном трафике.

Нажмите: `Статистика` --> `IPv4 Statistics` --> `All Addresses`

![image](https://github.com/xarll/vpr/assets/76239707/5e064b23-d70c-492f-acee-4c481d413ad5)

Тут продемонстрированы все уникальные ipv4 которые участвовали в трафике

![image](https://github.com/xarll/vpr/assets/76239707/34f50bdb-918f-4bd9-931a-643ad3a36e11)


#### 2.5. Определите все запрашиваемые доменные имена.

Нажмите: `Статистика` --> `HTTP` --> `Запросы`

![image](https://github.com/xarll/vpr/assets/76239707/61a9b760-e3a9-4658-ae66-d969721f05b8)


PS: возможно есть лучший метод, чем предлагаю я

#### 3. NMap

nmap — свободная утилита, предназначенная для разнообразного настраиваемого сканирования IP-сетей с любым количеством объектов, определения состояния объектов сканируемой сети. 
Ключевой информацией является «таблица важных портов».

Эта таблица содержит номер порта, протокол, имя службы и состояние. Состояние может иметь значение `open` (открыт), `filtered` (фильтруется), `closed` (закрыт) или `unfiltered` (не фильтруется). Открыт означает, что приложение на целевой машине готово для установки соединения/принятия пакетов на этот порт. Фильтруется означает, что брандмауэр, сетевой фильтр, или какая-то другая помеха в сети блокирует порт, и Nmap не может установить открыт этот порт или закрыт. Закрытые порты не связаны ни с каким приложением, но могут быть открыты в любой момент. Порты расцениваются как не фильтрованные, когда они отвечают на запросы Nmap, но Nmap не может определить открыты они или закрыты. Nmap выдает комбинации открыт|фильтруется и закрыт|фильтруется, когда не может определить, какое из этих двух состояний описывает порт. Эта таблица также может предоставлять детали о версии программного обеспечения, если это было указано при запуске сканирования.

Использование NMap заключается в выполнении команды:
```bash
nmap [<Тип сканирования>] [<Опции>] {<цель сканирования>}
```
Тип сканирования определяет используемые пакеты и подход к интерпретации результата. Для протокола TCP можно задать один или несколько из следующих типов сканирования:
- `-sS` — TCP SYN;
- `-sT` — TCP connect;
- `-sN`; `-sF`; `-sX` — NULL, FIN и Xmas;
- `-sA` — TCP ACK;
- `-sW` — TCP Window;
- `-sM` — TCP сканирование Мэймона.

Подробнее про их различие можно прочитать в официальной русскоязычной [документации](https://nmap.org/man/ru/man-port-scanning-techniques.html). Чем больше типов сканирования выбрано при запуске команды, тем дольше оно будет осуществляться.
Для сканирования UDP-портов необходимо использовать параметр `-sU`.
По умолчанию NMap проверяет только первую тысячу портов (от 1 до 1000). При необходимости изменить данный диапазон можно использовать параметр `-p`, например:
```bash
nmap -sU -p 520 172.16.0.1-254
```
В представленной команде указывается сканировать только UDP-порт `520` на всех узлах с адресами от `172.16.0.1` до `172.16.0.254`.
Возможен и противоположный вариант, например, сканировать все TCP-порты от 1 до 65535 на одном узле с адресом `10.0.0.1`:
```bash
nmap -sS -p 1-65535 10.0.0.1
```
Последний параметр называется целью сканирования и определяет множество адресов, для которых должно выполняться сканирование. В нём допустимо указывать:
—	единичный IP адрес: `nmap 192.168.1.1`
—	доменное имя узла: `nmap donstu.local`
—	множество IP-адресов: `nmap 192.168.1.1 192.168.1.2 192.168.1.3`
—	диапазон IP-адресов: `192.168.1.1-254`
—	подсеть: `192.168.1.0/24`

NMap имеет множество дополнительных опций, обеспечивающих широкие возможности сбора информации о сети. Среди них:
- `-sV` — детальное исследование портов для определения версий служб;
- `-O` — определять операционную систему.
Для упрощения использования `NMap` для неё существует официальный графический интерфейс — `ZenMap`.

#### 3.1 Просканируйте какую-либо сеть (реальную или виртуальную) с целью определить операционные системы оборудования, входящего в неё, наличие других сетевых сервисов, используемые служебные сетевые протоколы (например DHCP или протоколы маршрутизации).

```bash
nmap -A -p- <target>
```

В моем случае:

```bash
nmap -A -p- 10.0.2.1-255
```

![image](https://github.com/xarll/vpr/assets/76239707/e187d354-e91e-4d68-92b5-c725180f9c58)


Это занимает какое-то время (у меня несколько минут)


## Контрольные вопросы

> 1.	Что такое `TCPDump`?

Утилита, представляющая собой инструмент для перехвата и анализа сетевых пакетов

> 2.	Приведите примеры нескольких фильтров для `TCPDump`.
- `icmp`
- `tcp or icmp`
- `port 20`

> 3.	Как проанализировать трафик, захваченный с помощью TCPDump в Wireshark?

Сохранить трафик с помощью параметра `-w <path.cap>` и открыть в wireshark

> 4.	Какая статистика может быть получена в Wireshark для сетевого трафика?

Всяка-разная: от списка ip/mac/domain, до структурированного содержания данных пакета

> 5.	Что такое NMap и ZenMap? 

`NMap` - CLI утилита для сканирования сетей/узлов. `ZenMap` - её графический аналог

> 6.	Что является результатом работы NMap/ZenMap?

Результатом работы является список просканированных целей с дополнительной информацией по каждой из них в зависимости от заданных опций. Ключевой информацией является «таблица важных портов».

> 7.	Что означает «открытый», «закрытый» и «фильтруемый» порт?
- open (открыт) - Означает, что приложение на целевой машине готово для установки соединения/принятия пакетов на этот порт
- closed (закрыт) - Закрытые порты не связаны ни с каким приложением, но могут быть открыты в любой момент
- filtered (фильтруется) - Фильтруется означает, что брандмауэр, сетевой фильтр, или какая-то другая помеха в сети блокирует порт, и Nmap не может установить открыт этот порт или закрыт.
- unfiltered - Когда nmap получает их, но не может определить

*Авторство: **Бояршинов Н.О***
