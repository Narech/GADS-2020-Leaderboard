package com.sounach.gads2020leaderboard.utilities;

import java.util.regex.Pattern;

public class Utils {
    public static boolean isValidEmail(String email)
    {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
}
