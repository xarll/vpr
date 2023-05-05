# Лабораторная работа #6 

Нужно написать 2 bash скрипта по задаям из материалов

## Материалы

![image](https://user-images.githubusercontent.com/76239707/235899010-40e81cde-5947-4cb4-83a1-a752dc042408.png)


## Работа

### №7: Циклическое чтение системного времени и очистка экрана в заданный момент


```bash
#!/bin/sh

echo -n "Введите время в формате час:мин, пример <<10:05>>: "
read hourAndMin

echo -n "Введите дату в формате день/месяц/год, пример <<05/05/2023>>: "
read dateWithoutHour

datetime=$(date -d "$dateWithoutHour $hourAndMin"":00""" +"%D %T")
timestamp=$(date -d "$datetime" +%s)

echo "В " $datetime " очистится экран.


while [ $(date +%s) -lt $timestamp ];
do
    sleep 1
done

clear


```

### №8: Циклический просмотр списка файлов и выдача сообщения при появлении заданного имени в списке

```bash
#!/bin/sh

echo -n "Введите имя файла: "
read filename

while true;
do
    if ls | grep -q "$filename";
    then
        echo "Файл $filename найден!"
        break
    fi
    sleep 1
done


```

PS: Вместо ввода имени файла, можно передать его в позиционный аргумент программы


*Авторство: **Бояршинов Н.О***
