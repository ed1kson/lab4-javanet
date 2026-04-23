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
    public Client (String name, String message, Object... args) {
        id = nextId++;

        String hostname = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.printf("Client %d (%s, %s)\n", id, name, message);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(name);
            out.println(message);

            for (Object arg : args) {
                out.println(arg);
            }

            if ( message.equals("studentinfo") || message.equals("balanceinfo")) {
                String info = in.readLine();
                System.out.println("info: " + info);
            }

            System.out.printf("message: %s\n", in.readLine());

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
