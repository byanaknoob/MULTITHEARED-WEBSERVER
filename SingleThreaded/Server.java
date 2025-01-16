import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port: " + port);

            while (true) {
                // Accept a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to " + clientSocket.getRemoteSocketAddress());

                try (PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    // Send a message to the client
                    toClient.println("Hello World from the server");

                    // Read a message from the client
                    String clientMessage = fromClient.readLine();
                    if (clientMessage != null) {
                        System.out.println("Received from client: " + clientMessage);
                    }

                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    clientSocket.close();
                }
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
