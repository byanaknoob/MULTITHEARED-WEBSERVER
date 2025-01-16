import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public static void main(String[] args) {
        int port = 8022; // Port the server listens on
        Server server = new Server();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Infinite loop to accept and handle clients
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept a client connection
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create and start a new thread to handle the client
                Thread thread = new Thread(() -> server.getConsumer().accept(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage());
        }
    }

    // Consumer to handle client communication
    private Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try (
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                // Read the client's message
                String clientMessage = fromClient.readLine();
                System.out.println("Received from client: " + clientMessage);

                // Respond to the client
                toClient.println("Hello from server " + clientSocket.getInetAddress());
                System.out.println("Response sent to client: " + clientSocket.getInetAddress());
            } catch (IOException ex) {
                System.err.println("Error handling client: " + ex.getMessage());
            }
        };
    }
}
