package ru.mai;

import java.util.HashSet;
import java.util.Scanner;

/**
 * На Новом проспекте для разгрузки было решено пустить два новых автобусных маршрута на разных участках проспекта. Известны конечные остановки каждого из автобусов. Определите количество остановок, на которых можно пересесть с одного автобуса на другой.
 * <p>
 * Формат ввода
 * Вводятся четыре числа, не превосходящие 100, задающие номера конечных остановок. Сначала для первого, потом второго автобуса (см. примеры и рисунок).
 * <p>
 * Формат вывода
 * Ваша программа должна выводить одно число – искомое количество остановок.
 * <p>
 * В случае ввода некорректных данных программа должна вывести "ERROR!" и корректно завершить работу.
 * <p>
 * Пример 1
 * <p>
 * Ввод
 * <p>
 * 3 6 4 2
 * <p>
 * Вывод
 * <p>
 * 2
 * <p>
 * Пример 2
 * <p>
 * <b>Ввод</b>
 * <p>
 * 3 1 5 10
 * <p>
 * Вывод
 * <p>
 * 0
 */
public class Transfers {

    static Scanner in = new Scanner(System.in);
    static final String ERROR_MESSAGE = "ERROR";

    public static void main(String[] args) {
        try {
            HashSet<Byte> firstBusStops = makeSetOfStops(in.nextByte(), in.nextByte());
            HashSet<Byte> secondBusStops = makeSetOfStops(in.nextByte(), in.nextByte());

            firstBusStops.retainAll(secondBusStops);

            HashSet<Byte> commonStops = firstBusStops;

            System.out.println(commonStops.size());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
        }
    }

    private static HashSet<Byte> makeSetOfStops(Byte startPos, Byte finishStops) {
        HashSet<Byte> buffSet = new HashSet<>();

        if (startPos > finishStops) {
            Byte buff = startPos;
            startPos = finishStops;
            finishStops = buff;
        }

        for (byte i = startPos; i <= finishStops; ++i) {
            buffSet.add(i);
        }

        return buffSet;
    }
}
