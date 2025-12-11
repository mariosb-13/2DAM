# 11. Pedir números hasta que se introduzca el 0. Si el número introducido es negativo
# ignorar, si es positivo se usará para contabilizarlo y calcular la suma y media.
# Cuando se introduzca el 0 mostrar:
# Números: X (el número de datos introducidos sin contar el 0)
# Suma: Y (la suma de todos los números introducidos)
# Media: Z (la media de todos los números introducidos)

numeros = 0
suma = 0
cant_num = 0
num = 1

while num != 0:
    num = int(input("Introduce un número "))
    cant_num += 1
    if num > 0:
        numeros += 1
        suma += num
    else:
        continue

print("Números: ", cant_num - 1)
print("Suma: ", suma)
print("Media: ", suma / numeros)
