#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <winsock2.h>

#pragma comment(lib, "ws2_32.lib")

#define PORT 1234

int main() {
    WSADATA wsa;
    SOCKET server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    int client_addr_len = sizeof(client_addr);
    int received_number, response_number;

    // Inicializar Winsock
    if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0) {
        printf("Error al inicializar Winsock\n");
        return 1;
    }

    // Crear socket
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == INVALID_SOCKET) {
        printf("Error al crear el socket del servidor\n");
        return 1;
    }

    // Configurar la dirección del servidor
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    server_addr.sin_port = htons(PORT);

    // Enlazar el socket a la dirección del servidor
    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) == SOCKET_ERROR) {
        printf("Error al enlazar el socket a la dirección del servidor\n");
        closesocket(server_socket);
        WSACleanup();
        return 1;
    }

    // Escuchar por conexiones entrantes
    if (listen(server_socket, 5) == SOCKET_ERROR) {
        printf("Error al intentar escuchar por conexiones entrantes\n");
        closesocket(server_socket);
        WSACleanup();
        return 1;
    }

    printf("Servidor esperando conexiones en el puerto %d\n", PORT);

    // Aceptar conexiones entrantes y procesar números
    while (1) {
        // Aceptar la conexión entrante
        client_socket = accept(server_socket, (struct sockaddr *)&client_addr, &client_addr_len);
        if (client_socket == INVALID_SOCKET) {
            printf("Error al aceptar la conexión entrante\n");
            continue;
        }

        printf("Cliente conectado\n");

        // Procesar los números enviados por el cliente
        while (1) {
            // Recibir el número del cliente
            if (recv(client_socket, (char *)&received_number, sizeof(received_number), 0) == SOCKET_ERROR) {
                printf("Error al recibir datos del cliente\n");
                break;
            }

            if (received_number == 0) {
                printf("Cliente ha enviado 0. Terminando conexión con este cliente.\n");
                break;
            }

            // Incrementar el número recibido
            response_number = received_number + 1;

            // Enviar el número incrementado al cliente
            if (send(client_socket, (char *)&response_number, sizeof(response_number), 0) == SOCKET_ERROR) {
                printf("Error al enviar datos al cliente\n");
                break;
            }

            printf("Cliente envió %d, servidor responde con %d\n", received_number, response_number);
        }

        // Cerrar la conexión con el cliente
        closesocket(client_socket);
    }

    // Cerrar socket del servidor
    closesocket(server_socket);
    WSACleanup();

    return 0;
}
