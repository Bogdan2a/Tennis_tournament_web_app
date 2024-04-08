package org.example.assignment1;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Encoder {

    private Encoder() {}

    // singleton instance
    private static final Encoder INSTANCE = new Encoder();

    public static Encoder getInstance() {
        return INSTANCE;
    }

    //encode password
    public static String encodingPassword(String password) {
        StringBuilder encodedPassword = new StringBuilder();
        for (char c : password.toCharArray()) {
            encodedPassword.append((char) (c + 1));
        }
        return encodedPassword.toString();
    }

    //decode password
    public static String decodingPassword(String password) {
        StringBuilder decodedPassword = new StringBuilder();
        for (char c : password.toCharArray()) {
            decodedPassword.append((char) (c - 1));
        }
        return decodedPassword.toString();
    }

}