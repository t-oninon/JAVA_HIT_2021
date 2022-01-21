package data;

import java.util.regex.Pattern;

public class Constants {
    public static Pattern regexPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])" +
            "(?=.*[@$!%*?&_])[A-Za-z0-9@$!%*?&_]{8,15}$");
    public static Pattern regexUserName = Pattern.compile("^[a-zA-Z0-9]{6,15}$");
    public static Pattern card = Pattern.compile("^((010)|(020)|(050)|(100))[0-9]{7}$");
    public static Pattern regexAnyChar = Pattern.compile(".");
    public static Pattern regexLink = Pattern.compile("^https://owbcode.io/.{0,31}$");;
    public static String
        accountTitleAd = String.format("| %3s |  %-10s  |  %-15s  |  %-15s  |  %-5s  |  %-25s  |  %10s  |  %-40s  |",
        "STT" ,"  Mã TK", "Tên tài khoản", "    Mật khẩu", "Admin", "      Họ và tên", "Số dư ($)", "         Thông báo");
    public static String accountHRAd = regexAnyChar.matcher(accountTitleAd).replaceAll("-");

    public static String
            accountTitle = String.format("|  %-10s  |  %-15s  |  %-15s  |  %-5s  |  %-25s  |  %10s  |",
            "  Mã TK", "Tên tài khoản", "    Mật khẩu", "Admin", "      Họ và tên", "Số dư ($)");
    public static String accountHR = regexAnyChar.matcher(accountTitle).replaceAll("-");

    public static String lessonParagraphTitle = String.format("| %3s |  %-10s  |  %-30s  |"
            , "STT", "  Mã bài", "       Tên bài học");
    public static String lessonTitle = lessonParagraphTitle + "     " + lessonParagraphTitle;

    public static String lessonHR = regexAnyChar.matcher(lessonParagraphTitle).replaceAll("-") +
            "     " + regexAnyChar.matcher(lessonParagraphTitle).replaceAll("-");

    public static String lessonTitleAd = String.format("| %3s |  %-10s  |  %-5s  |  %-35s  |  %-50s  |",
             "STT", "   Mã", "Mã KH", "   Tên bài học", "        Nội dung");
    public static String lessonHRAd = regexAnyChar.matcher(lessonTitleAd).replaceAll("-");

    public static String courseTitle = String.format("| %3s |  %-5s  |  %-30s  |  %-8s  |  %-7s  |"
            , "STT", " Mã", "    Tên khóa học", "Đánh giá", "Giá ($)");
    public static String courseHR = regexAnyChar.matcher(courseTitle).replaceAll("-");

    public static String courseTitleAd = String.format("| %3s |  %-5s  |  %-30s  |  %-8s  |  %-7s  |  %-40s  |"
            , "STT", " Mã", "    Tên khóa học", "Đánh giá", "Giá ($)", "          Giới thiệu");
    public static String courseHRAd = regexAnyChar.matcher(courseTitleAd).replaceAll("-");

    public static String rateTitleAd = String.format("| %3s |  %-5s  |  %-30s  | %-10s |  %-25s  | %s |  %-30s |  %-30s |",
            "STT", "Mã KH", "      Tên khóa học", "Mã tài khoản", "    Tên tài khoản", "Vote", "     Bình luận", "    Phản hồi");
    public static String rateHRAd = regexAnyChar.matcher(rateTitleAd).replaceAll("-");

    public static String billTitle = String.format("| %3s |  %-5s  |  %-30s  |  %-7s |  %-20s |",
            "STT", "   Mã", "      Tên khóa học", "Giá ($)", "     Thời gian");
    public static String billHR = regexAnyChar.matcher(billTitle).replaceAll("-");

    public static String billTitleAd = String.format("| %3s |  %-5s  |  %-30s  | %-10s |  %-25s  |  %7s |  %-20s |",
            "STT", "Mã KH", "      Tên khóa học", "Mã tài khoản", "    Tên tài khoản", "Giá ($)", "     Thời gian");
    public static String billHRAd = regexAnyChar.matcher(billTitleAd).replaceAll("-");
}
