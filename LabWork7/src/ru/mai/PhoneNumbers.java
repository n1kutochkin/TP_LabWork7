package ru.mai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Телефонные номера в адресной книге мобильного телефона имеют один из следующих форматов:
 * +7<код><номер>
 * 8<код><номер> <номер>, где <номер> — это семь цифр, а <код> — это три цифры или три цифры в круглых скобках.
 * Если код не указан, то считается, что он равен 495.
 * Кроме того, в записи телефонного номера может стоять знак “-” между любыми двумя цифрами (см. пример).
 * <p>
 * На данный момент в адресной книге телефона Васи записано всего три телефонных номера,
 * и он хочет записать туда еще один.
 * Но он не может понять, не записан ли уже такой номер в телефонной книге.
 * Помогите ему!
 * Два телефонных номера совпадают, если у них равны коды и равны номера.
 * Например, +7(916)0123456 и 89160123456 — это один и тот же номер.
 * <p>
 * Формат ввода
 * <p>
 * В первой строке входных данных записан номер телефона, который Вася хочет добавить в адресную книгу своего телефона.
 * В следующих трех строках записаны три номера телефонов, которые уже находятся в адресной книге телефона Васи.
 * Гарантируется, что каждая из записей соответствует одному из приведенных в условии форматов.
 * <p>
 * Формат вывода
 * <p>
 * Для каждого телефонного номера в адресной книге выведите YES (заглавными буквами), если он совпадает с тем телефонным
 * номером, который Вася хочет добавить в адресную книгу или NO (заглавными буквами) в противном случае.
 * <p>
 * В случае ввода некорректных данных программа должна вывести "ERROR!" и корректно завершить работу.
 * <p>
 * Пример 1
 * <p>
 * Ввод
 * 8(495)430-23-97
 * <p>
 * +7-4-9-5-43-023-97
 * <p>
 * 4-3-0-2-3-9-7
 * <p></>
 * 8-495-430
 * <p>
 * Вывод
 * YES
 * YES
 * NO
 */
public class PhoneNumbers {

    static Scanner in = new Scanner(System.in);
    static final String YES_MESSAGE = "YES";
    static final String NO_MESSAGE = "NO";
    static final String ERROR_MESSAGE = "ERROR";

    static class PhoneNumber {

        private static final int CODE = 2;
        private static final int CODE_OF_MOSCOW = 495;
        private static final int NUMBER = 4;
        private static Pattern pattern = Pattern.compile("(\\+7|8)?\\-?\\(?((\\d\\-?){3})?\\)?((\\d\\-?){7})");
        private static Matcher matcher;


        private String number;

        PhoneNumber(String number) {
            setNumber(number);
        }

        public void setNumber(String number) {
            matcher = pattern.matcher(number);
            matcher.find();

            StringBuilder resStr = new StringBuilder();

            try {
                if (matcher.group(CODE) == null) {
                    resStr.append(CODE_OF_MOSCOW);
                } else {
                    resStr.append(matcher.group(CODE).replaceAll("-", ""));
                }

                resStr.append(matcher.group(NUMBER).replaceAll("-", ""));

                this.number = new String(resStr);
            } catch (Exception e) {
                System.out.println(ERROR_MESSAGE);
                throw e;
            }
        }

        public String getNumber() {
            return number;
        }
    }

    public static void main(String[] args) {
        PhoneNumber number;
        Collection<String> setOfContacts;

        try {
            number = new PhoneNumber(in.nextLine());
            setOfContacts = fillSet();
        } catch (Exception e) {
            return;
        }

        for (String iter : setOfContacts) {
            if (iter.equals(number.getNumber())) {
                System.out.println(YES_MESSAGE);
            } else {
                System.out.println(NO_MESSAGE);
            }
        }
    }

    public static Collection<String> fillSet() {

        Collection<String> buffSet = new ArrayList<>();

        while (in.hasNextLine()) {
            PhoneNumber buffNumber = new PhoneNumber(in.nextLine());
            buffSet.add(buffNumber.getNumber());

            if (in.hasNext("~")) {
                break;
            }
        }

        return buffSet;
    }
}
