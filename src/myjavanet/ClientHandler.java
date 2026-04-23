package myjavanet;

import model.Card;
import model.Metro;
import model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Metro metro;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler (Socket s, Metro m) {
        socket = s;
        metro = m;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            Student student = metro.getStudent(in.readLine());
            String message = in.readLine();
            double money = 0;
            if (message.equals("addBalance") ) {
                money = Double.parseDouble(in.readLine());
            }
            if ( student == null ) {
                out.println("failed");
                return;
            };

            switch ( message ) {
                case "newcard":
                    if ( !metro.addCard(student)) {
                        out.println();
                    }
                    break;
                case "studentinfo":
                    out.println(metro.getStudentInfo(student));
                    break;
                case "addbalance":
                    metro.addBalance(student, money);
                    break;
                case "ride":
                    metro.takeARide(student);
                    break;
                case "balanceinfo":
                    out.println(  metro.getBalance(student) );
                    break;
                default:
                    System.out.println("wadiyatalkinabeet");
            }

            out.println("Success!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
