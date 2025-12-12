from math import pi

print("Elija una opcion para calcular el área")
print("1. Círculo")
print("2. Triangulo")
print("3. Rectángulo")

num = int(input("Selecciona una opcion: "))
match num:
    case 1:
        radio = float(input("Introduzca el radio "))
        area=pi * (radio ** 2)
        print("El radio del cirulo es ", area)
    case 2:
        base = float(input("Introduzca la base "))
        altura = float(input("Introduzca la altura "))
        area=(base * altura) / 2
        print("El área del triángulo es ", area )
    case 3:
        base = float(input("Introduzca la base "))
        altura = float(input("Introduzca la altura "))
        area=base * altura
        print("El área del rectángulo es " , area)

