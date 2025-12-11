# 8. Pedir un número y muestra su tabla de multiplicar del 1 al 10.

num = int(input("Introduce un número "))

for i in range(11):
    print(num , " x ", i , " = ", num*i)