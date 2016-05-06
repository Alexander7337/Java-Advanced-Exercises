package com.company;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexander7337 on 5/6/2016.
 */
public class Problem4GUnitReworked {

    /*
    You will be given several input lines which will contain info about a class’ methods and their unit tests.
    All valid lines will be in the following format: {class name} | {method name} | {unit test name}
    The elements are separated by a space, a ‘|’ (vertical line) and another space.
    The valid class names, method names and unit test names can only contain English alphabet letters and digits,
    and must always start with a capital letter. All parameters must be at least 2 symbols long.
    Your task is to save every unit test to its corresponding method and every method to its corresponding class in the database.
    If a class with the given name already exists you should add the new method with its test to it.
    If the method also exists in the given existing class, you should just add the new unit test to the corresponding method.
    If even the test is not new, you should do nothing.
     */

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        TreeMap<String, TreeMap<String, TreeSet<String>>> data = new TreeMap<>();

        String line;
        Pattern pattern = Pattern.compile("^([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+)$");

        while (!"It's testing time!".equals(line = scn.nextLine())) {
            Matcher match = pattern.matcher(line);

            if (match.find()) {
                String className = match.group(1);
                String methodName = match.group(2);
                String testName = match.group(3);

                if (!data.containsKey(className)) {
                    data.put(className, new TreeMap<>());
                    data.get(className).put(methodName, new TreeSet<>());
                    data.get(className).get(methodName).add(testName);
                } else if (!data.get(className).containsKey(methodName)) {
                    data.get(className).put(methodName, new TreeSet<>());
                    data.get(className).get(methodName).add(testName);
                }

                data.get(className).get(methodName).add(testName);
            }
        }

        TreeMap<String, Integer> classesByTests = new TreeMap<>();

        for (Map.Entry<String, TreeMap<String, TreeSet<String>>> entry: data.entrySet()) {
            int testsNumber = 0;

            for (Map.Entry<String, TreeSet<String>> entry2: entry.getValue().entrySet()) {
                testsNumber += entry2.getValue().size();
            }
            classesByTests.put(entry.getKey(), testsNumber);
        }

        /*
        The classes should be ordered first by the amount of unit tests it has – descending, then by the amount of methods it has – ascending,
        and then alphabetically.
        The methods should be ordered by the amount of unit tests they have - descending, and then alphabetically.
        The unit tests should be ordered by the length of their names – ascending and then by alphabetically.
         */
        data.entrySet().stream().sorted((c1, c2) -> Integer.compare(c1.getValue().size(), c2.getValue().size())).
                sorted((c1, c2) -> classesByTests.get(c2.getKey()).compareTo(classesByTests.get(c1.getKey()))).
                forEach(klass -> {

                    System.out.println(klass.getKey() + ":");

                    klass.getValue().entrySet().stream().sorted((m1, m2) -> Integer.compare(m2.getValue().size(), m1.getValue().size())).
                            forEach(method -> {

                                System.out.println("##" + method.getKey());

                                method.getValue().stream().sorted((t1, t2) -> Integer.compare(t1.length(), t2.length())).
                                        forEach(test -> {

                                            System.out.println("####" + test);
                                        });
                            });
                });

    }
}
