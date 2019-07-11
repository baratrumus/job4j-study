package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class StoreTest {

    AbstractStore<User> users;
    AbstractStore<Role> roles;

    @Before
    public void setUp() {
        users = new UserStore(3);
        roles = new RoleStore(3);
    }

    @Test
    public void whenAddItsAdded() {
        User user1 = new User("11");
        Role role1 = new Role("22");
        users.add(user1);
        roles.add(role1);
        Base resultUser = users.findById("11");
        Base resultRole = roles.findById("22");
        assertThat(resultUser, is(user1));
        assertEquals(resultRole, role1);
    }

    @Test
    public void whenReplaceItsOk() {
        User user1 = new User("11");
        Role role1 = new Role("22");
        User user2 = new User("111");
        Role role2 = new Role("222");
        users.add(user1);
        roles.add(role1);
        users.replace("11", user2);
        roles.replace("22", role2);
        Base resultUser = users.findById("111");
        Base resultRole = roles.findById("222");
        assertThat(resultUser, is(user2));
        assertEquals(resultRole, role2);
    }

    @Test
    public void whenDeleteItsOk() {
        User user1 = new User("11");
        Role role1 = new Role("22");
        users.add(user1);
        roles.add(role1);
        users.delete("11");
        roles.delete("22");
        Base resultUser = users.findById("11");
        Base resultRole = roles.findById("22");
        assertNull(resultUser);
        assertNull(resultRole);
    }

    @Test
    public void whenFindByIdItsFound() {
        User user1 = new User("11");
        Role role1 = new Role("22");
        users.add(user1);
        roles.add(role1);
        Base resultUser = users.findById("11");
        Base resultRole = roles.findById("22");
        assertThat(resultUser, is(user1));
        assertEquals(resultRole, role1);
    }



}
