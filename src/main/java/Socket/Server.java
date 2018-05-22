package Socket;

/*
 * Author: Arturs Kuzmiks
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        Thread tr = new Thread(() -> {

            try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {

                System.out.println("New connection from: " + clientSocket.getRemoteSocketAddress().toString());

                String str;

                while ((str = in.readLine()) != null) {
                    System.out.println("Have read from client: " + str);
                    out.println(Integer.parseInt(str) + 1);
                    System.out.println("Have wrote to client: " + (Integer.parseInt(str) + 1));
                }

                System.out.println("Client has disconnected");

            } catch (Throwable cause) {
                System.out.println("connection error: " + cause.getMessage());
            }

        });

        tr.start();
    }
}
