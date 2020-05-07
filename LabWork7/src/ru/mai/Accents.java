package ru.mai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Accents {

    static Scanner in = new Scanner(System.in);
    static final String ERROR_MESSAGE = "ERROR";

    public static void main(String[] args) {
        try {
            HashMap<String, HashSet<String>> dictionary = new HashMap<>();

            Integer counterOfIncorrectWords = 0;

            Integer quantityOfWords = in.nextInt();
            in.nextLine();

            for (int i = 0; i < quantityOfWords; i++) {
                String buff = in.nextLine();
                if (dictionary.containsKey(buff.toLowerCase())) {
                    HashSet<String> buffSet = dictionary.get(buff.toLowerCase());
                    buffSet.add(buff);
                } else {
                    HashSet<String> buffSet = new HashSet<>();
                    buffSet.add(buff);
                    dictionary.put(buff.toLowerCase(), buffSet);
                }
            }

            String checkStr = in.nextLine();

            String[] mas = checkStr.split("\\s");

            for (String word : mas) {
                Pattern pattern = Pattern.compile("[A-Z]");
                Matcher matcher = pattern.matcher(word);
                int counter = 0;

                while (matcher.find()) {
                    ++counter;
                }

                if (counter == 1) {
                    if (dictionary.containsKey(word.toLowerCase())) {
                        if (dictionary.get(word.toLowerCase()).contains(word)) {
                            continue;
                        } else {
                            ++counterOfIncorrectWords;
                        }
                    } else {
                        continue;
                    }
                } else {
                    ++counterOfIncorrectWords;
                }
            }

            System.out.println(counterOfIncorrectWords);
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }
    }
}
