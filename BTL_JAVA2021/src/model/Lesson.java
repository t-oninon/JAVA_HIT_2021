package model;

import data.Base;

public class Lesson {
    private String id;
    private String courseId;
    private String name;
    private String content;

    public Lesson() {}

    public Lesson(String id, String courseId, String name, String content) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setTime(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        Lesson lesson = (Lesson) obj;
        return (lesson.getId() == this.id);
    }

    public void join(int stt) {
        System.out.println("\nTrang chủ > ... > Học > Bài số " + stt);
        System.out.println("              -------------------------------------------------------");
        System.out.printf("    ,___,     |%3d.   %-45s |\n", stt, name);
        System.out.printf("    [O.o]  /  |  %-50s |\n","");
        System.out.printf("    /)__)>/   |  %-50s |\n","");
        System.out.printf("----\"--\"-     |  %-50s |\n", content);
        System.out.println("              -------------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("|  %-10s  |  %-5s  |  %-35s  |  %-50s  |", id, courseId, name, content);
    }
}