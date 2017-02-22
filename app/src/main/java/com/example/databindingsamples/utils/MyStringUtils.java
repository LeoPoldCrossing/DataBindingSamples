package com.example.databindingsamples.utils;

/**
 * Created by LeoPoldCrossing on 2017/2/14.
 */

public class MyStringUtils {
    public static String capitalize(String word) {
        StringBuilder sb = new StringBuilder("Import Static Method : ");
        sb.append(word);
        return sb.toString();
    }
}
