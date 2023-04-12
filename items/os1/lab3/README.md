# Лабораторная работа #3 - Терминалы, многопользовательский доступ, виртуальная память

## Методички
- Задание [Laboratornaya_rabota3.docx](https://docs.google.com/document/d/1sbwQSSYawmDS1aE20lZQt9qFaLwW9cJX/edit?usp=share_link&ouid=114433453162808919564&rtpof=true&sd=true)

## Ход работы

Пока тут пусто, но...

### Python скрипт для вызова утечки памяти

```python
i = 10
s = []
while True:
  s.append(i)
  i = i*2
```

- Аналог (бинарник, не требует установки питона) - [https://vk.cc/cn4PkW](https://vk.cc/cn4PkW) `*Однако, он менее эффективный. Возможно, потребуется запуск сразу нескольких в разных tty*
