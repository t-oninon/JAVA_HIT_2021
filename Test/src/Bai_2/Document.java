package Bai_2;

public class Document {
    static int a = 0;
    protected int id; // Primary key
    protected String name;
    protected String publisher;
    protected double price;

    public Document() {
        a++;
        id = a;
    }

    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        Document.a = a;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}