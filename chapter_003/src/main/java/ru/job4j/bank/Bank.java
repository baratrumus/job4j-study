package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {
    private Map<User, ArrayList<Account>> bankAccounts = new TreeMap<>();

    public void addUser(User user) {
        this.bankAccounts.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        this.bankAccounts.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null && account != null) {
            ArrayList<Account> accList = this.bankAccounts.get(user);
            accList.add(account);
            this.bankAccounts.put(user, accList);
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null && account != null) {
            ArrayList<Account> accList = this.bankAccounts.get(user);
            accList.remove(account);
            this.bankAccounts.put(user, accList);
        }
    }


    public List<Account> getUserAccounts(String passport) {
        User user = getUserByPassport(passport);
        ArrayList<Account> accList = new ArrayList<>();
        if (user != null) {
            accList = this.bankAccounts.get(user);
            if (accList == null) {
                accList = new ArrayList<>();
            }
        }
        return accList;
    }

    /**
     * метод для перечисления денег amount с одного счёта на другой счёт
     * если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят)
     * должен вернуть false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport,
                                  String dstRequisite, double amount) {
        boolean result = false;
        Optional<User> srcUser = Optional.ofNullable(getUserByPassport(srcPassport));
        Optional<User> dstUser = Optional.ofNullable(getUserByPassport(dstPassport));
        if (srcUser.isPresent() && dstUser.isPresent()) {
           Optional<Account> srcAcc = Optional.ofNullable(getAccByReqs(srcUser.get(), srcRequisite));
           Optional<Account> dstAcc = Optional.ofNullable(getAccByReqs(dstUser.get(), dstRequisite));
           if (srcAcc.isPresent() && dstAcc.isPresent() && (srcAcc.get().getValues() >= amount)) {
               srcAcc.get().setValues(srcAcc.get().getValues() - amount);
               dstAcc.get().setValues(dstAcc.get().getValues() + amount);
               result = true;
           }
        }
        return result;
    }

    private Account getAccByReqs(User user, String reqs) {
             List<Account> retAccs =  this.bankAccounts.get(user)
                .stream()
                .filter(acc -> acc.getReqs().equals(reqs))
                .collect(Collectors.toList());
        return retAccs.isEmpty() ? null : retAccs.get(0);
    }


    private User getUserByPassport(String passport) {
        List<User> retUsers =  this.bankAccounts.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .collect(Collectors.toList());
        return retUsers.isEmpty() ? null : retUsers.get(0);
    }
}
