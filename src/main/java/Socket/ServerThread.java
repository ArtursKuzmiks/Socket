package Socket;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Artur Kuzmik on 18.27.5
 */

@Component
public class ServerThread extends Thread {

    private Socket socket;

    ServerThread() {
    }

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {


            System.out.println("New connection from: " + socket.getRemoteSocketAddress().toString());

            String msg;

            while ((msg = reader.readLine()) != null) {
                System.out.println("Have read from client: " + msg);
                out.println(Integer.parseInt(msg) + 1);
                System.out.println("Have wrote to client: " + (Integer.parseInt(msg) + 1));
            }

            System.out.println("Client has disconnected");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
