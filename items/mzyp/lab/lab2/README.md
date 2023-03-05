# Лабораторная работа #1

## Работа

Переходим во 2й пункт методички, в самом конце написаны задания под Ваш вариант.

* [Методические указания I семестр.doc](../%D0%9C%D0%B5%D1%82%D0%BE%D0%B4%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B5%20%D1%83%D0%BA%D0%B0%D0%B7%D0%B0%D0%BD%D0%B8%D1%8F%20I%20%D1%81%D0%B5%D0%BC%D0%B5%D1%81%D1%82%D1%80.doc)

##### Вариант 2
```asm
; 2. X= -4A + (B + C)/4 + 2

data segment
    a dw 10
    b dw 20
    c dw 5
    x dw ?
data ends
code segment    
    assume cs: code, ds: data
    start: 
        mov ax, data
        mov ds, ax 		; load addresses 
        mov ax, a 		; data segment
        sal ax, 2	    ; ax*2*2 => 4A

        mov bx, b       ; bx = b
        add bx, c       ; bx + c => b + c
        sar bx, 2       ; bx/2*2 => (b + c)/4
        sub bx, ax      ; bx - ax => (b + c)/4 - 4A 
        add bx, 2       ; bx + 2 => (b + c)/4 - 4A + 2
        dec bx
        mov x, bx 		; record result into x
    quit:
        mov ax, 4c00h 	; end code 0
        int 21 			; exit into dos
    end start
code ends
```

##### Вариант 12

```asm
; 12. X= 6(A - 2B + C/4) + 10

data segment
    a dw 10
    b dw 20
    c dw 5
    x dw ?
data ends
code segment    
    assume cs: code, ds: data
    start: 
        mov ax, data
        mov ds, ax 		; load addresses 
        mov ax, a 		; A
        mov bx, b
        sal bx, 1       ; 2B
        
        sub ax, bx      ; A - 2B
        mov bx, c       ; C
        sar bx, 2       ; C/4
        add ax, bx      ; A-2B + C/4
        mov bx, 6       ; bx = 6
        mul ax          ; 6(A-2B + C/4)
        mov bx, 10      ; bx = 10
        add ax, bx      ;
        mov x, ax 		; record result into x
    quit:
        mov ax, 4c00h 	; end code 0
        int 21 			; exit into dos
    end start
code ends
```

*Авторство: **Бояршинов Н.О***
