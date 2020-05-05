package ru.mai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Во входном файле (input.txt) записан текст.
 * Словом считается последовательность непробельных символов идущих подряд,
 * слова разделены одним или большим числом пробелов или символами конца строки.
 * Для каждого слова из этого текста подсчитайте, сколько раз оно встречалось в этом тексте ранее.
 * <p>
 * Ограничение времени 1 секунда
 * Ограничение памяти 64Mb
 * <p>
 * Формат ввода
 * <p>
 * Вводится текст.
 * <p>
 * Формат вывода
 * <p>
 * Выведите ответ на задачу.
 * <p>
 * Пример 1
 * <p>
 * Ввод
 * <p>
 * one two one tho three
 * <p>
 * Вывод
 * <p>
 * 0 0 1 0 0
 * <p>
 * Пример 2
 * <p>
 * Ввод
 * <p>
 * She sells sea shells on the sea shore;
 * The shells that she sells are sea shells I'm sure.
 * So if she sells sea shells on the sea shore,
 * I'm sure that the shells are sea shore shells.
 * <p>
 * Вывод
 * 0 0 0 0 0 0 1 0 0 1 0 0 1 0 2 2 0 0 0 0 1 2 3 3 1 1 4 0 1 0 1 2 4 1 5 0 0
 * <p>
 * Пример 3
 * <p>
 * Ввод
 * <p>
 * aba aba; aba @?"
 * <p>
 * Вывод
 * 0 0 1 0
 */
public class NumberOfWordAppearing {

    static Scanner in = null;
    static final String FILE_ERROR = "There is no such file!";
    static final int OFFSET = 1;
    static final int DEFAULT = 0;

    public static void main(String[] args) {

        HashMap<String, Integer> mapOfWords = new HashMap<>();
        ArrayList<Integer> numberOfWord = new ArrayList<>();

        try {
            in = new Scanner(new File("input.txt"));

            while (in.hasNext()) {
                String buffWord = in.next();

                if (mapOfWords.containsKey(buffWord)) {
                    mapOfWords.put(buffWord, mapOfWords.get(buffWord) + OFFSET);
                    numberOfWord.add(mapOfWords.get(buffWord));
                } else {
                    mapOfWords.put(buffWord, DEFAULT);
                    numberOfWord.add(DEFAULT);
                }
            }

            for (Integer iter : numberOfWord) {
                System.out.print(iter + " ");
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_ERROR);
        }
    }
}
