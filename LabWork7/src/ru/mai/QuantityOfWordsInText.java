package ru.mai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Во входном файле записан текст. Словом считается последовательность непробельных символов идущих подряд, слова разделены одним или большим числом пробелов или символами конца строки. Определите, сколько различных слов содержится в этом тексте.
 * <p>
 * Формат ввода
 * <p>
 * Вводится текст.
 * <p>
 * Формат вывода
 * <p>
 * Выведите ответ на задачу.
 * <p>
 * Ваша программа будет запускаться следующим образом:
 * java ProgramClass input.txt
 * <p>
 * Соответственно, нужно читать данные из входящего потока.
 */
public class QuantityOfWordsInText {

    static final String ERROR_MESSAGE = "ERROR";
    static Scanner in = null;
    static final byte INPUT_FILE = 0;

    public static void main(String[] args) {

        try {
            in = new Scanner(new File(args[INPUT_FILE].replace("<", "").replace(">", "")));
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MESSAGE);
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        HashSet<String> setOfWords = new HashSet<>();

        while (in.hasNext()) {
            setOfWords.add(in.next());
        }

        System.out.print(setOfWords.size());
    }
}
