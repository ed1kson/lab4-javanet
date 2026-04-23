package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class Metro implements Serializable {
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();
    private double cost = 5;

    public Metro() { }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addStudent(String name, String uni, int age) {
        students.add(new Student(name, uni, age));
    }

    public Student getStudent(String name) {
        for ( Student stud : students ) {
            if ( stud.getName().toLowerCase().contains(name.toLowerCase()) ) {
                return stud;
            }
        }

        return null;
    }

    public Student getStudent(long id) {
        for ( Student stud : students ) {
            if ( stud.getId() == id ) {
                return stud;
            }
        }

        return null;
    }

    public boolean addCard(Student student) {
        if ( student.getCard() != null ) {
            return false;
        }
        Card card = new Card(student);
        cards.add(card);
        student.setCard(card);

        return true;
    }

    public boolean addCard(String name) {
        return addCard(getStudent(name));
    }

    public boolean addCard(String name, double balance) {
        if ( !addCard(name) ) {
            return false;
        }
        addBalance(name, balance);

        return true;
    }

    public Card getCard(Student student) {
        return student.getCard();
    }

    public void addBalance(Card card, double money) {
        card.setBalance(card.getBalance()+money);
    }

    public void addBalance(Student student, double money) {
        Card card = student.getCard();
        card.setBalance(card.getBalance()+money);
    }

    public void addBalance(String name, double money) {
        addBalance(getStudent(name), money);
    }

    public boolean takeARide(Student student) {
        if ( student == null || student.getCard().getBalance()<cost ) return false;
        Card card = student.getCard();
        card.setBalance(card.getBalance() - cost);

        return true;
    }

    public boolean takeARide(String name) {
        return takeARide(getStudent(name));
    }

    public String getStudentInfo(Student stud) {
        return String.format("Name: %s\nage: %d\nstudies at: %s\ncard balance: %f",
                stud.getName(), stud.getAge(), stud.getUni(), stud.getCard().getBalance());
    }

    public void printStudentInfo(Student stud) {
        System.out.printf("Name: %s\nage: %d\nstudies at: %s\ncard balance: %f",
                stud.getName(), stud.getAge(), stud.getUni(), stud.getCard().getBalance());
    }

    public double getBalance(Student student) {
        return student.getCard().getBalance();
    }

}
