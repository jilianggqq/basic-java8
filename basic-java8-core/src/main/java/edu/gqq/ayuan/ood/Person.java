package edu.gqq.ayuan.ood;

public class Person {
    private int age;
    private String name;

    public void setAge(int ageValue) {
        if (ageValue <= 0) {
            age = 99;
            return;
        }
        age = ageValue;
    }

    public int getAge() {
        return age;
    }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
