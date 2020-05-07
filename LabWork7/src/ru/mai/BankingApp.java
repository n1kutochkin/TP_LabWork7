package ru.mai;


import ru.mai.BankingAppLibrary.BankingSystem;

import java.util.Scanner;

/**
 * Некоторый банк хочет внедрить систему управления счетами клиентов, поддерживающую следующие операции:
 * <p>
 * Пополнение счета клиента.
 * Снятие денег со счета.
 * Запрос остатка средств на счете.
 * Перевод денег между счетами клиентов.
 * Начисление процентов всем клиентам.
 * Вам необходимо реализовать такую систему.
 * Клиенты банка идентифицируются именами (уникальная строка, не содержащая пробелов).
 * Первоначально у банка нет ни одного клиента. Как только для клиента проводится операция пололнения,
 * снятия или перевода денег, ему заводится счет с нулевым балансом. Все дальнейшие операции проводятся
 * только с этим счетом. Сумма на счету может быть как положительной, так и отрицательной, при этом всегда
 * является целым числом.
 * <p>
 * Формат ввода
 * Входной поток содержит последовательность операций. Возможны следующие операции:
 * DEPOSIT name sum - зачислить сумму sum на счет клиента name. Если у клиента нет счета, то счет создается.
 * WITHDRAW name sum - снять сумму sum со счета клиента name. Если у клиента нет счета, то счет создается.
 * BALANCE name - узнать остаток средств на счету клиента name.
 * TRANSFER name1 name2 sum - перевести сумму sum со счета клиента name1 на счет клиента name2.
 * Если у какого-либо клиента нет счета, то ему создается счет.
 * INCOME p - начислить всем клиентам, у которых открыты счета, процент суммы счета.
 * Проценты начисляются только клиентам с положительным остатком на счету, если у клиента остаток отрицательный,
 * то его счет не меняется. После начисления процентов сумма на счету остается целой,
 * то есть начисляется только целое число денежных единиц. Дробная часть начисленных процентов отбрасывается.
 * Формат вывода
 * <p>
 * Для каждого запроса BALANCE программа должна вывести остаток на счету данного клиента.
 * Если же у клиента с запрашиваемым именем не открыт счет в банке, выведите ERROR.
 * <p>
 * В случае ввода некорректных данных программа должна вывести "Invalid Data!" и корректно завершить работу.
 * <p>
 * Пример 1
 * <p>
 * Ввод
 * <p>
 * DEPOSIT Ivanov 100
 * INCOME 5
 * BALANCE Ivanov
 * TRANSFER Ivanov Petrov 50
 * WITHDRAW Petrov 100
 * BALANCE Petrov
 * BALANCE Sidorov
 * <p>
 * Вывод
 * <p>
 * 105
 * -50
 * ERROR
 * <p>
 * Пример 2
 * <p>
 * Ввод
 * <p>
 * BALANCE Ivanov
 * BALANCE Petrov
 * DEPOSIT Ivanov 100
 * BALANCE Ivanov
 * BALANCE Petrov
 * DEPOSIT Petrov 150
 * BALANCE Petrov
 * DEPOSIT Ivanov 10
 * DEPOSIT Petrov 15
 * BALANCE Ivanov
 * BALANCE Petrov
 * DEPOSIT Ivanov 46
 * BALANCE Ivanov
 * BALANCE Petrov
 * DEPOSIT Petrov 14
 * BALANCE Ivanov
 * BALANCE Petrov
 * <p>
 * Вывод
 * <p>
 * ERROR
 * ERROR
 * 100
 * ERROR
 * 150
 * 110
 * 165
 * 156
 * 165
 * 156
 * 179
 * <p>
 * Пример 3
 * <p>
 * Ввод
 * <p>
 * BALANCE a
 * BALANCE b
 * DEPOSIT a 100
 * BALANCE a
 * BALANCE b
 * WITHDRAW a 20
 * BALANCE a
 * BALANCE b
 * WITHDRAW b 78
 * BALANCE a
 * BALANCE b
 * WITHDRAW a 784
 * BALANCE a
 * BALANCE b
 * DEPOSIT b 849
 * BALANCE a
 * BALANCE b
 * <p>
 * Вывод
 * <p>
 * ERROR
 * ERROR
 * 100
 * ERROR
 * 80
 * ERROR
 * 80
 * -78
 * -704
 * -78
 * -704
 * 771
 */
public class BankingApp {

    static final String INVALID_DATA = "Invalid Data!";

    static Scanner in = new Scanner(System.in);
    static final byte OPERATION = 0;

    enum DWOperations {
        OPERATION, NAME, SUM
    }

    enum BOperation {
        OPERATION, NAME
    }

    enum TOperation {
        OPERATION, FROM, TO, SUM
    }

    enum IOperation {
        OPERATION, PERCENT
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        try {
            while (in.hasNextLine() && !in.hasNext("~")) {
                String incomeStr = in.nextLine();
                String[] mas = incomeStr.split("\\s");

                switch (mas[OPERATION]) {
                    case "DEPOSIT":
                        bank.deposit(mas[DWOperations.NAME.ordinal()], Integer.parseInt(mas[DWOperations.SUM.ordinal()]));
                        break;
                    case "WITHDRAW":
                        bank.withdraw(mas[DWOperations.NAME.ordinal()], Integer.parseInt(mas[DWOperations.SUM.ordinal()]));
                        break;
                    case "BALANCE":
                        bank.balance(mas[BOperation.NAME.ordinal()]);
                        break;
                    case "TRANSFER":
                        bank.transfer(mas[TOperation.FROM.ordinal()], mas[TOperation.TO.ordinal()], Integer.parseInt(mas[TOperation.SUM.ordinal()]));
                        break;
                    case "INCOME":
                        bank.income(Integer.parseInt(mas[IOperation.PERCENT.ordinal()]));
                        break;
                    default:
                        System.out.println(INVALID_DATA);
                        return;
                }
            }
        } catch (Exception e) {
            System.out.println(INVALID_DATA);
        }
    }
}
