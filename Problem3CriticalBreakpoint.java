package com.company;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alexander7337 on 4/23/2016.
 */
public class Problem3CriticalBreakpoint {

    /*
    You are given a set of 4 integers separated by a single space, on every line of input, which
    represents plane coordinates for each line – {x1, y1}, {x2, y2}, the two points that form the line.
    Your task is to find if the given lines form a plane which holds a critical breakpoint.
    The critical breakpoint is formed only if the critical ratio of the current line is equal to the others’ critical ratios or
    is equal to ZERO. The critical ratio is, for each line, the absolute value of “(X2 + Y2) – (X1 + Y1)”.
    If you find a non-zero critical ratio value it becomes the actual critical ratio, and if there is even one line, which
    critical ratio does not equal that value, and is not equal to zero, the current lines fail to create a critical breakpoint.
    If a critical breakpoint is formed you need to calculate it.
    A critical breakpoint is equal to – the remainder of the division of, the critical ratio powered by the count of lines, and
    the count of lines.
     */

    public static boolean bool = true;
    public static boolean bool2 = true;
    public static BigInteger ifNotZero = new BigInteger("0");
    public static BigInteger absoluteValue;
    public static boolean bool3 = true;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        LinkedHashMap<Integer, BigInteger> check = new LinkedHashMap<>();

        ArrayList<List<Integer>> board = new ArrayList<>();
        String line = scn.nextLine();

        while (!line.equals("Break it.") && line.length() != 0) {
            String[] tokens = line.split("\\s");

            if (tokens.length > 4) {
                line = scn.nextLine();
                continue;
            }

            List<Integer> nums = new ArrayList<>();
            nums = Arrays.stream(tokens).map(Integer::parseInt).collect(Collectors.toList());
            board.add(nums);
            line = scn.nextLine();
        }

        for (int i = 0; i < board.size(); i++) {

            List<Integer> list = new ArrayList<>(board.get(i));

            int x2 = list.get(2);
            int y2 = list.get(3);
            int x1 = list.get(0);
            int y1 = list.get(1);
            absoluteValue = BigInteger.valueOf(Math.abs(((long)x2 + (long)y2) - ((long)x1 + (long)y1)));

            if (i == 0) {
                check.put(i, absoluteValue);
            } else if (i != 0 ) {
                for (Map.Entry<Integer, BigInteger> pair: check.entrySet()) {
                    BigInteger v = pair.getValue();

                    if (absoluteValue.compareTo(BigInteger.valueOf(0)) == 0
                            || (absoluteValue.compareTo(v) == 0 && v.compareTo(BigInteger.valueOf(0)) == 1)) {
                        bool = true;
                        if (absoluteValue.compareTo(BigInteger.valueOf(0)) == 1) {
                            ifNotZero = absoluteValue;
                        }

                    } else if (v.compareTo(BigInteger.valueOf(0)) == 1){
                        bool = false;
                        break;
                    } else {
                        ifNotZero = absoluteValue;
                    }
                }

            }

            if (!bool) {
                System.out.println("Critical breakpoint does not exist.");
                bool2 = false;
                break;
            } else {
                check.put(i, absoluteValue);
            }

        }

        if (bool2) {

            BigInteger calculation;
            int size = check.size();

            calculation = ifNotZero.pow(size);
            calculation = calculation.mod(BigInteger.valueOf(size));

            for (int i = 0; i < board.size(); i++) {
                System.out.println("Line: " + board.get(i));
            }

            System.out.println("Critical Breakpoint: " + calculation);

        }

    }
}
