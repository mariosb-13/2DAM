# 20. Crea la función numero_entre(a, b) que pida un número al usuario entre a y b,
# siendo mayor o igual que a y menor o igual que b. Si el número no cumple esa
# condición deberá indicar que no es válido y volverá a pedir el número, hasta que
# introduzca un número válido y lo devolverá.

def numero_entre(a, b):
    while True:  # Iniciamos un bucle infinito
        # Solicitamos el número y lo convertimos a entero (int)
        # Usamos f-strings (f"...") para insertar las variables a y b en el texto
            num = int(input(f"Introduzca un número entre {a} y {b}: "))

            # Comprobamos si está dentro del rango (inclusivo)
            if a <= num <= b:
                return num  # Si es válido, devolvemos el número y la función termina
            else:
                print("No es válido. Inténtelo de nuevo.")

if __name__ == "__main__":
    resultado = numero_entre(1, 10)
    print(f"El número válido introducido es: {resultado}")