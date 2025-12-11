# Pide un número al usuario y comprueba si es múltiplo de 2, de 3, de 5 o no es múltiplo de esos números.

num = int(input("Introduce un número "))

if num % 2 ==0 or num%3==0 or num%5==0:
    print("El número es múltiplo de 2")
    if num%3==0:
        print("El número es múltiplo de 3")
    if num%5==0:
        print("El número es múltiplo de 5")
