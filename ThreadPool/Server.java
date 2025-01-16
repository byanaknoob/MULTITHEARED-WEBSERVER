import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Server {
    
	private final ExecutorService threadPool;
	
	public Server(int poolSize)
	{
		this.threadPool=Executors.newFixedThreadPool(poolSize);
	}
	

    /**
     * Handles communication with the client.
     *
     * @param clientSocket The socket connected to the client.
     */
    private void handleClient(Socket clientSocket) {
        try (PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true)) {
            toSocket.println("Hello from server " + clientSocket.getInetAddress());
        } catch (IOException ex) {
            System.err.println("Error handling client: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                System.err.println("Error closing client socket: " + ex.getMessage());
            }
        }
    }
    
    /**
     * Starts the server and listens for client connections.
     *
     * @param port The port on which the server listens.
     * @throws IOException 
     */
    
    public void start(int port) 
    {
    	try(ServerSocket serverSocket = new ServerSocket(port))
    	{
    		  System.out.println("Server is listening on port " + port);
    		  while(true)
    		  {
    			  Socket clientSocket =  serverSocket.accept();
    			  threadPool.execute(()->handleClient(clientSocket));
    		  }
    	}
    	catch (IOException ex) {
            System.err.println("Server error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            shutdownThreadPool();
        }
    }
    
    /**
     * Shuts down the thread pool gracefully.
     */
    private void shutdownThreadPool() {
        threadPool.shutdown();
        System.out.println("Thread pool shut down.");
    }
    
    public static void main(String[] args) {
        int port = 8020;
        int poolSize = 10; 

        Server server = new Server(poolSize);
        server.start(port);
    }
}
