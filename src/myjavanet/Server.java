package myjavanet;

import model.Metro;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Metro metro = new Metro();

        String[] actions = {"newcard", "studentinfo", "addbalance", "ride", "balanceinfo"};

        metro.addStudent("Eduard Oplakanskyi", "Karazin", 18);
        metro.addStudent("Ning nhuyen shalavin", "karazin", 19);
        metro.addStudent("Sofia Sitnikova", "karazin", 19);

        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                new Thread(new ClientHandler(socket, metro)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
