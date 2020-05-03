package ru.mai;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Дан список чисел, который может содержать до 100000 чисел. Определите, сколько в нем встречается различных чисел.
 * <p>
 * Формат ввода
 * Вводится список целых чисел. Все числа списка находятся на одной строке.
 * <p>
 * Формат вывода
 * Выведите ответ на задачу.
 * <p>
 * В случае ввода некорректных данных программа должна вывести "ERROR!" и корректно завершить работу.
 */

public class QuantityOfDiffNumbers {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        HashSet<Integer> mainSet = new HashSet<>();
        HashSet<Integer> buffSet = new HashSet<>();

        while (in.hasNextInt()) {
            int buff = in.nextInt();

            if (mainSet.contains(buff)) {
                buffSet.add(buff);
            } else {
                mainSet.add(buff);
            }

            //выход для консольного ввода
            if (in.hasNext("~")) {
                break;
            }
        }

        mainSet.removeAll(buffSet);

        System.out.print(mainSet.size());
    }
}
