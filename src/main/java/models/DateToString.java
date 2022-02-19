package models;

import java.util.Date;

public class DateToString {
    public static String convert(Date date){
        return String.format("%1$tb %1$te, %1$tY ", date);
    }
}
