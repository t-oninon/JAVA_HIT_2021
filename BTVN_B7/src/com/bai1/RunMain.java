package com.bai1;

public class RunMain {
    public static void main(String[] args) {
        Person person1 = new Employee("Trung", "HaNoi", 3300);
        person1.display();
        Person person2 = new Customer("Linh", "Bac Ninh", 10400);
        person2.display();
    }
}
