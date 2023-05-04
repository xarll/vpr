# Лабораторная работа #6.2 - Протоколы Telnet и SSH

## Материалы
- [Lab-6.2 - Протоколы Telnet и SSH.docx](./Lab-6.2%20-%20%D0%9F%D1%80%D0%BE%D1%82%D0%BE%D0%BA%D0%BE%D0%BB%D1%8B%20Telnet%20%D0%B8%20SSH.docx)

## Работа

Нам потребуется 2 ВМ. Я буду использовать две [Ubuntu Server](https://ubuntu.com/download/server#downloads)


### Настроим сеть NAT

Для начала создадим общую сеть NAT для виртуальных машин.

Откройте **Файл** -> **Инструменты** -> **Менеджер сетей**

![Screenshot_20230504_193557](https://user-images.githubusercontent.com/76239707/236268229-2c33b00f-64cc-4dc7-9a72-25b011854a9f.png)

Выберите **Сети NAT** и нажмите **Создать**. Должно получится примерно как на скриншоте:

![Screenshot_20230504_193718](https://user-images.githubusercontent.com/76239707/236268652-ab9b7cd1-0879-43e3-ab19-1ae709efd3e8.png)

Далее для каждой ВМ выполните откройте **Настройки** -> **Сеть** -> Выберите именно **Сеть NAT** для первого адаптера (не путать с просто **NAT**). Имя только что созданной сети. Настраиваемая машина в это время должна быть отключена

![Screenshot_20230504_193826](https://user-images.githubusercontent.com/76239707/236269064-6048cf53-ef83-481d-bf65-08b453aa44b4.png)

Поздравляем, сеть настроена!

### ВМ 1

Имеем

![Screenshot_20230504_192926](https://user-images.githubusercontent.com/76239707/236266629-ee5bacd4-f773-4855-9ee8-4136d3b1ae74.png)

Установим `telnetd` - демон, который будет являться сервером telnet

```bash
sudo apt update && sudo apt-get install telnetd

```

### ВМ 2

Имеем

![Screenshot_20230504_192959](https://user-images.githubusercontent.com/76239707/236266798-3c389e7c-93d9-489b-b6f1-232de8563da7.png)


*Авторство: **Бояршинов Н.О***
