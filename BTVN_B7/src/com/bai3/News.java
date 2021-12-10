package com.bai3;

import java.util.ArrayList;

public class News implements INews {
    private static int save;
    private int id;
    private String title;
    private String pulishDate;
    private String author;
    private String content;
    private double averangeRate;
    ArrayList<Integer> rateList = new ArrayList<Integer>();

    public News() {
        this.id =++ save;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPulishDate() {
        return pulishDate;
    }

    public void setPulishDate(String pulishDate) {
        this.pulishDate = pulishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getAverangeRate() {
        return averangeRate;
    }

    public ArrayList<Integer> getRateList() {
        return rateList;
    }

    @Override
    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Publish date: " + pulishDate);
        System.out.println("Author: " + author);
        System.out.println("Content: " + content);
        System.out.println("Averange of rate: " + averangeRate);
    }

    public void caculate() {
        double result = (double)rateList.stream().reduce((sum, index) -> sum + index).get() / rateList.size();
        averangeRate = Double.parseDouble(String.format("%.2f", result));
    }
}
