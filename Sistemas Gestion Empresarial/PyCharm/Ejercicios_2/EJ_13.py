# 13. Crea la función es_par(n) que devuelva True si el número es par y False si es impar.
from operator import truediv


def es_par(n):
    if n%2==0:
        return True
    else:
        return False
print(es_par(2))