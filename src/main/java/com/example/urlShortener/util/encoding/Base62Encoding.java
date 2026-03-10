package com.example.urlShortener.util.encoding;

public class Base62Encoding {

    public static String encode(long number){
        if (number == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (number > 0) {
            sb.append(chars.charAt((int) (number % 62)));
            number /= 62;
        }
        return sb.reverse().toString();
    }
}
