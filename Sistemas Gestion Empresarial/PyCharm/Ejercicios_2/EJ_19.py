# 19. Crea la función contar_vocales(cadena) que imprima el número de vocales que hay
# y cuántas hay de cada una de ella, por ejemplo, si recibe “hola” imprimirá:
# vocales: 2

def contar_vocales(cadena):
    cadena = cadena.lower()

    vocales_definidas = "aeiou"

    total_vocales = 0
    conteo_individual = {}

    for letra in cadena:
        if letra in vocales_definidas:
            total_vocales += 1
            # Si la vocal ya está en el diccionario, sumamos 1, si no, la creamos
            if letra in conteo_individual:
                conteo_individual[letra] += 1
            else:
                conteo_individual[letra] = 1
    print(f"vocales: {total_vocales}")

    for vocal in sorted(conteo_individual):
        print(f"{vocal}: {conteo_individual[vocal]}")


cadena = input("Introduzca una cadena ")
contar_vocales(cadena)
