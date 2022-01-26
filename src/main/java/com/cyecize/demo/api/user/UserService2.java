package com.cyecize.demo.api.user;

import com.cyecize.demo.config.db.DataSourceType;
import com.cyecize.demo.config.routing.WithDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService2 {

    private final UserRepository userRepository;

    private final UserService3 userService3;

    @WithDatabase(DataSourceType.TERTIARY)
    public List<User> findAll() {
        List<User> users = this.userRepository.findAll();
        users.addAll(this.userService3.findAll());

        return users;
    }
}
