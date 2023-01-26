package org.example.collections.user;

import org.junit.Assert;
import org.junit.Test;

public class UsersTest {

    @Test
    public void isUserDataCorrect() {
        Users user = new Users();
        Assert.assertTrue(user.areLoginAndPasswordCorrectForExistingUser("testlogin", "zaq1@WSX"));
    }

    @Test
    public void isUserDataIncorrect() {
        Users user = new Users();
        Assert.assertFalse(user.areLoginAndPasswordCorrectForExistingUser("testLogin", "pasw"));
    }
}
