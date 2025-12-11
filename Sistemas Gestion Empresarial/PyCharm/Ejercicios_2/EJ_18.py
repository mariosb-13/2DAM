# 18. Crea la función es_palindromo(cadena) que devuelva True si la cadena se lee igual
# al derecho y al revés.

def es_palindromo(cadena):
    if  cadena[::-1] == cadena:
        return True
    else:
        return False

print(es_palindromo("ana"))