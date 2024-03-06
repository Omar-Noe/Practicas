#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <winsock2.h>

#pragma comment(lib, "ws2_32.lib")

#define PORT 1234
#define SERVER_ADDR "127.0.0.1"

int main() {
    WSADATA wsa;
    SOCKET sock = INVALID_SOCKET;
    struct sockaddr_in serv_addr;
    char message[1024] = {0};

    if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0) {
        printf("Error en WSAStartup\n");
        return 1;
    }

    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET) {
        printf("Error al crear el socket\n");
        return 1;
    }

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    serv_addr.sin_addr.s_addr = inet_addr(SERVER_ADDR);

    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        printf("Error de conexión\n");
        return 1;
    }

    printf("Conectado al servidor.\n");
    printf("Escriba un mensaje: ");
    fgets(message, sizeof(message), stdin);

    send(sock, message, strlen(message), 0);
    printf("Mensaje enviado al servidor.\n");

    char buffer[1024] = {0};
    recv(sock, buffer, 1024, 0);
    printf("Respuesta del servidor: %s\n", buffer);

    closesocket(sock);
    WSACleanup();

    printf("Presiona Enter para salir...");
    getchar(); // Pausa para que el usuario pueda ver la respuesta
    
    return 0;
}
