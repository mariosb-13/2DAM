# 15. Crea la función maximo_lista(lista) que devuelva el mayor de los números pasados
# por argumento.
from uuid import MAX


def maximo_lista(*lista):
    return max(lista)

print(maximo_lista(1,99,3,4))