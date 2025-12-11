# Pide un número n y calcula la suma de los números del 1 al n, por ejemplo, si introduce el 4 muestra 10 (1+2+3+4).

num=int(input("Introduzca un numero "))

i=num
while i<10:
    i-=1
    print(num+i)
