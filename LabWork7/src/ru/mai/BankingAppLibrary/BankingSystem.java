package ru.mai.BankingAppLibrary;


import java.util.ArrayList;
import java.util.Comparator;

public class BankingSystem implements Comparator<Account> {

    static final String ERROR_MESSAGE = "ERROR";
    ArrayList<Account> activeAccounts;
    Account buffID;

    public BankingSystem() {
        activeAccounts = new ArrayList<>();
    }

    public void deposit(String id, Integer sum) {
        buffID = new Account(id);
        if (activeAccounts.contains(buffID)) {
            activeAccounts.get(activeAccounts.indexOf(buffID)).add(sum);
        } else {
            buffID.add(sum);
            activeAccounts.add(buffID);
        }
        return;
    }

    public void withdraw(String id, Integer sum) {
        buffID = new Account(id);
        if (activeAccounts.contains(buffID)) {
            activeAccounts.get(activeAccounts.indexOf(buffID)).withdraw(sum);
        } else {
            buffID.withdraw(sum);
            activeAccounts.add(buffID);
        }
        return;
    }

    public void balance(String id) {
        buffID = new Account(id);
        if (activeAccounts.contains(buffID)) {
            System.out.println(activeAccounts.get(activeAccounts.indexOf(buffID)).getBalance());
        } else {
            System.out.println(ERROR_MESSAGE);
        }
    }

    public void transfer(String from, String to, Integer sum) {
        withdraw(from, sum);
        deposit(to, sum);
    }

    public void income(Integer percent) {
        for (Account acc : activeAccounts) {
            acc.income(percent);
        }
    }

    @Override
    public int compare(Account o1, Account o2) {
        return 0;
    }
}

