import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_ADDR = "127.0.0.1";
        final int PORT = 1234;

        try (Socket socket = new Socket(SERVER_ADDR, PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Conectado al servidor. Envíe un número entero (escriba 0 para salir):");
            String userInput;
            while ((userInput = input.readLine()) != null) {
                if (userInput.equals("0")) {
                    System.out.println("Cliente cerrado.");
                    break;
                }

                try {
                    int number = Integer.parseInt(userInput);
                    output.println(number);

                    int response = Integer.parseInt(reader.readLine());
                    System.out.println("Respuesta del servidor: " + response);
                } catch (NumberFormatException e) {
                    System.out.println("El servidor ha enviado una respuesta no válida.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
