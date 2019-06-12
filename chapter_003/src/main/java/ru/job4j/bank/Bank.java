package ru.job4j.bank;

import java.util.*;

public class Bank {
    private Map<User, ArrayList<Account>> bankAccounts = new TreeMap<>();

    public void addUser(User user) {
        this.bankAccounts.put(user, new ArrayList<Account>());
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
                accList = new ArrayList<Account>();
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
        Account retAcc = null;
        for (Account acc : this.bankAccounts.get(user)) {
            if (reqs.equals(acc.getReqs())) {
                retAcc = acc;
                break;
            }
        }
        return retAcc;
    }


    private User getUserByPassport(String passport) {
        User retUser = null;
        for (User user : this.bankAccounts.keySet()) {
            if (passport.equals(user.getPassport())) {
                retUser = user;
                break;
            }
        }
        return retUser;
    }
}
