package ru.job4j.bank;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    Bank bnk = new Bank();
    User usr = new User("GH", "tt");

    @Test
    public void whenAddAccountToUserItsAdded() {
        bnk.addUser(usr);
        bnk.addAccountToUser("tt", new Account(200, "ggg"));
        Assert.assertThat(bnk.getUserAccounts("tt").get(0).getReqs(), is("ggg"));
    }

    @Test
    public void whenDeleteAccountFromUserItsAbsent() {
        bnk.addUser(usr);
        bnk.addAccountToUser("tt", new Account(200, "ggg"));
        bnk.deleteAccountFromUser("tt", new Account(200, "ggg"));
        Assert.assertTrue(bnk.getUserAccounts("tt").isEmpty());
    }

    @Test
    public void whenGetUserAccountsItsHere() {
        bnk.addUser(usr);
        bnk.addAccountToUser("tt", new Account(200, "ggg"));
        bnk.addAccountToUser("tt", new Account(100, "fff"));
        List<Account> result = bnk.getUserAccounts("tt");
        Assert.assertThat(result.get(1).getReqs(), is("fff"));
        Assert.assertThat(result.size(), is(2));
    }


    @Test
    public void whenTransferMoneyItsTransfered() {
        bnk.addUser(usr);
        User usr1 = new User("vvv", "kkkk");
        bnk.addUser(usr1);
        bnk.addAccountToUser("tt", new Account(200, "ggg"));
        bnk.addAccountToUser("kkkk", new Account(100, "fff"));
        List<Account> result = bnk.getUserAccounts("tt");
        boolean res = bnk.transferMoney("tt", "ggg", "kkkk", "fff", 60);
        Assert.assertTrue(res);
        Assert.assertThat(bnk.getUserAccounts("tt").get(0).getValues(), is(140.0));
        Assert.assertThat(bnk.getUserAccounts("kkkk").get(0).getValues(), is(160.0));
    }
}
