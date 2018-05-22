package Socket;

/*
 * @author Artur Kuzmik on 18.19.5
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
    try (
            Socket socket = new Socket("127.0.0.1", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    ) {

        String str;

        out.println(1);
        System.out.println("Have wrote to server: 1");

        while ((str = in.readLine()) != null) {
            System.out.println("Have read from server: " + str);
            int number = Integer.valueOf(str);

            if (number >= 10) {
                break;
            }
            out.println(++number);
            System.out.println("Have wrote to server: " + number);
            Thread.sleep(5000);
        }

        System.out.println("Disconnecting...");

    } catch (Throwable cause) {
        System.out.println("connection error: " + cause.getMessage());
    }
}
}
