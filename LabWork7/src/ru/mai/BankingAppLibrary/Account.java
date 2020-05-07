package ru.mai.BankingAppLibrary;

import java.util.Objects;

public class Account implements Comparable<Account> {

    String name;
    Integer balance;

    Account(String name) {
        this.name = name;
        this.balance = 0;
    }

    public Integer getBalance() {
        return balance;
    }

    public void add(Integer add) {
        balance += add;
    }

    public void withdraw(Integer withdrawal) {
        balance -= withdrawal;
    }

    public void income(double percent) {
        if (balance > 0) {
            balance = (int) Math.floor(balance * (1 + (percent / 100)));
        }
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return name.equals(account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
