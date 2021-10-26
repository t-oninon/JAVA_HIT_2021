package Bai_1;

public class RunMain {
    public static void main(String[] args) {
        Person a = new Person();
        a.setName("Nguyen Trung Thanh");
        a.setAge(18);
        a.setGender("Nam");
        a.setHobby("Sport");
        System.out.println(a);

        Person b = new Person();
        b.setName("Nguyen Dinh Huan");
        b.setAge(19);
        b.setGender("Nam");
        b.setHobby("Code");
        System.out.println(b);
    }
}
