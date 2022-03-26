# Лаба №3. Настройка системы обмена почтой

## Файлы к лабе

- Порядок выполнения (версия 1) [(.docx)](https://github.com/whitekeep/vpr12/raw/main/seti/lab3/Labwork_3.docx)
- Порядок выполнения (подробная версия) [(.pdf)](https://github.com/whitekeep/vpr12/raw/main/seti/lab3/LabWork_3_v2.pdf)
- Пример отчёта [(.doc)](https://github.com/whitekeep/vpr12/raw/main/seti/lab3/example_report_3.doc)

## Инструкция по выполнению лабы (от Бояршинова)

- В случае обнаружения логических ошибок писать [сюда](https://vk.com/jkearnsl), открывать [Issues](https://github.com/whitekeep/vpr12/issues) или делать [Pull Requests](https://github.com/whitekeep/vpr12/pulls)
- При обнаружении орфаграфических или пунктационных ошибок вы можете самостоятельно редктировать инструкцию при наличии доступа
- Горох временно отсутствует

## Скачиваем образы дистрибутивов

Для выполнения этой лабы нам понадобиться скачать:

- [Oracle VirtualBox](https://download.virtualbox.org/virtualbox/6.1.32/VirtualBox-6.1.32-149290-Win.exe)
- [Ubuntu 20.04 LTS (в первой лабе был такой же)](https://releases.ubuntu.com/20.04.3/ubuntu-20.04.3-live-server-amd64.iso)

Можно использовать любой другой дистрибутив линукса. Главное чтобы была поддержка всех необходимых сетевых утилит

### Особенноти установки VirtualBox

- Необходиом согласиться на установку драйвера когда появиться соответсвующее диалоговое окно

### Особенности использования Ubuntu

- Аккуратнее с [выключением](./lab2.md#как-закрывать-машины) вм
- Во время исподьзования убунтой не выполняйте `apt upgrade`. Это излишне (выполняется 20 минут и займёт кучу места на диске)
- Всё делаем из под `sudo`
- Иногда опущенные интерфейсы сами поднимаются. Если это происходит, опускайте их снова. Следите за этим моментом

## Создание ВМ

- Создадим ВМ
- ![create](https://i.ibb.co/Y2tR2wd/image.png)
- Выбереем версию ОС
- ![create](https://i.ibb.co/4JSbrwj/image.png)
- Выбереем кол-во ОЗУ и жмем далее
- ![create](https://i.ibb.co/5RWTs7h/image.png)
- ![create](https://i.ibb.co/9wrpj4x/image.png)
- ![create](https://i.ibb.co/x6qQ1CC/image.png)
- ![create](https://i.ibb.co/MZf8stW/image.png)
- ![create](https://i.ibb.co/7SjtjVz/image.png)
- Настроим ВМ
- ![settings](https://i.imgur.com/fxdntyJ.png)
- Выберем образ Ubuntu или другой unix OS (в моем случае ссылка на файл сохранилась, выбирарю его)
- ![settings](https://i.imgur.com/PHetOky.png)
