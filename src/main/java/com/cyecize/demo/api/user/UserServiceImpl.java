package com.cyecize.demo.api.user;

import com.cyecize.demo.config.db.DataSourceType;
import com.cyecize.demo.config.routing.WithDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserService2 userService2;

    @Override
    @WithDatabase(DataSourceType.SECONDARY)
    public List<User> findAllNestedTransactions() {
        List<User> users = this.userRepository.findAll();
        users.addAll(this.userService2.findAll());

        return users;
    }

    @Override
    @WithDatabase
    public List<User> findAllDS1() {
        return this.findAll();
    }

    @Override
    @WithDatabase(DataSourceType.SECONDARY)
    public List<User> findAllDS2() {
        return this.findAll();
    }

    @Override
    @WithDatabase(DataSourceType.TERTIARY)
    public List<User> findAllDS3() {
        return this.findAll();
    }

    private List<User> findAll() {
        return this.userRepository.findAll();
    }
}
