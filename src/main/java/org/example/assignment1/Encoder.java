package org.example.assignment1;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Encoder {

    // Private constructor to prevent instantiation from outside
    private Encoder() {}

    // Singleton instance
    private static final Encoder INSTANCE = new Encoder();

    // Public method to access the singleton instance
    public static Encoder getInstance() {
        return INSTANCE;
    }

    // Method to encode password
    public static String encodingPassword(String password) {
        StringBuilder encodedPassword = new StringBuilder();
        for (char c : password.toCharArray()) {
            encodedPassword.append((char) (c + 1));
        }
        return encodedPassword.toString();
    }

    // Method to decode password (not implemented here)
    // Add your implementation as needed
    public static String decodingPassword(String password) {
        StringBuilder decodedPassword = new StringBuilder();
        for (char c : password.toCharArray()) {
            decodedPassword.append((char) (c - 1));
        }
        return decodedPassword.toString();
    }

    // Method to convert date string to timestamp
    public Timestamp convertToTimestamp(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }
}