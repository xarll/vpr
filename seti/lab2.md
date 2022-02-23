# Лаба №2. Настройка маршрутизации и ssh

## Файлы к лабе

- Порядок выполнения [(.pdf)](http://s.nektools.ru/LabWork_1.pdf)
- Пример отчёта [(.docx)](http://s.nektools.ru/%D1%81%D0%B5%D1%82%D0%B8%20%D0%BB1%20%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80%20%D0%BE%D1%82%D1%87%D0%B5%D1%82%D0%B0.doc)

## Инструкция по выполнению лабы (от Горшочка)

- В случае обнаружения логических ошибок писать [сюда](https://vk.com/wkeep), открывать [Issues](https://github.com/whitekeep/vpr12/issues) или делать [Pull Requests](https://github.com/whitekeep/vpr12/pulls)
- При обнаружении орфаграфических или пунктационных ошибок вы можете самостоятельно редктировать инструкцию при наличии доступа

## Скачиваем образы дистрибутивов

Для выполнения этой лабы нам понадобиться скачать:

- [Oracle VirtualBox](https://download.virtualbox.org/virtualbox/6.1.32/VirtualBox-6.1.32-149290-Win.exe)
- [Ubuntu 20.04 LTS (в первой лабе был такой же)](https://releases.ubuntu.com/20.04.3/ubuntu-20.04.3-live-server-amd64.iso)

Можно использовать любой другой дистрибутив линукса. Главное чтобы была поддержка всех необходимых сетевых утилит

### Особенноти установки VirtualBox

- Необходиом согласиться на установку драйвера когда появиться соответсвующее диалоговое окно

### Особенности использования Ubuntu

- Во время исподьзования убунтой не выполняйте `apt upgrade`. Это излишне (выполняется 20 минут и займёт кучу места на диске)

## Создание ВМ

- Создадим ВМ
- ![create](https://i.imgur.com/On8N8Rf.png)
- Выбереем версию ОС
- ![settings](https://i.imgur.com/2b4xw0r.png)
- Оперативную память ставим по умолчанию 1024МБ. После заершения установки мы поменяем кол-во памяти на 512МБ для экономии памяти.
- ![settings2](https://i.imgur.com/gnp1p1o.png)
- Создадим виртуальный жёсткий диск
- ![fincreate](https://i.imgur.com/uh6r22h.png)
- Далее
- ![fin2create](https://i.imgur.com/baFzuR3.png)
- Далее
- ![fin3create](https://i.imgur.com/kZmeLQg.png)
- Далее
- ![fin4create](https://i.imgur.com/cLDM4ZZ.png)
- Заходим в настройки ВМ
- ![fin5create](https://i.imgur.com/K9wLNgT.png)
- Стваим галочку возле Сеть в Порядке загрузки
- ![settingssetonvm](https://i.imgur.com/ttStJ0b.png)

## Устанавливаем ОС

### Выбираем образ

- Запускаем ВМ
- ![startvm](https://i.imgur.com/qBeMZ4V.png)
- Выбираем загрузочный диск
- ![choose](https://i.imgur.com/ByXzdhx.png)
- Жмём Добавить
- ![press](https://i.imgur.com/UsyVGUG.png)
- Выбираем скачанный обзар в проводнике
- ![chooseinexp](https://i.imgur.com/rUnOQ4z.png)
- Выбираем из списка
- ![chosefromlist](https://i.imgur.com/9DI1yQD.png)
- Продолжить
- ![next](https://i.imgur.com/hFFN4IV.png)

### Установка

- Выбираем русския язык. Навигация происходит только с помощь клавиш клавиатуры
- ![chrus](https://i.imgur.com/tzGpm9m.png)
- Продолжить без обновления
- ![next](https://i.imgur.com/UhXtL3K.png)
- Готово. Тут мы не мучаемся и выбираем только Английскую раскладку. Из-за этого в убунте мы сможем писать только на английском
- ![next](https://i.imgur.com/yTvbTwH.png)
- Готово
- ![next](https://i.imgur.com/Z3yFvOs.png)
- Готово
- ![next](https://i.imgur.com/83LamPB.png)
- Готово
- ![next](https://i.imgur.com/QRWdQ1o.png)
- Клавишой Вниз идём до Готово и жмём интер
- ![next](https://i.imgur.com/erQT5q5.png)
- Готово
- ![next](https://i.imgur.com/FRKSoHc.png)
- Продолжить
- ![next](https://i.imgur.com/MNbsFUn.png)
- Заполняем поля на сувой вкус и цвет. После жмём Готово
- ![next](https://i.imgur.com/SRzjaoz.png)
- Ставим Х возле пункта установки OpenSHH сервера и жмём Готово
- ![openssh](https://i.imgur.com/yAIWz5g.png)
- Ничего из этого нам не нужно. Жмём Готово
- ![nonepacknext](https://i.imgur.com/iGZkrqs.png)
- Активируем режим ждуна
- ![zhdun](https://i.imgur.com/roltcQZ.png)
- Как только появляется вторая строчка жмём клавишу вниз и Отменяем загрузку обновления (нам оно не нужно)
- ![noupdate](https://i.imgur.com/rzEJfAA.png)
- Тут жмём Enter
- ![enter](https://i.imgur.com/S8IDKsC.png)

Готово. Убунту установлена

## Первичная настройка

### Доустанавливаем пакеты

1. Входим в убунту
2. Повыщаем права до суперпользователя командой `sudo su`
3. Обновляем списки пакетов `apt update`
4. Устанавливаем пакет для просмотра arp таблиц `apt install net-tools`
5. И стваим screen `apt install screen`. Он может пригодиться
6. Завершаем работу ВМ

### Настраиваем сеть на ВМ

- Заходим в настройки ВМ
- ![gotosettings](https://i.imgur.com/K9wLNgT.png)
- Выставляем следующие настроки сети для Адаптера 1
- ![setsetiad1](https://i.imgur.com/arAdHp0.png)
- И следующие для Адаптера 2 и жмём ОК
- ![setsetiad2](https://i.imgur.com/BNwAIOn.png)

### Урезаем кол-во оперативной памяти

Для нашего дистрибутива (без графики) будет достаточно 512МБ оперативной памяти на ВМ

- Заходим в настройки
- ![toset](https://i.imgur.com/K9wLNgT.png)
- Уменьшаем память
- ![minopera](https://i.imgur.com/1yGi9Xf.png)
- Готово

### Клонирование ВМ

Дважды клонируем нашу ВМ со слежующими параметрами:

- Клонирование
- ![cloning](https://i.imgur.com/G1QrL7F.png)

- Также выставим название при первом клонировании `Two - Ubuntu` и при втором `Three - Ubuntu`. Это необходимо для удобства различимости их между собой
- ![setclon](https://i.imgur.com/d06VaIT.png)
- Выбираем Полное копирование
- ![set2clon](https://i.imgur.com/oQsY3z1.png)
- В итоге получаем три ВМ:
- ![allvms](https://i.imgur.com/dyJbJeQ.png)

Готово. Первичная настройка завершена

## Настраиваем сеть на машинах

- Повысим права `sudo su` и выполняем `ip a`. Видим что у `enp0s8`нету апишника
- ![look](https://i.imgur.com/cNsMwDu.png)
- Выдадим его `dnclient enp0s8` и проверим повторно командой `ip a`. Всё отлично, он появился
![addip](https://i.imgur.com/ScaBEjH.png)
- Выполним так для каждой из трёх машин

