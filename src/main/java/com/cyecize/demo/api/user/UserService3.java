package com.cyecize.demo.api.user;

import com.cyecize.demo.config.routing.WithDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService3 {

    private final UserRepository userRepository;

    @WithDatabase
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
