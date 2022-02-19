package com.cyecize.demo.api.user;

import com.cyecize.demo.config.db.DataSourceType;
import com.cyecize.demo.config.routing.WithDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<User> findAllDS1(Pageable pageable) {
        return this.findAll(pageable);
    }

    @Override
    @WithDatabase(DataSourceType.SECONDARY)
    public Page<User> findAllDS2(Pageable pageable) {
        return this.findAll(pageable);
    }

    @Override
    @WithDatabase(DataSourceType.TERTIARY)
    public Page<User> findAllDS3(Pageable pageable) {
        return this.findAll(pageable);
    }

    private List<User> findAll() {
        return this.userRepository.findAll();
    }

    private Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }
}
