package data;

import java.util.regex.Pattern;

public class Constants {
    public static Pattern regexPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])" +
            "(?=.*[@$!%*?&_])[A-Za-z0-9@$!%*?&_]{8,15}$");
    public static Pattern regexUserName = Pattern.compile("^[a-zA-Z0-9]{6,15}$");
    public static Pattern card = Pattern.compile("^((010)|(020)|(050)|(100))[0-9]{7}$");
    public static Pattern regexAnyChar = Pattern.compile(".");
    public static Pattern regexPhone = Pattern.compile("^[0-9\\-\\+]{9,15}$");
    public static String textSeperate = "^.*|$";
    public static String
        accountTitle = String.format("|  %-10s  |  %-15s  |  %-15s  | %-7s |  %-25s  |  %10s  |",
        "ID", "User name", "Password", "IsAdmin", "Full name", "Balane");
    public static String accountHR = regexAnyChar.matcher(accountTitle).replaceAll("-");

    public static String lessonParagraphTitle = String.format("| %3s |  %-10s  |  %-30s  |"
            , "STT", "  Mã bài", "       Tên bài học");
    public static String lessonTitle = lessonParagraphTitle + "     " + lessonParagraphTitle;


    public static String lessonHR = regexAnyChar.matcher(lessonParagraphTitle).replaceAll("-") +
            "     " + regexAnyChar.matcher(lessonParagraphTitle).replaceAll("-");


    public static String courseTitleAd = String.format("| %3s |  %-5s  |  %-30s  |  %-8s  |  %-7s  |  %-40s  |"
            , "STT", "  Mã", "    Tên khóa học", "Đánh giá", "Giá ($)", "          Giới thiệu");
    public static String courseHRAd = regexAnyChar.matcher(courseTitleAd).replaceAll("-");

    public static String courseTitle = String.format("| %3s |  %-5s  |  %-30s  |  %-8s  |   %-7s  |"
            , "STT", "  Mã", "    Tên khóa học", "Đánh giá", " Giá ($)");
    public static String courseHR = regexAnyChar.matcher(courseTitle).replaceAll("-");

    public static String rateTitle = String.format("| %3s |  %-10s  |  %-25s  | %4s |", "STT", "Mã TK",
            "Họ và tên", "Vote");
    public static String rateHR = regexAnyChar.matcher(rateTitle).replaceAll("-");

}
