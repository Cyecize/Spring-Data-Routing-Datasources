package com.cyecize.demo.api.user;

import java.util.List;

public interface UserService {

    List<User> findAllNestedTransactions();

    List<User> findAllDS1();

    List<User> findAllDS2();

    List<User> findAllDS3();
}
