package baitap;


import java.util.Scanner;

public class Person implements Comparable<Person> {
    private int id;
    private String name;
    private int age;
    private String address;

    public Person() {}

    public Person(int id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id of person: ");
        id = sc.nextInt();
        System.out.print("Enter name of person: ");
        sc.nextLine();
        name = sc.nextLine();
        System.out.print("Enter age of person: ");
        age = sc.nextInt();
        System.out.print("Enter address of person: ");
        sc.nextLine();
        address = sc.nextLine();
    }

    @Override
    public int compareTo(Person obj) {
        return id - obj.getId();
    }

    @Override
    public String toString() {
        return String.format("| %-10d| %-20s| %-10d| %-30s|", id, name, age, address);
    }

}
