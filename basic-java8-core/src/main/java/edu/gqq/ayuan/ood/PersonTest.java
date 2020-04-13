package edu.gqq.ayuan.ood;

public class PersonTest {
    public static void main(String[] args) {
        Person right = PersonFactory.create();
        System.out.println(right.getName() + ":" + right.getAge());

        Person wrong = PersonFactory.createWrong();
        System.out.println(wrong.getName() + ":" + wrong.getAge());
    }
}
