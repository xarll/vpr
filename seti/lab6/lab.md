# Лаба №6. Удаленный вызов процедур с помощью XML-RPC (в процессе)
## Файлы к лабе

- О XML-RPC и список вариантов [(.doc)](./XML-RPC%20%D0%BC%D0%B5%D1%82%D0%BE%D0%B4%D0%B8%D1%87%D0%BA%D0%B0.doc?raw=true)

## Быстрый старт
- Список вариантов [(.pdf)](./XML-RPC_Problems.pdf)
- Инструкция сборки [ТЫК](https://github.com/mxsleo/xmlrpc-c_example/blob/master/README.md)

## Выполнение лабораторной работы
- Требуется наличие Debian-подобной ОС (лучше всего две виртуальные машины в одной NAT-сети - прим. mxsleo)
- Необходима установка сборочных библиотек и утилит
- Необходимо установить файлы разработки XML-RPC C
- Предоставлены готовые сервер и клиент (поиск суммы n чисел) для UNIX
- Вы переписываете логику в соответствии с вариантом и собираете программы
- Сначала запускается сервер, а затем клиент

## Файлы шаблона:
- Шаблон сервера [(.c)](https://github.com/mxsleo/xmlrpc-c_example/blob/master/unix_server.c)
- Шаблон клиента [(.c)](https://github.com/mxsleo/xmlrpc-c_example/blob/master/unix_client.c)

## Инструкция:

Подробная инструкция сборки приложений описана [ТУТ](https://github.com/mxsleo/xmlrpc-c_example/blob/master/README.md)

### Типы данных:

<table>
<thead>
<tr class="header">
<th><p>Имя типа</p></th>
<th><p>Пример тега</p></th>
<th><p>Описание типа</p></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p>array</p></td>
<td><div class="sourceCode" id="cb1"><pre
class="sourceCode numberSource xml numberLines"><code class="sourceCode xml"><span id="cb1-1"><a href="#cb1-1"></a> &lt;<span class="kw">array</span>&gt;</span>
<span id="cb1-2"><a href="#cb1-2"></a>   &lt;<span class="kw">data</span>&gt;</span>
<span id="cb1-3"><a href="#cb1-3"></a>     &lt;<span class="kw">value</span>&gt;&lt;<span class="kw">i4</span>&gt;1404&lt;/<span class="kw">i4</span>&gt;&lt;/<span class="kw">value</span>&gt;</span>
<span id="cb1-4"><a href="#cb1-4"></a>     &lt;<span class="kw">value</span>&gt;&lt;<span class="kw">string</span>&gt;Что-нибудь здесь&lt;/<span class="kw">string</span>&gt;&lt;/<span class="kw">value</span>&gt;</span>
<span id="cb1-5"><a href="#cb1-5"></a>     &lt;<span class="kw">value</span>&gt;&lt;<span class="kw">i4</span>&gt;1&lt;/<span class="kw">i4</span>&gt;&lt;/<span class="kw">value</span>&gt;</span>
<span id="cb1-6"><a href="#cb1-6"></a>   &lt;/<span class="kw">data</span>&gt;</span>
<span id="cb1-7"><a href="#cb1-7"></a> &lt;/<span class="kw">array</span>&gt;</span></code></pre></div></td>
<td><p>Массив величин, без ключей</p></td>
</tr>
<tr class="even">
<td><p>base64</p></td>
<td><div class="sourceCode" id="cb2"><pre
class="sourceCode xml"><code class="sourceCode xml"><span id="cb2-1"><a href="#cb2-1" aria-hidden="true" tabindex="-1"></a>&lt;<span class="kw">base64</span>&gt;eW91IGNhbid0IHJlYWQgdGhpcyE=&lt;/<span class="kw">base64</span>&gt;</span></code></pre></div></td>
<td><p>Кодированные в <a href="https://ru.wikipedia.org/wiki/Base64" title="wikilink">Base64</a>
двоичные данные</p></td>
</tr>
<tr class="odd">
<td><p>boolean</p></td>
<td><div class="sourceCode" id="cb3"><pre
class="sourceCode xml"><code class="sourceCode xml"><span id="cb3-1"><a href="#cb3-1" aria-hidden="true" tabindex="-1"></a>&lt;<span class="kw">boolean</span>&gt;1&lt;/<span class="kw">boolean</span>&gt;</span></code></pre></div></td>
<td><p>Логическая (булева) величина (0 или 1)</p></td>
</tr>
<tr class="even">
<td><p>date/time</p></td>
<td><div class="sourceCode" id="cb4"><pre
class="sourceCode xml"><code class="sourceCode xml"><span id="cb4-1"><a href="#cb4-1" aria-hidden="true" tabindex="-1"></a>&lt;<span class="kw">dateTime.iso8601</span>&gt;19980717T14:08:55&lt;/<span class="kw">dateTime.iso8601</span>&gt;</span></code></pre></div></td>
<td><p>Дата и время</p></td>
</tr>
<tr class="odd">
<td><p>double</p></td>
<td><div class="sourceCode" id="cb5"><pre
class="sourceCode xml"><code class="sourceCode xml"><span id="cb5-1"><a href="#cb5-1" aria-hidden="true" tabindex="-1"></a>&lt;<span class="kw">double</span>&gt;-12.53&lt;/<span class="kw">double</span>&gt;</span></code></pre></div></td>
<td><p>Дробная величина двойной точности</p></td>
</tr>
<tr class="even">
<td><p>integer</p></td>
<td><div class="sourceCode" id="cb6"><pre
class="sourceCode xml"><code class="sourceCode xml"><span id="cb6-1"><a href="#cb6-1" aria-hidden="true" tabindex="-1"></a>&lt;<span class="kw">i4</span>&gt;42&lt;/<span class="kw">i4</span>&gt;</span></code></pre></div></td>
<td><p>Целое число</p></td>
</tr>
<tr class="odd">
<td><p>string</p></td>
<td><div class="sourceCode" id="cb7"><pre
class="sourceCode xml"><code class="sourceCode xml"><span id="cb7-1"><a href="#cb7-1" aria-hidden="true" tabindex="-1"></a>&lt;<span class="kw">string</span>&gt;Здравствуй, Мир!&lt;/<span class="kw">string</span>&gt;</span></code></pre></div></td>
<td><p>Строка символов (в той же кодировке, что и весь <a href="https://ru.wikipedia.org/wiki/XML"
title="wikilink">XML</a>-документ)</p></td>
</tr>
<tr class="even">
<td><p>struct</p></td>
<td><div class="sourceCode" id="cb8"><pre
class="sourceCode xml"><code class="sourceCode xml"><span id="cb8-1"><a href="#cb8-1" aria-hidden="true" tabindex="-1"></a> &lt;<span class="kw">struct</span>&gt;</span>
<span id="cb8-2"><a href="#cb8-2" aria-hidden="true" tabindex="-1"></a>   &lt;<span class="kw">member</span>&gt;</span>
<span id="cb8-3"><a href="#cb8-3" aria-hidden="true" tabindex="-1"></a>     &lt;<span class="kw">name</span>&gt;Что-то&lt;/<span class="kw">name</span>&gt;</span>
<span id="cb8-4"><a href="#cb8-4" aria-hidden="true" tabindex="-1"></a>     &lt;<span class="kw">value</span>&gt;&lt;<span class="kw">i4</span>&gt;1&lt;/<span class="kw">i4</span>&gt;&lt;/<span class="kw">value</span>&gt;</span>
<span id="cb8-5"><a href="#cb8-5" aria-hidden="true" tabindex="-1"></a>   &lt;/<span class="kw">member</span>&gt;</span>
<span id="cb8-6"><a href="#cb8-6" aria-hidden="true" tabindex="-1"></a>   &lt;<span class="kw">member</span>&gt;</span>
<span id="cb8-7"><a href="#cb8-7" aria-hidden="true" tabindex="-1"></a>     &lt;<span class="kw">name</span>&gt;Ещё что-то&lt;/<span class="kw">name</span>&gt;</span>
<span id="cb8-8"><a href="#cb8-8" aria-hidden="true" tabindex="-1"></a>     &lt;<span class="kw">value</span>&gt;&lt;<span class="kw">i4</span>&gt;2&lt;/<span class="kw">i4</span>&gt;&lt;/<span class="kw">value</span>&gt;</span>
<span id="cb8-9"><a href="#cb8-9" aria-hidden="true" tabindex="-1"></a>   &lt;/<span class="kw">member</span>&gt;</span>
<span id="cb8-10"><a href="#cb8-10" aria-hidden="true" tabindex="-1"></a> &lt;/<span class="kw">struct</span>&gt;</span></code></pre></div></td>
<td><p>Массив величин, с ключами</p></td>
</tr>
<tr class="odd">
<td><p>nil</p></td>
<td><div class="sourceCode" id="cb9"><pre
class="sourceCode xml"><code class="sourceCode xml"><span id="cb9-1"><a href="#cb9-1" aria-hidden="true" tabindex="-1"></a>&lt;<span class="kw">nil</span>/&gt;</span></code></pre></div></td>
<td><p>Нулевая (пустая) величина — это <a
href="https://web.archive.org/web/20070309154227/http://ontosys.com/xml-rpc/extensions.php">расширение</a>
XML-RPC</p></td>
</tr>
</tbody>
</table>

## Инструкция и пример работы
Буду предполагать, что виртуальные машины уже установлены и настроены. В противном случае можно обратиться к одному из многочисленных гайдов в интернете или к предыдущим лабораторным работам. В моём случае в качестве клиента и сервера выступают виртуальные машины с установленной Xubuntu 22.04 LTS, объединённые в одну общую NAT-сеть. Дальнейшие действия будут производиться в соответствии с инструкцией в моём личном репозитории: https://github.com/mxsleo/xmlrpc-c_example.

### Подготовка сборочной среды
Для начала установим на обе машины сборочные библиотеки и утилиты:
```
sudo apt install clang gcc git make libcurl4-openssl-dev libssl-dev
```
Затем скачаем и установим файлы разработки XML-RPC C, также на обе системы:
```
cd ~/
wget -c https://sourceforge.net/projects/xmlrpc-c/files/Xmlrpc-c%20Super%20Stable/1.54.05/xmlrpc-c-1.54.05.tgz
tar -xvf xmlrpc-c-1.54.05.tgz
cd xmlrpc-c-1.54.05
./configure
make
sudo make install
sudo ldconfig
```
На этапе загрузки архива исхов XML-RPC C (вторая команда) рекомендую проверить наличие в домашнем каталоге самого архива, в случае ошибки загрузку повторить.
После установки можно удалить временные объекты:
```
rm -rf ~/xmlrpc-c-1.54.05.tgz ~/xmlrpc-c-1.54.05
```

### Загрузка и сборка примеров
Как только мы подготовили обе машины, можно приступать к загрузке моих исходников сервера и клиента со сборочной системой на обе виртуалки:
```
cd ~/
git clone https://github.com/mxsleo/xmlrpc-c_example.git
cd xmlrpc-c_example
```
Затем нужно собрать программы. Если работа осуществляется на одном компьютере, то выполняем:
```
make
```
Иначе, на сервере запускаем:
```
make server
```
А на клиенте:
```
make client
```
В случае успеха, в каталоге ~/xmlrpc-c_example появятся две программы - unix_server и unix_client. Иначе система сообщит детали об ошибке прямо в терминале.

### Запуск сервера и клиента
Теперь, когда сервер собран, его можно запустить:
```
./unix_server <port>
```
Например,
```
./unix_server 8080
```
Если работа осуществляется на одной машине, его можно запустить в фоновом режиме:
```
./unix_server <port> &
```
Чтобы вернуть его с фонового выполнения, можно использовать:
```
fg
```
Для остановки используется сочетание клавиш `Ctrl+C`. Клиент запустить ещё проще:
```
./unix_client <server ip> <port>
```
Например,
```
./unix_client 192.168.0.10 8080
```
Если клиент и сервер - это одна машина, используется команда:
```
./unix_client localhost 8080
```
В примере дано взаимодействие следующего вида: клиент запрашивает количество чисел, сами числа, посылает их на сервер и просит просуммировать. Сервер возвращает готовую сумму. Можно подробно рассмотреть запросы, воспользовавшись утилитой `tcpdump`:
```
sudo tcpdump -i any -A port 8080
```

### Изменение логики программ
Вся логика сервера прописана в этих строках: описание [функции](https://github.com/mxsleo/xmlrpc-c_example/blob/master/unix_server.c#L20:L59) и внесение её в [реестр](https://github.com/mxsleo/xmlrpc-c_example/blob/master/unix_server.c#L65:L68). Логика клиента прописана в этих строках: объявление [имени метода](https://github.com/mxsleo/xmlrpc-c_example/blob/master/unix_client.c#L29) и его [вызов](https://github.com/mxsleo/xmlrpc-c_example/blob/master/unix_client.c#L45:L81). Мы можем менять её как угодно, для этого достаточно понимать, что передавать надо данные в формате xmlrpc_value и функцию объявлять по образцу моего сервера. Подробнее про преобразование различных типов к xmlrpc_value и обратно можно почитать в документации: [шифрование](http://xmlrpc-c.sourceforge.net/doc/libxmlrpc.html#creatingxmlrpcvalue), [дешифрование](http://xmlrpc-c.sourceforge.net/doc/libxmlrpc.html#interpreting), [литералы](http://xmlrpc-c.sourceforge.net/doc/libxmlrpc.html#formatstring). Там же можно получить необходимую информацию о принципе работы программы и найти другие примеры.

Если будут вопросы, пишите [VK](https://vk.com/mxsleo).
