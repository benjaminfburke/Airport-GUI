import java.io.*;
import java.net.Socket;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class ReservationRequestHandler implements Runnable{

    private Socket clientSocket;

    public ReservationRequestHandler(Socket clientSocket) throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");
        this.clientSocket = clientSocket;
    } //ReservationRequestHandler

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            writer.flush();
            String message = "";
            String response = "";

            while (message != null) {
                message = reader.readLine();
                writer.write(response);
                writer.newLine();
                writer.flush();

            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //run

}