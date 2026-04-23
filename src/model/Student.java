package model;

import java.io.Serializable;

public class Student implements Serializable {
    private static long nextId = 0;
    private long id;
    private String name = null;
    private String uni = null;
    private int age;
    private Card card;

    public Student() {
        id = nextId++;
    }
    public Student(String n, String u, int a) {
        this();
        name = n;
        uni = u;
        age = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s of %s, %d years old", name, uni, age);
    }
}
