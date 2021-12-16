package baitap;

import java.util.*;

public class RunMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Person> personLists = new ArrayList<>();
        int quanlity;
        System.out.print("Enter personNumber: ");
        quanlity = sc.nextInt();
        for (int i = 0; i < quanlity; i++) {
            System.out.println("Person[" + i + "]");
            boolean isOk;
            Person person = new Person();

            do {
                isOk = true;
                try {
                    person.input();
                }
                catch (Exception e) {
                    isOk = false;
                    System.out.println("ERROR INPUT! please redo");
                }
            } while (!isOk);
            personLists.add(person);
        }

        System.out.println("Initial people list");
        System.out.printf("| %-10s| %-20s| %-10s| %-30s| %n", "id", "Name", "Age", "Address");
        personLists.forEach(person -> System.out.println(person));


        //Sort asc id use Comparable
        Collections.sort(personLists);
        System.out.println("People list after sort asc by id");
        System.out.printf("| %-10s| %-20s| %-10s| %-30s| %n", "id(ASC)", "Name", "Age", "Address");
        personLists.forEach(person -> System.out.println(person));

        //Sort desc name use Comparator
        Collections.sort(personLists, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("People list after sort desc by name");
        System.out.printf("| %-10s| %-20s| %-10s| %-30s| %n", "id", "Name(DESC)", "Age", "Address");
        personLists.forEach(person -> System.out.println(person));

        // Sort desc age use lambda
        personLists.sort(((o1, o2) -> o2.getAge() - o1.getAge()));
        System.out.println("People list after sort desc by age");
        System.out.printf("| %-10s| %-20s| %-10s| %-30s| %n", "id", "Name", "Age(DESC)", "Address");
        personLists.forEach(person -> System.out.println(person));
    }
}
