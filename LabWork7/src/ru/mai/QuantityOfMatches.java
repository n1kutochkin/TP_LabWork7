package ru.mai;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Даны два списка чисел, которые могут содержать до 100000 чисел каждый.
 * Найдите числа, которые содержатся одновременно как в первом списке, так и во втором.
 * <p>
 * Формат ввода:
 * Вводятся два списка чисел. Все числа каждого списка находятся на отдельной строке.
 * <p>
 * Формат вывода:
 * Выведите совпадающие числа в порядке возрастания.
 * <p>
 * В случае ввода некорректных данных программа должна вывести "ERROR!" и корректно завершить работу.
 * <p>
 * Пример
 * Ввод
 * <p>
 * 1 2 3 2 1
 * 2 3 4 3 2
 * <p>
 * Вывод
 * <p>
 * 2 3
 */

public class QuantityOfMatches {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        TreeSet<Integer> firstRow = fillRow();
        TreeSet<Integer> secondRow = fillRow();

        for (Integer iter : firstRow) {
            if (secondRow.contains(iter)) {
                System.out.print(iter + " ");
            }
        }
    }

    public static TreeSet<Integer> fillRow() {
        String inputStr = in.nextLine();
        String[] buff = inputStr.split(" ");
        TreeSet<Integer> res = new TreeSet<Integer>();
        for (String str : buff) {
            res.add(Integer.parseInt(str));
        }
        return res;
    }
}
