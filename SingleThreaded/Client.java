import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void run() throws IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);

        try (PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            toSocket.println("Hello World from socket " + socket.getLocalSocketAddress());
            String line = fromSocket.readLine();  // Wait for server's response
            System.out.println("Received from server: " + line);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
