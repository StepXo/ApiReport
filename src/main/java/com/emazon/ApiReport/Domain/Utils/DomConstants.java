package com.emazon.ApiReport.Domain.Utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Pattern;

public class DomConstants {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    public static final int ZERO = 0;
    public static final int ITEM_ERROR = 1;
    public static final int QUANTITY_ERROR = 2;


    public DomConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated.");

    }
}