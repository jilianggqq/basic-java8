package edu.gqq.ayuan.ood;

public class PersonFactory {
    public static Person create() {
        Person p = new Person();
        p.setAge(30);
        p.setName("Peter");
        return p;
    }

    public static Person createWrong() {
        Person p = new Person();
        p.setAge(-30);
        p.setName("Jhon");
        return p;
    }
}
