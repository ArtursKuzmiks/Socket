package Socket;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
class Server {

    private static final int PORT = 8080;

    void serverRun() throws IOException {
        new Server().runServer();

    }

    private void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server uo & ready for connections...");

        do {
            Socket clientSocket = serverSocket.accept();
            new ServerThread(clientSocket).start();
        } while (true);
    }

}
