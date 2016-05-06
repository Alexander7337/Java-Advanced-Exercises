package com.company;

import java.util.Scanner;

/**
 * Created by Alexander7337 on 4/23/2016.
 */
public class Problem1MelrahShake {

    /*
    You are given a string of random characters, and a pattern of random characters.
    You need to “shake off” (remove) all of the border occurrences of that pattern, in other words, the very first match and
    the very last match of the pattern you find in the string.
    When you successfully shake off a match, you remove from the pattern the character which corresponds to
    the index equal to the pattern’s length / 2.
    Then you continue to shake off the border occurrences of the new pattern until the pattern becomes empty or
    until there is less than the - needed for shake, matches in the remaining string.
    In case you have found at least two matches, and you have successfully shaken them off, you print “Shaked it.” on the console.
    Otherwise you print “No shake.”
     */

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String str = scn.nextLine();
        String pattern = scn.nextLine();

        int loops = str.length();
        int lastIndex = 0;
        int count = 0;

        if (str.length() != 0) {

            for (int i = 0; i < loops; i++) {

                if (str.length() > 1 && pattern.length() != 0 && str.contains(pattern)) {

                    while (lastIndex != -1) {

                        lastIndex = str.indexOf(pattern, lastIndex);

                        if (lastIndex != -1) {
                            count++;
                            lastIndex += pattern.length();
                        }
                    }

                    lastIndex = 0;

                    if (count >= 2) {
                        count = 0;

                        int firstIndex = str.indexOf(pattern);
                        int lastIndexOf = str.lastIndexOf(pattern);
                        int patternLength = pattern.length();

                        StringBuilder sb = new StringBuilder();

                        sb.append(str.substring(0, firstIndex));
                        sb.append(str.substring((firstIndex + patternLength), lastIndexOf));
                        sb.append(str.substring((lastIndexOf + patternLength), str.length()));

                        str = sb.toString();
                        sb = new StringBuilder();

                        StringBuilder sbForPattern = new StringBuilder();
                        int index = pattern.length() / 2;
                        sbForPattern.append(pattern);
                        sbForPattern.deleteCharAt(index);
                        pattern = sbForPattern.toString();
                        sbForPattern = new StringBuilder();

                        System.out.println("Shaked it.");

                    }
                } else {
                    System.out.println("No shake.");
                    break;
                }
            }
        } else {
            System.out.println("No shake.");
        }


        System.out.println(str);
    }
}
