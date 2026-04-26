package myjavanet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static long nextId = 0;
    private long id;
    public Client (String command) {
        id = nextId++;

        String hostname = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.printf("Client %d (%s)\n", id, command);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(command);

            String[] messages = in.readLine().split("[|]");
            System.out.println("Status: " + messages[0]);
            if ( messages.length != 1 ) {
                System.out.println("Info: " + messages[1]);
            }
            System.out.println();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
