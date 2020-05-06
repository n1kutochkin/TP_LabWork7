package ru.mai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;


/**
 * @author n1kutochkin
 * Дан текст (в файле input.txt).
 * Выведите все слова, встречающиеся в тексте, по одному на каждую строку.
 * Слова должны быть отсортированы по убыванию их количества появления в тексте,
 * а при одинаковой частоте появления — в лексикографическом порядке.
 * <p>
 * Пример
 * <p>
 * Ввод
 * <p>
 * oh you touch my tralala
 * mmm my ding ding dong
 * <p>
 * Вывод
 * <p>
 * ding
 * my
 * dong
 * mmm
 * oh
 * touch
 * tralala
 * you
 */
public class FrequencyAnalysis {

    static Scanner in = null;
    static final String ERROR_MESSAGE = "ERROR";

    static class WordComparator implements Comparator<Word> {

        @Override
        public int compare(Word o1, Word o2) {
            if (o1.quantity > o2.quantity) {
                return -1;
            } else if (o1.quantity == o2.quantity) {
                return new WordLexicograghicalComparator().compare(o1, o2);
            } else {
                return 1;
            }
        }
    }

    static class WordLexicograghicalComparator implements Comparator<Word> {

        @Override
        public int compare(Word o1, Word o2) {
            return o1.compareTo(o2);
        }
    }

    static class Word implements Comparable<Word> {

        Integer quantity;
        String str;

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(Word o) {
            return this.str.compareTo(o.str);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Word word = (Word) o;
            return Objects.equals(str, word.str);
        }

        @Override
        public int hashCode() {
            return Objects.hash(quantity, str);
        }
    }

    public static void main(String[] args) {

        try {
            in = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MESSAGE);
        }

        HashSet<Word> buffSet = new HashSet<>();

        while (in.hasNext()) {
            Word buff = new Word();
            buff.setStr(in.next());

            if (buffSet.contains(buff)) {
                for (Word iter : buffSet) {
                    if (iter.equals(buff)) {
                        ++iter.quantity;
                        break;
                    }
                }
            } else {
                buff.quantity = 0;
                buffSet.add(buff);
            }
        }

        TreeSet<Word> setOfWords = new TreeSet<>(new WordComparator());
        setOfWords.addAll(buffSet);

        for (Word word : setOfWords) {
            System.out.println(word.str + " ");
        }
    }
}
