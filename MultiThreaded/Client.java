import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                int port = 8022; // The port the client will connect to
                try {
                    InetAddress address = InetAddress.getByName("localhost"); // Connect to localhost
                    System.out.println("Attempting to connect to server...");

                    // Create a socket and connect to the server
                    try (Socket socket = new Socket(address, port)) {
                        System.out.println("Connected to server.");

                        // Set up communication with the server
                        try (
                            PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                        ) {
                            // Send a message to the server
                            toSocket.println("Hello from client " + socket.getLocalSocketAddress());
                            System.out.println("Message sent to server.");

                            // Read the server's response
                            String line = fromSocket.readLine();
                            System.out.println("Received from server: " + line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Connection failed: " + e.getMessage());
                }
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 5; i++) { // Create 5 client threads
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            } catch (Exception ex) {
                System.err.println("Error starting client thread: " + ex.getMessage());
            }
        }
    }
}
