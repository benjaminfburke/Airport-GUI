
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;

public class ReservationServer {

    private ServerSocket serverSocket;

    public ReservationServer() throws IOException {
        this.serverSocket = new ServerSocket(0);

    } //ReservationServer

    public void serveClients() {
        Socket clientSocket;
        ReservationRequestHandler handler;
        Thread handlerThread;
        int connectionCount = 0;

        System.out.printf("<Now serving clients on port %d...>%n", this.serverSocket.getLocalPort());

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();

                break;
            } //end try catch

            handler = new ReservationRequestHandler(clientSocket);

            handlerThread = new Thread(handler);

            handlerThread.start();

            System.out.printf("<Client %d connected...>%n", connectionCount);

            connectionCount++;
        } //end while
    } //serveClients
}
