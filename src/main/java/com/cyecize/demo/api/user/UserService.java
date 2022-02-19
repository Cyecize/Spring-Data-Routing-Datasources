package com.cyecize.demo.api.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<User> findAllNestedTransactions();

    List<User> findAllDS1();

    List<User> findAllDS2();

    List<User> findAllDS3();

    Page<User> findAllDS1(Pageable pageable);

    Page<User> findAllDS2(Pageable pageable);

    Page<User> findAllDS3(Pageable pageable);
}
