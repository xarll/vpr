
; 1.7

; bytes

mov ax, 0102h

add ah, al     ; ah - 03
               
               
; 1.7.1 multiply 
               
mov ax, 7c4bh
mov bx, 100h

mul bx         ; DX = 00 7C # AX = 4B 00


; 1.7.2 divide 

; 00 7C 4B 12 divide 01 00

mov dx, 007Ch
mov ax, 4B12h

mov bx, 0100h

div bx          ; AX = 7C 4B # DX = 00 12           


; 1.7.3 copy and move

mov ax, 1234h 
mov dx, 0ABCDh  ; Stupid compiler: '0' needed

mov ah, dl      ; ax = CD 34


; 1.7.4 overflow

mov ax, 0FFFFh
mov bx, 0001h

add ax, bx      ; ax = 00 00
              
              
; 1.7.5 registr flags

; C, P, A,  Z, S, T, I, D, O
; States: 1 - True, 0 - False


; 1.7.6 Flag 'C' - Carry Flag

mov ax, 0FFFFh
mov bx, 0001h

add ax, bx      ; ax = 00 00; CF = 1


mov ax, 0FF00h
mov bx, 0001h

add ax, bx      ; ax = 00 00; CF = 0


; 1.7.7.1 ADD with Carry Flag

; a) FFFF ADD 1

mov ax, 0FFFFh
mov bx, 1h

add ax, bx       ; CF = 1

; b) exec ADC BX, AX

adc bx, ax       ; bx = 00 02 # CF = 0

  
; 1.7.7.2 divide with C flag

; a) FFFF ADD 1

mov ax, 0FFFFh
mov bx, 1h

add ax, bx       ; CF = 1

; b) exec SBB BX, AX

sbb bx, ax       ; bl = 00 # CF = 0


; 1.7.8 Zero Flag - Z

mov ax, 2h
mov bx, 2h

sbb bx, ax       ; ZF = 1


; 1.7.9 Sign Flag - S

mov ax, 0
mov bx, 1

sub ax, bx       ; ax = FF FF # SF = 1


; 1.7.10 Overflow flag

mov ax, 7000h
mov bx, 6000h

add ax, bx       ; ax = D0 00  # OF = 1 