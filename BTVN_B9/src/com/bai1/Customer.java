package com.bai1;

public class Customer extends Person{
    public int balance;

    public Customer(String name, String address, int balance) {
        super(name, address);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void display() {
        System.out.println("Customer name: " + this.getName());
        System.out.println("Customer address: " + this.getAddress());
        System.out.println("Customer salary: " + balance);
    }
}
