package model;

import java.io.Serializable;

public class Card implements Serializable {
    private static long nextId = 0;
    private long id;
    private Student holder = null;
    private double balance = 0;

    public Card() {
        id = nextId++;
    }

    public Card(Student stud) {
        this();
        holder = stud;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getHolder() {
        return holder;
    }

    public void setHolder(Student holder) {
        this.holder = holder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Holder-%s, balance: %f", holder.getName(), balance);
    }

}
