package myjavanet;

import model.Card;
import model.Metro;
import model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

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

            String message = in.readLine();
            String[] parts = message.split("[|]");
            System.out.println(Arrays.toString(parts));

            String response = "";
            Student student;
            if ((student = metro.getStudent(parts[1])) == null) {
                out.println("Fail|Student was not found");
                return;
            };

            switch (parts[0]) {
                case "newcard":
                    if ( !addCard(student)) {
                        response += "Fail|";
                        response += ("Student already has a card");
                    }
                    response += "Success!";
                    break;
                case "studentinfo":
                    response += "Success!";
                    response += "|" + (getStudentInfo(student));
                    break;
                case "addbalance":
                    double money;
                    if ( (money = Double.parseDouble(parts[2])) == 0 ) {
                        response += ("Fail|Can't top up with 0 UAH");
                    }
                    addBalance(student, money);
                    response += "Success!";
                    System.out.printf("Student %s topped up the card with %f UAH", student.getName(), money);
                    break;
                case "ride":
                    if ( !takeARide(student) ) {
                        response += "Fail|";
                        response += "Not sufficient money";
                    }
                    break;
                case "balanceinfo":
                    response += "Success!|";
                    response += ( getBalance(student) );
                    break;
                default:
                    System.out.println("wadiyatalkinabeet");
                    out.println("Error");
            }

            out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized boolean addCard(Student student) {
        return metro.addCard(student);
    }

    private synchronized String getStudentInfo(Student student) {
        return metro.getStudentInfo(student);
    }

    private synchronized void addBalance(Student student, double money) {
        metro.addBalance(student, money);
    }

    private synchronized boolean takeARide(Student student) {
        return metro.takeARide(student);
    }

    private synchronized double getBalance(Student student) {
        return metro.getBalance(student);
    }

}
