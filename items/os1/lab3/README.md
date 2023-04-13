# Лабораторная работа #3 - Терминалы, многопользовательский доступ, виртуальная память

## Методички
- Задание [Laboratornaya_rabota3.docx](https://docs.google.com/document/d/1sbwQSSYawmDS1aE20lZQt9qFaLwW9cJX/edit?usp=share_link&ouid=114433453162808919564&rtpof=true&sd=true)

## Ход работы

Пока тут пусто, но... вы можете это дописать ^_^

### Python скрипт для вызова утечки памяти

```python
i = 10
s = []
while True:
  s.append(i)
  i = i*2
```
PS: Питон предустановлен почти во всех linux дистрибутивах

- Аналог - [https://vk.cc/cn4PkW](https://vk.cc/cn4PkW) `*Однако, он менее эффективный. Возможно, потребуется запуск сразу нескольких в разных tty*
