package com.bai1;

public class Employee extends Person {
    int salary;

    Employee(String name, String address, int salary) {
        super(name, address);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void display() {
        System.out.println("Employee name: " + this.getName());
        System.out.println("Employee address: " + this.getAddress());
        System.out.println("Employee salary: " + salary);
    }
}
