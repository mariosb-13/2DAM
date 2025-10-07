#include <stdio.h>
#include <unistd.h>   // Para fork()
#include <sys/types.h> // Para pid_t
#include <sys/wait.h>  // Para wait()

int main() {
    int num;
    printf("Introduce un número: ");
    scanf("%d", &num);

    pid_t pid = fork(); // Crear proceso hijo

    if (pid < 0) {
        // Error al crear el proceso
        perror("Error al crear el proceso");
        return 1;
    } else if (pid == 0) {
        // Este es el proceso hijo
        int hijo_resultado = num + 4;
        printf("Proceso hijo: %d + 4 = %d\n", num, hijo_resultado);
    } else {
        // Este es el proceso padre
        int padre_resultado = num - 5;
        printf("Proceso padre: %d - 5 = %d\n", num, padre_resultado);

        // Esperar a que termine el hijo para que la salida sea ordenada
        wait(NULL);
    }

    return 0;
}
