package ru.mai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Статья 83 закона “О выборах депутатов Государственной Думы Федерального Собрания Российской Федерации”
 * в предыдущей редакции определяет следующий алгоритм пропорционального распределения мест в парламенте.
 * Необходимо распределить 450 мест между партиями, участвовавших в выборах.
 * Сначала подсчитывается сумма голосов избирателей, поданных за каждую партию и подсчитывается сумма голосов, поданных
 * за все партии.
 * Эта сумма делится на 450, получается величина, называемая “первое избирательное частное” (смысл первого избирательного
 * частного - это количество голосов избирателей, которое необходимо набрать для получения одного места в парламенте).
 * Далее каждая партия получает столько мест в парламенте, чему равна целая часть от деления числа голосов
 * за данную партию на первое избирательное частное.
 * Если после первого раунда распределения мест сумма количества мест, отданных партиям, меньше 450,
 * то оставшиеся места передаются по одному партиям, в порядке убывания дробной части частного от деления числа голосов
 * за данную партию на первое избирательное частное. Если же для двух партий эти дробные части равны, то преимущество
 * отдается той партии, которая получила большее число голосов.
 * <p>
 * Формат ввода
 * <p>
 * На вход программе подается список партий, участвовавших в выборах. Каждая строка входного файла содержит название партии
 * (строка, возможно, содержащая пробелы), затем, через пробел, количество голосов, полученных данной партией – число,
 * не превосходящее 108.
 * <p>
 * Формат вывода
 * <p>
 * Программа должна вывести названия всех партий и количество голосов в парламенте, полученных
 * данной партией. Названия необходимо выводить в том же порядке, в котором они шли во входных данных.
 * <p>
 * В случае ввода некорректных данных программа должна вывести "ERROR!" и корректно завершить работу.
 * <p>
 * Пример 1
 * <p>
 * Ввод
 * Party One 100000
 * Party Two 200000
 * Party Three 400000
 * <p>
 * Вывод
 * Party One 64
 * Party Two 129
 * Party Three 257
 * <p>
 * Пример 2
 * <p>
 * Ввод
 * Party number one 100
 * Partytwo 100
 * <p>
 * Вывод
 * Party number one 225
 * Partytwo 225
 */


public class StateDumaElections {

    static Scanner in = null;
    static final String ERROR_MESSAGE = "ERROR";

    static class PartyComparator implements Comparator<Party> {

        @Override
        public int compare(Party o1, Party o2) {
            if (o1.getFractionalPart() > o2.getFractionalPart()) {
                return -1;
            } else if (o1.getFractionalPart() == o2.getFractionalPart()) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    static class Party {

        static final Integer QUANTITY_OF_PLACES = 450;
        static Integer sum = 0;
        static Integer finalSum = 0;
        static Double firstSelectablePrivate;
        private String nameOfParty;
        private Integer quantityOfMembers;
        private Integer numberOfPlaces;

        public static void calculateFirstSelectablePrivate() {
            firstSelectablePrivate = (double) sum / QUANTITY_OF_PLACES;
        }

        public static boolean isEnoughParties() {
            if (sum > 450) {
                return true;
            } else {
                return false;
            }
        }

        public void setNameOfParty(String nameOfParty) {
            this.nameOfParty = nameOfParty;
        }

        public void setQuantityOfMembers(Integer quantityOfMembers) {
            this.quantityOfMembers = quantityOfMembers;
        }

        public Integer getQuantityOfMembers() {
            return quantityOfMembers;
        }

        public String getNameOfParty() {
            return nameOfParty;
        }

        public void addPlace() {
            ++numberOfPlaces;
        }

        public Integer getCalculatedNumberOfPlaces() {
            numberOfPlaces = (int) Math.floor(this.quantityOfMembers / firstSelectablePrivate);
            return numberOfPlaces;
        }

        public Integer getNumberOfPlaces() {
            return numberOfPlaces;
        }

        private double divideSumFSP() {
            return quantityOfMembers / firstSelectablePrivate;
        }

        public double getFractionalPart() {
            double buff = divideSumFSP() - Math.floor(divideSumFSP());
            return buff;
        }
    }

    public static void main(String[] args) {

        try {
            in = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        ArrayList<Party> parties = new ArrayList<>();

        try {
            while (in.hasNextLine()) {

                Party buffParty = new Party();

                StringBuilder buffStr = new StringBuilder();

                while (!in.hasNextInt()) {
                    buffStr.append(in.next() + " ");
                }

                buffParty.setNameOfParty(String.valueOf(buffStr));

                buffParty.setQuantityOfMembers(in.nextInt());

                parties.add(buffParty);
            }

            Iterator<Party> iterator = parties.iterator();

            while (iterator.hasNext()) {
                Party buffParty = iterator.next();
                Party.sum = Party.sum + buffParty.getQuantityOfMembers();
            }

            Party.calculateFirstSelectablePrivate();

            if (isAnyPlaceAvailable(parties)) {
                ArrayList<Party> buffParties = generateRequiredList(parties);
                Iterator<Party> iter = buffParties.iterator();

                for (int i = 0; i < Party.QUANTITY_OF_PLACES - Party.finalSum; i++) {
                    if (iter.hasNext()) {
                        Party buffParty = iter.next();
                        buffParty.addPlace();
                    } else {
                        iter = buffParties.iterator();
                    }
                }
            }

            outAllParties(parties);
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
        }
    }

    public static void outAllParties(ArrayList<Party> list) {
        for (Party party : list) {
            System.out.println(party.getNameOfParty() + party.getNumberOfPlaces());
        }
    }

    public static boolean isAnyPlaceAvailable(ArrayList<Party> listOfParties) {
        for (Party party : listOfParties) {
            Party.finalSum += party.getCalculatedNumberOfPlaces();
        }

        if (Party.finalSum < Party.QUANTITY_OF_PLACES) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Party> generateRequiredList(ArrayList<Party> list) {
        Collections.sort(list, new PartyComparator());
        return list;
    }
}
